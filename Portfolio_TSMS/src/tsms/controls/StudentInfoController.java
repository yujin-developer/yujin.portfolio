package tsms.controls;

import java.util.HashMap;
import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.StudentDao;
import tsms.vo.StudentVO;

@Component("/student/info.do")
public class StudentInfoController implements Controller, DataBinding {
	StudentDao studentDao;

	public StudentInfoController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "id", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String id = (String) model.get("id");
		StudentVO student = studentDao.selectOne(id);
		paramMap.put("readcount", student.getReadcount() + 1);
		paramMap.put("id", id);

		int result = studentDao.updateReadcount(paramMap); // 조회수 update
		if (result > 0) {
			
			model.put("student", student);
		}

		return "/student/StudentInfo.jsp";

	}

}
