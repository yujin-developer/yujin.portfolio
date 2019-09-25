package tsms.controls;

import java.util.List;
import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.StudentDao;
import tsms.vo.AddressVO;
import tsms.vo.StudentVO;

@Component("/student/insert.do")
public class StudentInsertController implements Controller, DataBinding {
	StudentDao studentDao;

	public StudentInsertController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "student", tsms.vo.StudentVO.class, "sido", String.class, "checkId", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		StudentVO student = (StudentVO) model.get("student");
		String sido = (String) model.get("sido");
		String checkId = (String) model.get("checkId");

		if (student.getId() == null & sido == null & checkId == null) {
			model.put("address", studentDao.address_option_sido());
			return "/student/StudentForm.jsp";
		} else if (checkId != null) {
			String id = "";
			id = studentDao.check_duplicate_id(checkId).getId();

			if (id == null) {
				return "result: ";
			} else {
				return "result:" + id;
			}
		} else if (sido != null) {
			List<AddressVO> list = studentDao.address_option_sigungu(sido);
			String str = "";
			for (int i = 0; i < list.size(); i++) {
				str += list.get(i).getSi_gun_gu() + ",";
			}
			return "result:" + str;
		} else {
			studentDao.insert(student);
			model.put("showId", student.getId());
			return "/notice/Welcome.jsp";
		}
	}
}
