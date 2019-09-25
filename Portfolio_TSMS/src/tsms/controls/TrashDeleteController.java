package tsms.controls;

import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.MailBoxDao;

@Component("/mailbox/trashDelete.do")
public class TrashDeleteController implements Controller, DataBinding {
	MailBoxDao mailBoxDao;

	public TrashDeleteController setMailBoxDao(MailBoxDao mailBoxDao) {
		this.mailBoxDao = mailBoxDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "idList", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String idList = (String) model.get("idList");
		if (idList.contains("allDelete")) {
			idList = idList.substring(10);
		}
		String[] ids = idList.split(",");
		for (int i = 0; i < ids.length; i++) {
			mailBoxDao.trashDelete(Integer.parseInt(ids[i]));
		}

		return "result:success";
	}

}
