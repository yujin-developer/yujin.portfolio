package tsms.controls;

import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.StudentDao;

@Component("/student/delete.do")
public class StudentDeleteController implements Controller, DataBinding {
	StudentDao studentDao;

	public StudentDeleteController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "student_id", Integer.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Integer student_id = (Integer) model.get("student_id");
		studentDao.delete(student_id);

		return "redirect:../main/Main.jsp";
	}

}
