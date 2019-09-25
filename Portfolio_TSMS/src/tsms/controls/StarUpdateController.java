package tsms.controls;

import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.MailBoxDao;

@Component("/mailbox/starUpdate.do")
public class StarUpdateController implements Controller, DataBinding {
	MailBoxDao mailBoxDao;

	public StarUpdateController setMailBoxDao(MailBoxDao mailBoxDao) {
		this.mailBoxDao = mailBoxDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "starcheck", String.class, "id", Integer.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String starcheck = (String) model.get("starcheck");
		int id = (int) model.get("id");

		mailBoxDao.updateStar(starcheck, id);
		return "result:success";
	}

}
