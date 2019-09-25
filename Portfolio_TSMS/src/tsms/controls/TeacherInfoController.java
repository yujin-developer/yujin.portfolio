package tsms.controls;

import java.util.HashMap;
import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.TeacherDao;
import tsms.vo.TeacherVO;

@Component("/teacher/info.do")
public class TeacherInfoController implements Controller, DataBinding {
	TeacherDao teacherDao;

	public TeacherInfoController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "readDialog", String.class, "id", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String id = (String) model.get("id");
		TeacherVO teacher = teacherDao.selectOne(id);
		String readDialog = (String) model.get("readDialog");

		paramMap.put("readcount", teacher.getReadcount() + 1);
		paramMap.put("id", id);

		int result = teacherDao.updateReadcount(paramMap); // 조회수 update
		if (result > 0) {
			model.put("teacher", teacher);
		}

		if (readDialog != null) { // 쪽지함에서 dialog로 상세보기
			String str = "";
			str += "<table>";
			str += "	<tr><td><img width=100 height=100 src='http://localhost:8088/Portfolio_TSMS/upload/"
					+ teacher.getPhoto() + "'><br></td></tr>";
			str += "	<tr><th>이름</th><td>" + teacher.getName() + "</td></tr>";
			str += "	<tr><th>나이</th><td>" + teacher.getAge() + "</td></tr>";
			str += "	<tr><th>성별</th><td>" + teacher.getGender() + "</td></tr>";
			str += "	<tr><th>대학교</th><td>" + teacher.getUniversity() + "</td></tr>";
			str += "	<tr><th>과외가능지역1</th><td>" + teacher.getAddress1() + "</td></tr>";
			str += "	<tr><th>과외가능지역2</th><td>" + teacher.getAddress2() + "</td></tr>";
			str += "	<tr><th>과외가능과목</th><td>";
			str += (teacher.getSubject().equals("무관")) ? "수학 , 영어" : "";
			str += (teacher.getSubject().equals("수학")) ? "수학 " : "";
			str += (teacher.getSubject().equals("영어")) ? "영어" : "";
			str += "	</td></tr>";
			str += "	<tr>";
			str += "		<th>과외가능학생</th>";
			str += "		<td>";
			str += (teacher.getGrade().equals("all")) ? "전 학년" : "";
			str += (teacher.getGrade().contains("e")) ? "초 " : "";
			str += (teacher.getGrade().contains("m1")) ? "중1 " : "";
			str += (teacher.getGrade().contains("m2")) ? "중2 " : "";
			str += (teacher.getGrade().contains("m3")) ? "중3 " : "";
			str += (teacher.getGrade().contains("h1")) ? "고1 " : "";
			str += (teacher.getGrade().contains("h2")) ? "고2 " : "";
			str += (teacher.getGrade().contains("h3")) ? "고3 " : "";
			str += "		</td>";
			str += "	</tr>";
			str += "	<tr>";
			str += "		<th>과외가능요일</th>";
			str += "		<td>";
			str += (teacher.getDay().equals("all")) ? "월 화 수 목 금 토 일" : "";
			str += (teacher.getDay().contains("월")) ? "월 " : "";
			str += (teacher.getDay().contains("화")) ? "화 " : "";
			str += (teacher.getDay().contains("수")) ? "수 " : "";
			str += (teacher.getDay().contains("목")) ? "목 " : "";
			str += (teacher.getDay().contains("금")) ? "금 " : "";
			str += (teacher.getDay().contains("토")) ? "토 " : "";
			str += (teacher.getDay().contains("일")) ? "일 " : "";
			str += "		</td>";
			str += "	</tr>";
			str += "	<tr><th>과외가능시간</th><td>" + teacher.getTime() + "</td></tr>";
			str += "	<tr><th>자기소개</th><td>" + teacher.getIntroduce() + "</td></tr>";
			str += "</table>";

			return "result:" + str;
		} else {// 리스트에서 상세보기
			return "/teacher/TeacherInfo.jsp";
		}
	}
}
