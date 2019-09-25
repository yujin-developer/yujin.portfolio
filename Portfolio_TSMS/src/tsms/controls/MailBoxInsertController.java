package tsms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.MailBoxDao;
import tsms.vo.MailBoxVO;
import tsms.vo.StudentVO;

@Component("/mailbox/insert.do")
public class MailBoxInsertController implements Controller, DataBinding {
	MailBoxDao mailBoxDao;

	public MailBoxInsertController setMailBoxDao(MailBoxDao mailBoxDao) {
		this.mailBoxDao = mailBoxDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "mail", tsms.vo.MailBoxVO.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// 쪽지 보내기
		MailBoxVO mail = (MailBoxVO) model.get("mail");
		mail.setReceiver(mail.getSender());
		HttpSession session = (HttpSession) model.get("session");
		StudentVO student = (StudentVO) session.getAttribute("student");
		mail.setSender(student.getId());

		mailBoxDao.insert(mail);

		return "redirect:../mailbox/receivelist.do";
	}

}
