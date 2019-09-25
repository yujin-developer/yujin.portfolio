package tsms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.StudentDao;
import tsms.vo.StudentVO;

@Component("/student/update.do")
public class StudentUpdateController implements Controller, DataBinding {
	StudentDao studentDao;

	public StudentUpdateController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "student", tsms.vo.StudentVO.class, "goModify", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		StudentVO student = (StudentVO) model.get("student");
		String goModify = (String) model.get("goModify");
		HttpSession session = (HttpSession) model.get("session");
		StudentVO sessionStudent = (StudentVO) session.getAttribute("student");

		if (student.getEmail() == null && goModify == null) {
			// 로그인 후 프로필 상세보기
			infoFromDB(model);
			return "/student/StudentReadProfile.jsp";
		} else if (student.getEmail() == null && goModify.equals("goModify")) {
			// 로그인 후 프로필 상세보기에서 수정 클릭후
			infoFromDB(model);
			return "/student/StudentUpdate.jsp";
		} else {
			// 수정 완료해서 DB에 update
			studentDao.update(student);
			infoFromDB(model);

			if (sessionStudent.getId() != student.getId()) {
				// 아이디 또는 비번 수정됐으면 강제로그아웃시키고 로그인페이지로
				model.put("updateIdPwd", "updateIdPwd");
				return "redirect:../auth/logout.do";
			} else {
				// 수정완료후 프로필페이지로 바로 넘기기
				model.put("successUpdate", "successUpdate");
				return "redirect:/student/StudentReadProfile.jsp";
			}
		}
	}

	public void infoFromDB(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		StudentVO sessionStudent = (StudentVO) session.getAttribute("student");
		sessionStudent = studentDao.selectOne(sessionStudent.getId());
		model.put("address", studentDao.address_option_sido());
		model.put("student", sessionStudent);
	}
}
