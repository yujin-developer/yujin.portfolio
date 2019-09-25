package tsms.controls;

import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.MailBoxDao;

@Component("/mailbox/trash.do")
public class GoingToTrashMailBoxController implements Controller, DataBinding {
	MailBoxDao mailBoxDao;

	public GoingToTrashMailBoxController setMailBoxDao(MailBoxDao mailBoxDao) {
		this.mailBoxDao = mailBoxDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "idList", String.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// 받은쪽지함에서 휴지통으로 옮기기
		String idList = (String) model.get("idList");
		
		if (idList.contains("all")) {
			idList = idList.substring(4);
		}

		String[] ids = idList.split(",");
		for (int i = 0; i < ids.length; i++) {
			mailBoxDao.goingToTrash(Integer.parseInt(ids[i]));
		}

		return "result:success";
	}

}
