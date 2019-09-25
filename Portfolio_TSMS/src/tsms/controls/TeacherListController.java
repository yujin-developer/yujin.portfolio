package tsms.controls;

import java.util.HashMap;
import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.TeacherDao;

@Component("/teacher/list.do")
public class TeacherListController implements Controller, DataBinding {
	TeacherDao teacherDao;

	public TeacherListController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
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
		/*if (searchType == null || searchType.equals("") || search == null || search.equals("")) {
		} else if (search != null) {
			paramMap.put("searchType", searchType);
			paramMap.put("search", search);
		}*/

		if (search != null) {
			paramMap.put("searchType", searchType);
			paramMap.put("search", search);
		}
		paramMap = teacherDao.selectList(go, gogroup, paramMap);
		model.put("page", paramMap.get("page"));
		model.put("teachers", paramMap.get("teachers"));
		
		return "/teacher/TeacherList.jsp";
	}

}
