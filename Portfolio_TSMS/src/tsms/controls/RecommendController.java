package tsms.controls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import tsms.annotation.Component;
import tsms.dao.TeacherDao;
import tsms.vo.StudentVO;
import tsms.vo.TeacherVO;

@Component("/teacher/recommend.do")
public class RecommendController implements Controller {
	TeacherDao teacherDao;

	public RecommendController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// session에 있는 학생 가져와서 매칭항목들 map에 담아서 DB로 보낸후 TeacherList 반환
		HttpSession session = (HttpSession) model.get("session");
		StudentVO student = (StudentVO) session.getAttribute("student");

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("student_gender", student.getGender());
		paramMap.put("student_address", student.getAddress());
		paramMap.put("student_subject", student.getSubject());
		paramMap.put("student_grade", student.getGrade());
		List<TeacherVO> recommendTeachers = teacherDao.selectRecommendList(paramMap);

		String str = "";
		int randomIndex = 0;
		Random r = new Random();
		randomIndex = r.nextInt(recommendTeachers.size());
		TeacherVO teacher = (TeacherVO) recommendTeachers.get(randomIndex);

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
		str += "<input type='hidden' id='id' name='id' value='"+teacher.getId()+"'>";
		str += "</table>";

		return "result:" + str;
	}
}
