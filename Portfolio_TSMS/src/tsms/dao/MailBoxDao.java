package tsms.dao;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import tsms.annotation.Component;
import tsms.vo.MailBoxVO;
import tsms.vo.PageVO;

@Component("mailBoxDao")
public class MailBoxDao {
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	// 받은쪽지함 list(페이징, 검색)
	public HashMap<String, Object> selectReceiveList(String go, String gogroup, HashMap<String, Object> paramMap, String id)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PageVO page = new PageVO();
		List<MailBoxDao> receivemails = new ArrayList<MailBoxDao>();
		try {
			if (paramMap.get("search") == null) {
				receivemails = sqlSession.selectList("tsms.dao.MailBoxDao.selectList", id);
			} else {
				paramMap.put("id", id);
				receivemails = sqlSession.selectList("tsms.dao.MailBoxDao.selectListMap", paramMap);
			}

			page = page.methodForPage(go, gogroup, receivemails);

			paramMap.put("receivemails", receivemails);
			paramMap.put("page", page);
			return paramMap;
		} finally {
			sqlSession.close();
		}
	}

	//쪽지함에서 휴지통으로 옮기기
	public int goingToTrash(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.insert("tsms.dao.MailBoxDao.goingToTrash", id);
			int count = sqlSession.delete("tsms.dao.MailBoxDao.deleteMail", id);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	//휴지통 목록 보기
	public HashMap<String, Object> selectTrashList(String go, String gogroup, HashMap<String, Object> paramMap, String id)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PageVO page = new PageVO();
		List<MailBoxDao> trashmails = new ArrayList<MailBoxDao>();
		try {
			if (paramMap.get("search") == null) {
				trashmails = sqlSession.selectList("tsms.dao.MailBoxDao.selectTrashList", id);
			} else {
				paramMap.put("id", id);
				trashmails = sqlSession.selectList("tsms.dao.MailBoxDao.selectTrashListMap", paramMap);
			}

			page = page.methodForPage(go, gogroup, trashmails);

			paramMap.put("trashmails", trashmails);
			paramMap.put("page", page);
			return paramMap;
		} finally {
			sqlSession.close();
		}
	}
	
	//휴지통 완전 삭제
	public int trashDelete(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("tsms.dao.MailBoxDao.trashdelete", id);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	//별표상태 update
	public int updateStar(String starcheck, int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("starcheck", starcheck);
			paramMap.put("id", id);
			int count = sqlSession.update("tsms.dao.MailBoxDao.updateStar", paramMap);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	//쪽지 보낸후 DB에 저장
	public int insert(MailBoxVO mail) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("tsms.dao.MailBoxDao.insert", mail);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	//Read상태 update
	public int updateRead(String readcheck, int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("readcheck", readcheck);
			paramMap.put("id", id);
			int count = sqlSession.update("tsms.dao.MailBoxDao.updateRead", paramMap);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	//별표쪽지함 리스트
	public HashMap<String, Object> selectStarList(String go, String gogroup, HashMap<String, Object> paramMap, String id)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PageVO page = new PageVO();
		List<MailBoxDao> starmails = new ArrayList<MailBoxDao>();
		try {
			if (paramMap.get("search") == null) {
				starmails = sqlSession.selectList("tsms.dao.MailBoxDao.selectStarList", id);
			} else {
				paramMap.put("id", id);
				starmails = sqlSession.selectList("tsms.dao.MailBoxDao.selectStarListMap", paramMap);
			}

			page = page.methodForPage(go, gogroup, starmails);

			paramMap.put("starmails", starmails);
			paramMap.put("page", page);
			return paramMap;
		} finally {
			sqlSession.close();
		}
	}
	
	
	//보낸쪽지함 리스트
	public HashMap<String, Object> selectSendList(String go, String gogroup, HashMap<String, Object> paramMap, String id)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PageVO page = new PageVO();
		List<MailBoxDao> sendmails = new ArrayList<MailBoxDao>();
		try {
			if (paramMap.get("search") == null) {
				sendmails = sqlSession.selectList("tsms.dao.MailBoxDao.selectSendList", id);
			} else {
				paramMap.put("id", id);
				sendmails = sqlSession.selectList("tsms.dao.MailBoxDao.selectSendListMap", paramMap);
			}

			page = page.methodForPage(go, gogroup, sendmails);

			paramMap.put("sendmails", sendmails);
			paramMap.put("page", page);
			return paramMap;
		} finally {
			sqlSession.close();
		}
	}
	
	//휴지통에서 쪽지함으로 복구(이동)
	public int goingToMailbox(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.insert("tsms.dao.MailBoxDao.goingToMailbox", id);
			int count = sqlSession.delete("tsms.dao.MailBoxDao.deleteTrash", id);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
