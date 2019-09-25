package tsms.controls;

import java.util.HashMap;
import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.StudentDao;

@Component("/student/list.do")
public class StudentListController implements Controller, DataBinding {
	StudentDao studentDao;

	public StudentListController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
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

		paramMap = studentDao.selectList(go, gogroup, paramMap);
		model.put("page", paramMap.get("page"));
		model.put("students", paramMap.get("students"));

		return "/student/StudentList.jsp";

	}

}
