package tsms.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import tsms.annotation.Component;
import tsms.vo.ReplyVO;

@Component("englishReplyDao")
public class EnglishReplyDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	// 영어게시판 댓글 목록
	public List<ReplyVO> selectList(int bno) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<ReplyVO> replys = sqlSession.selectList("tsms.dao.EnglishReplyDao.selectList", bno);
			return replys;
		} finally {
			sqlSession.close();
		}
	}

	// 영어게시판 댓글 등록
	public Date insert(ReplyVO reply) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.insert("tsms.dao.EnglishReplyDao.insert", reply);
			Date date = sqlSession.selectOne("tsms.dao.EnglishReplyDao.selectDate", reply);
			sqlSession.commit();
			return date;
		} finally {
			sqlSession.close();
		}
	}

	// 영어게시판 글 상세보기
	/*public EnglishBoardVO selectOne(int student_id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("tsms.dao.EnglishBoardDao.selectOne", student_id);
		} finally {
			sqlSession.close();
		}
	}*/

	// 게시글 수정
	/*public int update(EnglishBoardVO english, String english_photo, MultipartRequest multi) throws Exception {
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
	}*/

	// 게시글 삭제
	/*public int delete(int student_id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("tsms.dao.EnglishBoardDao.delete", student_id);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}*/

	//조회수 올려서 DB에 새로 저장
	/*public int updateReadcount(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("tsms.dao.EnglishBoardDao.updateReadcount", paramMap);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}*/
}
