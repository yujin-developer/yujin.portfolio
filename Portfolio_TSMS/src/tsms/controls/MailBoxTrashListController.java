package tsms.controls;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.MailBoxDao;
import tsms.vo.StudentVO;

@Component("/mailbox/trash/list.do")
public class MailBoxTrashListController implements Controller, DataBinding {
	MailBoxDao mailBoxDao;

	public MailBoxTrashListController setMailBoxDao(MailBoxDao mailBoxDao) {
		this.mailBoxDao = mailBoxDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "searchType", String.class, "search", String.class, "go", String.class, "gogroup",
				String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String searchType = (String) model.get("searchType");
		String search = (String) model.get("search");
		String go = (String) model.get("go");
		String gogroup = (String) model.get("gogroup");

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (search != null) {
			paramMap.put("searchType", searchType);
			paramMap.put("search", search);
		}

		HttpSession session = (HttpSession) model.get("session");
		StudentVO student = (StudentVO) session.getAttribute("student");
		String id = student.getId();
		paramMap = mailBoxDao.selectTrashList(go, gogroup, paramMap, id);
		model.put("page", paramMap.get("page"));
		model.put("trashmails", paramMap.get("trashmails"));

		return "/mailbox/TrashList.jsp";

	}

}
