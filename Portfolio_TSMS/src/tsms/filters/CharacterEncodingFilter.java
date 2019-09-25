package tsms.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {
	FilterConfig config;
	
    public CharacterEncodingFilter() {
    
    }

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter) throws IOException, ServletException {
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		response.setContentType("text/html; charset=UTF-8");
		
		nextFilter.doFilter(request, response);
	
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}
