package tsms.controls;

import java.util.HashMap;
import java.util.Map;

import com.oreilly.servlet.MultipartRequest;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.EnglishBoardDao;
import tsms.dao.EnglishReplyDao;
import tsms.vo.EnglishBoardVO;

@Component("/english/update.do")
public class EnglishBoardUpdateController implements Controller, DataBinding {
	EnglishBoardDao englishBoardDao;
	public EnglishBoardUpdateController setEnglishBoardDao(EnglishBoardDao englishBoardDao) {
		this.englishBoardDao = englishBoardDao;
		return this;
	}

	EnglishReplyDao englishReplyDao;
	public EnglishBoardUpdateController setEnglishReplyDao(EnglishReplyDao englishReplyDao) {
		this.englishReplyDao = englishReplyDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] { "bno", Integer.class, "english", EnglishBoardVO.class, "english_photo",
				String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		EnglishBoardVO english = (EnglishBoardVO) model.get("english");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		int bno = (int) model.get("bno");
		String english_photo = (String) model.get("english_photo");

		if (english.getSubject() == null) { // 게시글 상세보기
			english = englishBoardDao.selectOne(bno);
			paramMap.put("readcount", english.getReadcount() + 1);
			paramMap.put("bno", bno);

			int result = englishBoardDao.updateReadcount(paramMap); // 조회수 update
			if (result > 0) {
				model.put("english", english);
			}
			model.put("replys", englishReplyDao.selectList(bno));
			return "/english/EnglishBoardInfo.jsp";
		} else { // DB에 수정
			MultipartRequest multi = (MultipartRequest) model.get("multi");
			englishBoardDao.update(english, english_photo, multi);

			return "redirect:/english/EnglishBoardInfo.jsp";
		}

	}

}
