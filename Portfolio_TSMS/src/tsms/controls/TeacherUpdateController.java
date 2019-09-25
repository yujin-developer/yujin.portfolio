package tsms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.TeacherDao;
import tsms.vo.TeacherVO;

@Component("/teacher/update.do")
public class TeacherUpdateController implements Controller, DataBinding {
	TeacherDao teacherDao;

	public TeacherUpdateController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "teacher", tsms.vo.TeacherVO.class, "goModify", String.class, "teacher_photo",
				String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		TeacherVO teacher = (TeacherVO) model.get("teacher");
		String goModify = (String) model.get("goModify");
		String teacher_photo = (String) model.get("teacher_photo");
		HttpSession session = (HttpSession) model.get("session");
		TeacherVO sessionTeacher = (TeacherVO) session.getAttribute("teacher");

		if (teacher.getEmail() == null && goModify == null) {
			// 로그인 후 프로필 상세보기
			infoFromDB(model);
			return "/teacher/TeacherReadProfile.jsp";
		} else if (teacher.getEmail() == null && goModify.equals("goModify")) {
			// 로그인 후 프로필 상세보기에서 수정 클릭후
			infoFromDB(model);
			return "/teacher/TeacherUpdate.jsp";
		} else {
			// 수정 완료해서 DB에 update
			MultipartRequest multi = (MultipartRequest) model.get("multi");
			teacherDao.update(teacher, teacher_photo, multi);
			infoFromDB(model);

			if (sessionTeacher.getId() != teacher.getId()) {
				// 아이디 또는 비번 수정됐으면 강제로그아웃시키고 로그인페이지로
				model.put("updateIdPwd", "updateIdPwd");
				return "redirect:../auth/logout.do";
			} else {
				// 수정완료후 프로필페이지로 바로 넘기기
				model.put("successUpdate", "successUpdate");
				return "redirect:/teacher/TeacherReadProfile.jsp";
			}
		}
	}

	public void infoFromDB(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		TeacherVO sessionTeacher = (TeacherVO) session.getAttribute("teacher");
		sessionTeacher = teacherDao.selectOne(sessionTeacher.getId());
		model.put("universitys", teacherDao.university_option());
		model.put("address", teacherDao.address_option_sido());
		model.put("teacher", sessionTeacher);
	}
}
