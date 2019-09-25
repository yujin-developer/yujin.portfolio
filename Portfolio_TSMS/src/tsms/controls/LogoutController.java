package tsms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import tsms.annotation.Component;

@Component("/auth/logout.do")
public class LogoutController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		session.invalidate();
		if (model.get("updateIdPwd") != null) {
			model.put("reLogin", "reLogin");
		}
		
		return "redirect:login.do";
	}

}
