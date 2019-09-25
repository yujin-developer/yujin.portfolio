package tsms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import tsms.bind.DataBinding;
import tsms.bind.ServletRequestDataBinder;
import tsms.context.ApplicationContext;
import tsms.controls.Controller;
import tsms.listeners.ContextLoaderListener;


@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	HashMap<String, Object> model = new HashMap<String, Object>();
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		MultipartRequest multi = null;
		
		try {
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
			Controller pageController = (Controller) ctx.getBean(servletPath);
			model.put("session", request.getSession());
			String noMulti = request.getParameter("noMulti");

			if(request.getMethod().equals("POST") && noMulti == null) {
				ServletContext sc = this.getServletContext();
				//String fileurl = "D:\\JSP\\Portfolio_TSMS\\WebContent\\upload";
				String saveFolder = "upload";
				String fileurl = sc.getRealPath(saveFolder);
				String encType="UTF-8";
				final int MAXSIZE = 5*1024*1024;
				DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
				multi = new MultipartRequest(request,fileurl,MAXSIZE,encType,policy);
				model.put("multi", multi);
			}

			if(pageController instanceof DataBinding) {
				if(request.getMethod().equals("GET") || noMulti != null) {
					prepareRequestData(request, model, (DataBinding)pageController);
				}else {
					prepareRequestData(multi, model, (DataBinding)pageController);
				}
			}
		
			String viewUrl = pageController.execute(model);

			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
			}else if(viewUrl.startsWith("result:")) {
				PrintWriter out = response.getWriter();
				out.print(viewUrl.substring(7));
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			/*request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);*/
		}
	}

	private void prepareRequestData(MultipartRequest request,HashMap<String, Object> model, DataBinding dataBinding) throws Exception {
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		
		for(int i=0; i<dataBinders.length; i+=2) {
			dataName = (String) dataBinders[i];
			dataType = (Class<?>) dataBinders[i+1];
			
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}
	
	private void prepareRequestData(HttpServletRequest request,HashMap<String, Object> model, DataBinding dataBinding) throws Exception {
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		
		for(int i=0; i<dataBinders.length; i+=2) {
			dataName = (String) dataBinders[i];
			dataType = (Class<?>) dataBinders[i+1];
			
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			
			/*System.out.println("dataName:"+dataName);
			System.out.println("dataType:"+dataType);
			System.out.println("dataObj:"+dataObj);*/
			model.put(dataName, dataObj);
			
		}
	}
}
