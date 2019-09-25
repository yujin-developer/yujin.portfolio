package tsms.controls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.EnglishReplyDao;
import tsms.vo.ReplyVO;
import tsms.vo.StudentVO;

@Component("/english/reply/insert.do")
public class ReplyInsertController implements Controller, DataBinding {
	EnglishReplyDao englishReplyDao;

	public ReplyInsertController setEnglishReplyDao(EnglishReplyDao englishReplyDao) {
		this.englishReplyDao = englishReplyDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "content", String.class, "bno", Integer.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String content = (String) model.get("content");
		int bno = (int) model.get("bno");
		HttpSession session = (HttpSession) model.get("session");
		StudentVO student = (StudentVO)session.getAttribute("student");
		String replyer = student.getId();
		
		ReplyVO reply = new ReplyVO().setBno(bno).setContent(content).setReplyer(replyer);
		Date date = new Date(englishReplyDao.insert(reply).getTime());
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
		String replydateString = format.format(date);
		
		return "result:"+replydateString;
	}

}
