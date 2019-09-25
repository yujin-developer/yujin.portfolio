package tsms.dao;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.oreilly.servlet.MultipartRequest;

import tsms.annotation.Component;
import tsms.vo.EnglishBoardVO;
import tsms.vo.PageVO;
import tsms.vo.TeacherVO;

@Component("englishBoardDao")
public class EnglishBoardDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	//메인에서 영어게시판 목록 최근 10개 및 총등록개수 가져오기 
	public List<EnglishBoardVO> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("tsms.dao.EnglishBoardDao.selectList");
		} finally {
			sqlSession.close();
		}
	}
	
	// english board list(페이징, 검색)
	public HashMap<String, Object> selectList(String go, String gogroup, HashMap<String, Object> paramMap)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PageVO page = new PageVO();
		List<EnglishBoardDao> english = new ArrayList<EnglishBoardDao>();
		try {
			if (paramMap.get("search") == null) {
				english = sqlSession.selectList("tsms.dao.EnglishBoardDao.selectList");
			} else {
				english = sqlSession.selectList("tsms.dao.EnglishBoardDao.selectListMap", paramMap);
			}

			page = page.methodForPage(go, gogroup, english);

			paramMap.put("englishs", english);
			paramMap.put("page", page);
			return paramMap;
		} finally {
			sqlSession.close();
		}
	}

	// 영어게시판 새글 등록
	public int insert(EnglishBoardVO english, MultipartRequest multi) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Enumeration files = multi.getFileNames();

		String photoFile = (String) files.nextElement();
		String photo = multi.getFilesystemName(photoFile);
		english.setPhoto(photo);
		try {
			int count = sqlSession.insert("tsms.dao.EnglishBoardDao.insert", english);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	// 영어게시판 글 상세보기
	public EnglishBoardVO selectOne(int bno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("tsms.dao.EnglishBoardDao.selectOne", bno);
		} finally {
			sqlSession.close();
		}
	}

	// 게시글 수정
	public int update(EnglishBoardVO english, String english_photo, MultipartRequest multi) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EnglishBoardVO originEnglish = sqlSession.selectOne("tsms.dao.EnglishBoardDao.selectOne", english.getStudent_id());
			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			// 이전 값이랑 다를경우에만 수정할수 있도록 하는 코드
			if (!english.getSubject().equals(originEnglish.getSubject())) {
				paramMap.put("subject", english.getSubject());
			}
			if (!english.getContent().equals(originEnglish.getContent())) {
				paramMap.put("content", english.getContent());
			}

			Enumeration files = multi.getFileNames();
			String photoFile = (String) files.nextElement();
			String photo = multi.getFilesystemName(photoFile);

			if ((english_photo.equals("yes") && photo == null) || (english_photo.equals("no") && photo == null)) {
				// 원래 사진 그대로 유지
			} else if (photo != null) {
				// 사진 수정
				english.setPhoto(photo);
				paramMap.put("photo", english.getPhoto());
			}

			if (paramMap.size() > 0) {
				paramMap.put("Student_id", english.getStudent_id());
				int count = sqlSession.update("tsms.dao.EnglishBoardDao.update", paramMap);
				sqlSession.commit();
				return count;
			} else {
				return 0;
			}
		} finally {
			sqlSession.close();
		}
	}

	// 게시글 삭제
	public int delete(int student_id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("tsms.dao.EnglishBoardDao.delete", student_id);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	//조회수 올려서 DB에 새로 저장
	public int updateReadcount(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("tsms.dao.EnglishBoardDao.updateReadcount", paramMap);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
