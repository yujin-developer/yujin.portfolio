package tsms.controls;

import java.util.List;
import java.util.Map;

import com.oreilly.servlet.MultipartRequest;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.TeacherDao;
import tsms.vo.AddressVO;
import tsms.vo.TeacherVO;

@Component("/teacher/insert.do")
public class TeacherInsertController implements Controller, DataBinding {
	TeacherDao teacherDao;

	public TeacherInsertController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "teacher", tsms.vo.TeacherVO.class, "sido", String.class, "checkId", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		TeacherVO teacher = (TeacherVO) model.get("teacher");
		String sido = (String) model.get("sido");
		String checkId = (String) model.get("checkId");

		if (teacher.getId() == null & sido == null & checkId == null) { // 등록화면
			model.put("universitys", teacherDao.university_option());
			model.put("address", teacherDao.address_option_sido());
			return "/teacher/TeacherForm.jsp";
		} else if (checkId != null) { // 아이디 중복체크
			if (teacherDao.check_duplicate_id(checkId) == null) { // 아이디 사용가능
				return "result:";
			} else { // 아이디 사용 불가능
				return "result:" + teacherDao.check_duplicate_id(checkId).getId();
			}
		} else if (sido != null) { // 시군구 옵션 가져오기
			List<AddressVO> list = teacherDao.address_option_sigungu(sido);
			String str = "";
			for (int i = 0; i < list.size(); i++) {
				str += list.get(i).getSi_gun_gu() + ",";
			}
			return "result:" + str;
		} else { // DB에 등록
			MultipartRequest multi = (MultipartRequest) model.get("multi");
			teacherDao.insert(teacher, multi);
			model.put("showId", teacher.getId());
			return "/notice/Welcome.jsp";
		}
	}

}
