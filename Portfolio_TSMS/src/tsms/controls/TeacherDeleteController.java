package tsms.controls;

import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.TeacherDao;

@Component("/teacher/delete.do")
public class TeacherDeleteController implements Controller, DataBinding {
	TeacherDao teacherDao;

	public TeacherDeleteController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "teacher_id", Integer.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Integer teacher_id = (Integer) model.get("teacher_id");
		teacherDao.delete(teacher_id);

		return "redirect:../auth/logout.do";
	}
}
