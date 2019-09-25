package tsms.dao;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.oreilly.servlet.MultipartRequest;

import tsms.annotation.Component;
import tsms.vo.AddressVO;
import tsms.vo.PageVO;
import tsms.vo.StudentVO;
import tsms.vo.TeacherVO;

@Component("studentDao")
public class StudentDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	//메인에서 학생 목록 최근 10개 및 총등록개수 가져오기 
	public List<StudentVO> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.StudentDao.selectList");
		} finally {
			sqlSession.close();
		}
	}
	
	// 학생 목록 가져오기(페이징, 검색)
	public HashMap<String, Object> selectList(String go, String gogroup, HashMap<String, Object> paramMap)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PageVO page = new PageVO();
		List<TeacherVO> students = new ArrayList<TeacherVO>();
		try {
			if(paramMap.get("search") == null) {
				students = sqlSession.selectList("tsms.dao.StudentDao.selectList");
			}else {
				students = sqlSession.selectList("tsms.dao.StudentDao.selectListMap", paramMap);
			}
			page = page.methodForPage(go, gogroup, students);

			paramMap.put("students", students);
			paramMap.put("page", page);
			return paramMap;
		} finally {
			sqlSession.close();
		}
	}

	// 학생 DB에 등록
	public int insert(StudentVO student) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("tsms.dao.StudentDao.insert", student);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	// 학생 상세보기
	public StudentVO selectOne(String id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("tsms.dao.StudentDao.selectOne", id);
		} finally {
			sqlSession.close();
		}
	}

	// 학생 수정
	public int update(StudentVO Student) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			StudentVO originStudent = sqlSession.selectOne("tsms.dao.StudentDao.selectOne", Student.getStudent_id());
			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			// 이전 값이랑 다를경우에만 수정할수 있도록 하는 코드
			if (!Student.getId().equals(originStudent.getId())) {
				paramMap.put("id", Student.getId());
			}
			if (!Student.getPassword().equals(originStudent.getPassword())) {
				paramMap.put("password", Student.getPassword());
			}
			if (!Student.getName().equals(originStudent.getName())) {
				paramMap.put("name", Student.getName());
			}
			if (!Student.getGender().equals(originStudent.getGender())) {
				paramMap.put("gender", Student.getGender());
			}
			if (!Student.getAge().equals(originStudent.getAge())) {
				paramMap.put("age", Student.getAge());
			}
			if (!Student.getEmail().equals(originStudent.getEmail())) {
				paramMap.put("email", Student.getEmail());
			}
			if (!Student.getAddress().equals(originStudent.getAddress())) {
				paramMap.put("address", Student.getAddress());
			}
			if (!Student.getSubject().equals(originStudent.getSubject())) {
				paramMap.put("subject", Student.getSubject());
			}
			if (!Student.getGrade().equals(originStudent.getGrade())) {
				paramMap.put("grade", Student.getGrade());
			}
			if (!Student.getDay().equals(originStudent.getDay())) {
				paramMap.put("day", Student.getDay());
			}
			if (!Student.getTime().equals(originStudent.getTime())) {
				paramMap.put("time", Student.getTime());
			}
			if (!Student.getIntroduce().equals(originStudent.getIntroduce())) {
				paramMap.put("introduce", Student.getIntroduce());
			}

			if (paramMap.size() > 0) {
				paramMap.put("Student_id", Student.getStudent_id());
				int count = sqlSession.update("tsms.dao.StudentDao.update", paramMap);
				sqlSession.commit();
				return count;
			} else {
				return 0;
			}
		} finally {
			sqlSession.close();
		}
	}

	// 학생 삭제
	public int delete(int student_id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("tsms.dao.StudentDao.delete", student_id);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	// 학생 로그인
	public StudentVO exist(String id, String password) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			StudentVO student = new StudentVO().setId(id).setPassword(password);
			return sqlSession.selectOne("tsms.dao.StudentDao.exist", student);
		} finally {
			sqlSession.close();
		}
	}

	// 주소(시도) 옵션들 DB에서 가져오기
	public List<AddressVO> address_option_sido() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.StudentDao.selectAddrOption");
		} finally {
			sqlSession.close();
		}
	}

	// 주소(시군구) 옵션들 DB에서 가져오기
	public List<AddressVO> address_option_sigungu(String sido) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.StudentDao.selectAddrOptionSigungu", sido);
		} finally {
			sqlSession.close();
		}
	}

	// 학생 등록시 아이디 중복 체크하기
	public StudentVO check_duplicate_id(String checkId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("tsms.dao.StudentDao.selectDuplicateId", checkId);
		} finally {
			sqlSession.close();
		}
	}

	//조회수 올려서 DB에 새로 저장
	public int updateReadcount(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("tsms.dao.StudentDao.updateReadcount", paramMap);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
