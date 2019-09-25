package tsms.controls;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.MailBoxDao;
import tsms.vo.StudentVO;

@Component("/mailbox/receive/list.do")
public class MailBoxReceiveListController implements Controller, DataBinding {
	MailBoxDao mailBoxDao;

	public MailBoxReceiveListController setMailBoxDao(MailBoxDao mailBoxDao) {
		this.mailBoxDao = mailBoxDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "searchType", String.class, "search", String.class, "go", String.class, "gogroup",
				String.class, "starList", String.class, "sendList", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String searchType = (String) model.get("searchType");
		String search = (String) model.get("search");
		String go = (String) model.get("go");
		String gogroup = (String) model.get("gogroup");
		String starList = (String) model.get("starList");
		String sendList = (String) model.get("sendList");

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (search != null) {
			paramMap.put("searchType", searchType);
			paramMap.put("search", search);
		}

		HttpSession session = (HttpSession) model.get("session");
		StudentVO student = (StudentVO) session.getAttribute("student");
		String id = student.getId();

		if (starList == null && sendList == null) { // 받은쪽지함 리스트 보여주기
			paramMap = mailBoxDao.selectReceiveList(go, gogroup, paramMap, id);
			model.put("page", paramMap.get("page"));
			model.put("mails", paramMap.get("receivemails"));
		} else if (starList != null) { // 별표쪽지함 리스트 보여주기
			paramMap = mailBoxDao.selectStarList(go, gogroup, paramMap, id);
			model.put("page", paramMap.get("page"));
			model.put("mails", paramMap.get("starmails"));
		} else if (sendList != null) { // 보낸쪽지함 리스트 보여주기
			paramMap = mailBoxDao.selectSendList(go, gogroup, paramMap, id);
			model.put("page", paramMap.get("page"));
			model.put("mails", paramMap.get("sendmails"));
		}

		return "/mailbox/MailReceiveList.jsp";

	}

}
