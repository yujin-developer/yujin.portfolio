package tsms.controls;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import tsms.annotation.Component;
import tsms.bind.DataBinding;
import tsms.dao.TeacherDao;
import tsms.vo.MailVO;
import tsms.vo.StudentVO;

@Component("/teacher/sendMail.do")
public class MatchingToTeacherController implements Controller, DataBinding {
	TeacherDao teacherDao;

	public MatchingToTeacherController setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "id", String.class, "mail", tsms.vo.MailVO.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// 발신자의 메일이 gmail일 경우 메일 발신
		String id = (String) model.get("id");
		MailVO mail = (MailVO) model.get("mail");
		String email = teacherDao.selectOne(id).getEmail();
		HttpSession sessionLogin = (HttpSession) model.get("session");
		StudentVO student = (StudentVO) sessionLogin.getAttribute("student");
		BodyPart bodyPart = new MimeBodyPart();
		Multipart multi = new MimeMultipart();

		String str = "";
		str += "<table'>";
		str += "	<tr><th>이름</th><td>" + student.getName() + "</td></tr>";
		str += "	<tr><th>나이</th><td>" + student.getAge() + "</td></tr>";
		str += "	<tr><th>성별</th><td>" + student.getGender() + "</td></tr>";
		str += "	<tr><th>주소</th><td>" + student.getAddress() + "</td></tr>";
		str += "	<tr><th>과외과목</th><td>";
		str += (student.getSubject().equals("무관")) ? "수학 , 영어" : "";
		str += (student.getSubject().equals("수학")) ? "수학 " : "";
		str += (student.getSubject().equals("영어")) ? "영어" : "";
		str += "	</td></tr>";
		str += "	<tr>";
		str += "		<th>과외학년</th>";
		str += "		<td>";
		str += (student.getGrade().contains("e")) ? "초 " : "";
		str += (student.getGrade().contains("m1")) ? "중1 " : "";
		str += (student.getGrade().contains("m2")) ? "중2 " : "";
		str += (student.getGrade().contains("m3")) ? "중3 " : "";
		str += (student.getGrade().contains("h1")) ? "고1 " : "";
		str += (student.getGrade().contains("h2")) ? "고2 " : "";
		str += (student.getGrade().contains("h3")) ? "고3 " : "";
		str += "		</td>";
		str += "	</tr>";
		str += "	<tr>";
		str += "		<th>과외가능요일</th>";
		str += "		<td>";
		str += (student.getDay().equals("all")) ? "월 화 수 목 금 토 일" : "";
		str += (student.getDay().contains("월")) ? "월 " : "";
		str += (student.getDay().contains("화")) ? "화 " : "";
		str += (student.getDay().contains("수")) ? "수 " : "";
		str += (student.getDay().contains("목")) ? "목 " : "";
		str += (student.getDay().contains("금")) ? "금 " : "";
		str += (student.getDay().contains("토")) ? "토 " : "";
		str += (student.getDay().contains("일")) ? "일 " : "";
		str += "		</td>";
		str += "	</tr>";
		str += "	<tr><th>과외가능시간</th><td>" + student.getTime() + "</td></tr>";
		str += "	<tr><th>자기소개</th><td>" + student.getIntroduce() + "</td></tr>";
		str += "<h2>학생으로부터 매칭 쪽지가 도착했습니다.</h2>";
		str += "<h2><a href='http://localhost:8088/Portfolio_TSMS/'>TSMS바로가기</a></h2>";
		str += "</table>";

		bodyPart.setContent(str, "text/html; charset=UTF-8");
		multi.addBodyPart(bodyPart);

		Properties prop = new Properties();
		/*prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465); // 네이버는 587,구글은 465 - smtp서버와 통신하는 포트
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		*/
		
		
		/*Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.getUser(), mail.getPassword());
			}
		});*/

		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "false");
		/*prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.ssl.enable","false");
        prop.put("mail.smtp.starttls.enable","false");*/
       /* prop.put("mail.smtp.connectiontimeout", "3000");
    	prop.put("mail.smtp.timeout", "4000");*/
		
		/*Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(null, null);
			}
		};*/
		Session session = Session.getInstance(prop);
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getUser(), "TSMS 관리자", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("TSMS 쪽지가 도착했습니다.", "UTF-8");
			// message.setText(str);
			message.setContent(multi, "text/html; charset=UTF-8");
			Transport.send(message);
			
			
			/*Transport transport = session.getTransport("smtp");
			
		    transport.connect();
		    transport.sendMessage(message, message.getAllRecipients());
		    transport.close();*/
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return "redirect:info.do?id=" + id;
	}

}
