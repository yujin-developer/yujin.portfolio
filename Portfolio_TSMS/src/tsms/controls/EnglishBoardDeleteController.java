package tsms.controls;

import java.util.Map;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.EnglishBoardDao;

@Component("/english/delete.do")
public class EnglishBoardDeleteController implements Controller, DataBinding {
	EnglishBoardDao englishBoardDao;

	public EnglishBoardDeleteController setEnglishBoardDao(EnglishBoardDao englishBoardDao) {
		this.englishBoardDao = englishBoardDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "student_id", Integer.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Integer student_id = (Integer) model.get("student_id");
		englishBoardDao.delete(student_id);

		return "redirect:../main/Main.jsp";
	}

}
