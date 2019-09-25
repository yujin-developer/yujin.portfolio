package tsms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.StudentDao;
import tsms.dao.TeacherDao;
import tsms.vo.StudentVO;
import tsms.vo.TeacherVO;

@Component("/auth/login.do")
public class LoginController implements Controller, DataBinding {
	TeacherDao teacherDao;

	public LoginController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
		return this;
	}

	StudentDao studentDao;

	public LoginController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "loginInfoTeacher", tsms.vo.TeacherVO.class, "loginInfoStudent", tsms.vo.StudentVO.class,
				"teacherLogin", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		TeacherVO loginInfoTeacher = (TeacherVO) model.get("loginInfoTeacher");
		StudentVO loginInfoStudent = (StudentVO) model.get("loginInfoStudent");

		if (loginInfoTeacher.getId() == null && loginInfoStudent.getId() == null) {// 로그인 화면 보여주기
			return "/auth/LoginForm.jsp";
		} else if (model.get("teacherLogin") != null) { // 선생님 아이디/비번 일치하는지 체크한후 로그인처리
			TeacherVO teacher = teacherDao.exist(loginInfoTeacher.getId(), loginInfoTeacher.getPassword());
			if (teacher != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("teacher", teacher);
				return "redirect:../main.do";
			} else {
				model.put("loginFail", "loginFail");
				return "redirect:login.do";
			}
		} else { // 학생 아이디,비번 체크후 로그인 처리
			StudentVO student = studentDao.exist(loginInfoStudent.getId(), loginInfoStudent.getPassword());
			if (student != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("student", student);
				return "redirect:../main.do";
			} else {
				model.put("loginFail", "loginFail");
				return "redirect:login.do";
			}
		}
	}
}
