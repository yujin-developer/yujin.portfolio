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
import tsms.vo.TeacherVO;
import tsms.vo.UniversityVO;

@Component("teacherDao")
public class TeacherDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	//메인에서 선생님 목록 최근 10개 및 총등록개수 가져오기 
	public List<TeacherVO> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.TeacherDao.selectList");
		} finally {
			sqlSession.close();
		}
	}
	
	// 선생님 목록 가져오기(페이징, 검색)
	public HashMap<String, Object> selectList(String go, String gogroup, HashMap<String, Object> paramMap)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PageVO page = new PageVO();
		List<TeacherVO> teachers = new ArrayList<TeacherVO>();
		try {
			if (paramMap.get("search") == null) {
				teachers = sqlSession.selectList("tsms.dao.TeacherDao.selectList");
			} else {
				teachers = sqlSession.selectList("tsms.dao.TeacherDao.selectListMap", paramMap);
			}

			page = page.methodForPage(go, gogroup, teachers);

			paramMap.put("teachers", teachers);
			paramMap.put("page", page);
			return paramMap;
		} finally {
			sqlSession.close();
		}
	}

	// 선생님 DB에 등록
	public int insert(TeacherVO teacher, MultipartRequest multi) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Enumeration files = multi.getFileNames();

		String photoFile = (String) files.nextElement();
		String photo = multi.getFilesystemName(photoFile);
		teacher.setPhoto(photo);
		try {
			int count = sqlSession.insert("tsms.dao.TeacherDao.insert", teacher);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	// 선생님 상세보기
	public TeacherVO selectOne(String id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("tsms.dao.TeacherDao.selectOne", id);
		} finally {
			sqlSession.close();
		}
	}

	// 선생님 수정
	public int update(TeacherVO teacher, String teacher_photo, MultipartRequest multi) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			TeacherVO originTeacher = sqlSession.selectOne("tsms.dao.TeacherDao.selectOneForUpdate", teacher.getTeacher_id());
			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			// 이전 값이랑 다를경우에만 수정할수 있도록 하는 코드
			if (!teacher.getId().equals(originTeacher.getId())) {
				paramMap.put("id", teacher.getId());
			}
			if (!teacher.getPassword().equals(originTeacher.getPassword())) {
				paramMap.put("password", teacher.getPassword());
			}
			if (!teacher.getName().equals(originTeacher.getName())) {
				paramMap.put("name", teacher.getName());
			}
			if (!teacher.getGender().equals(originTeacher.getGender())) {
				paramMap.put("gender", teacher.getGender());
			}
			if (!teacher.getAge().equals(originTeacher.getAge())) {
				paramMap.put("age", teacher.getAge());
			}
			if (!teacher.getEmail().equals(originTeacher.getEmail())) {
				paramMap.put("email", teacher.getEmail());
			}
			if (!teacher.getUniversity().equals(originTeacher.getUniversity())) {
				paramMap.put("university", teacher.getUniversity());
			}
			if (!teacher.getAddress1().equals(originTeacher.getAddress1())) {
				paramMap.put("address1", teacher.getAddress1());
			}
			if (!teacher.getAddress2().equals(originTeacher.getAddress2())) {
				paramMap.put("address2", teacher.getAddress2());
			}
			if (!teacher.getSubject().equals(originTeacher.getSubject())) {
				paramMap.put("subject", teacher.getSubject());
			}
			if (!teacher.getGrade().equals(originTeacher.getGrade())) {
				paramMap.put("grade", teacher.getGrade());
			}
			if (!teacher.getDay().equals(originTeacher.getDay())) {
				paramMap.put("day", teacher.getDay());
			}
			if (!teacher.getTime().equals(originTeacher.getTime())) {
				paramMap.put("time", teacher.getTime());
			}
			if (!teacher.getIntroduce().equals(originTeacher.getIntroduce())) {
				paramMap.put("introduce", teacher.getIntroduce());
			}

			Enumeration files = multi.getFileNames();
			String photoFile = (String) files.nextElement();
			String photo = multi.getFilesystemName(photoFile);

			if ((teacher_photo.equals("yes") && photo == null) || (teacher_photo.equals("no") && photo == null)) {
				// 원래 사진 그대로 유지
			} else if (photo != null) {
				// 사진 수정
				teacher.setPhoto(photo);
				paramMap.put("photo", teacher.getPhoto());
			}

			if (paramMap.size() > 0) {
				paramMap.put("teacher_id", teacher.getTeacher_id());
				int count = sqlSession.update("tsms.dao.TeacherDao.update", paramMap);
				sqlSession.commit();
				return count;
			} else {
				return 0;
			}
		} finally {
			sqlSession.close();
		}
	}

	// 선생님 삭제
	public int delete(int teacher_id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("tsms.dao.TeacherDao.delete", teacher_id);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	// 선생님 로그인
	public TeacherVO exist(String id, String password) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			TeacherVO teacher = new TeacherVO().setId(id).setPassword(password);
			return sqlSession.selectOne("tsms.dao.TeacherDao.exist", teacher);
		} finally {
			sqlSession.close();
		}
	}

	// 대학교 옵션들 DB에서 가져오기
	public List<UniversityVO> university_option() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.TeacherDao.selectUniOption");
		} finally {
			sqlSession.close();
		}
	}

	// 주소(시도) 옵션들 DB에서 가져오기
	public List<AddressVO> address_option_sido() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.TeacherDao.selectAddrOption");
		} finally {
			sqlSession.close();
		}
	}

	// 주소(시군구) 옵션들 DB에서 가져오기
	public List<AddressVO> address_option_sigungu(String sido) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.TeacherDao.selectAddrOptionSigungu", sido);
		} finally {
			sqlSession.close();
		}
	}

	// 선생님 등록시 아이디 중복 체크하기
	public TeacherVO check_duplicate_id(String checkId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("tsms.dao.TeacherDao.selectDuplicateId", checkId);
		} finally {
			sqlSession.close();
		}
	}

	// 추천받은 선생님 목록 가져오기
	public List<TeacherVO> selectRecommendList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.TeacherDao.selectRecommend", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	//조회수 올려서 DB에 새로 저장
	public int updateReadcount(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("tsms.dao.TeacherDao.updateReadcount", paramMap);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
