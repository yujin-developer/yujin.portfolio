package tsms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.EnglishBoardDao;
import tsms.vo.EnglishBoardVO;
import tsms.vo.StudentVO;

@Component("/english/insert.do")
public class EnglishBoardInsertController implements Controller, DataBinding {
	EnglishBoardDao englishBoardDao;

	public EnglishBoardInsertController setEnglishBoardDao(EnglishBoardDao englishBoardDao) {
		this.englishBoardDao = englishBoardDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "english", tsms.vo.EnglishBoardVO.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		EnglishBoardVO english = (EnglishBoardVO) model.get("english");
		if (english.getSubject() == null) {
			return "/english/EnglishBoardForm.jsp";
		} else {
			MultipartRequest multi = (MultipartRequest) model.get("multi");
			HttpSession session = (HttpSession) model.get("session");
			StudentVO student = (StudentVO) session.getAttribute("student");
			english.setStudent_id(student.getStudent_id());
			englishBoardDao.insert(english, multi);
			return "redirect:list.do";
		}

	}

}
