package tsms.controls;

import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.MailBoxDao;

@Component("/mailbox/readUpdate.do")
public class ReadUpdateController implements Controller, DataBinding {
	MailBoxDao mailBoxDao;

	public ReadUpdateController setMailBoxDao(MailBoxDao mailBoxDao) {
		this.mailBoxDao = mailBoxDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "readcheck", String.class, "id", Integer.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String readcheck = (String) model.get("readcheck");
		int id = (int) model.get("id");

		mailBoxDao.updateRead(readcheck, id);
		return "result:success";
	}

}
