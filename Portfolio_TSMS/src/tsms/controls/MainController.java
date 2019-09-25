package tsms.controls;

import java.util.List;
import java.util.Map;

import tsms.annotation.Component;
import tsms.dao.EnglishBoardDao;
import tsms.dao.StudentDao;
import tsms.dao.TeacherDao;
import tsms.vo.EnglishBoardVO;
import tsms.vo.StudentVO;
import tsms.vo.TeacherVO;

@Component("/main.do")
public class MainController implements Controller {
	TeacherDao teacherDao;
	public MainController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
		return this;
	}
	
	StudentDao studentDao;
	public MainController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
		return this;
	}

	EnglishBoardDao englishBoardDao;
	public MainController setEnglishBoardDao(EnglishBoardDao englishBoardDao) {
		this.englishBoardDao = englishBoardDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		List<TeacherVO> teachers = teacherDao.selectList();
		model.put("teachersSize", teachers.size());
		model.put("teachers", teachers);
		List<StudentVO> students = studentDao.selectList();
		model.put("studentsSize", students.size());
		model.put("students", students);
		List<EnglishBoardVO> englishs = englishBoardDao.selectList();
		model.put("englishsSize", englishs.size());
		model.put("englishs", englishs);
		
		return "/main/Main.jsp";

	}

}
