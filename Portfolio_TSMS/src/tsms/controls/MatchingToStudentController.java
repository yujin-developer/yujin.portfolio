package tsms.controls;

import java.util.Map;
import java.util.Properties;

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
import tsms.dao.StudentDao;
import tsms.vo.MailVO;
import tsms.vo.TeacherVO;

@Component("/student/sendMail.do")
public class MatchingToStudentController implements Controller, DataBinding {
	StudentDao studentDao;

	public MatchingToStudentController setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
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
		String email = studentDao.selectOne(id).getEmail();
		HttpSession sessionLogin = (HttpSession) model.get("session");
		TeacherVO teacher = (TeacherVO) sessionLogin.getAttribute("teacher");
		BodyPart bodyPart = new MimeBodyPart();
		Multipart multi = new MimeMultipart();

		String str = "";
		str += "<table>";
		str += "	<tr><td><img width=100 height=100 src='http://localhost:8088/Portfolio_TSMS/upload/"+teacher.getPhoto()+"'></td></tr>";
		str += "	<tr><th>이름</th><td>" + teacher.getName() + "</td></tr>";
		str += "	<tr><th>나이</th><td>" + teacher.getAge() + "</td></tr>";
		str += "	<tr><th>성별</th><td>" + teacher.getGender() + "</td></tr>";
		str += "	<tr><th>주소</th><td>" + teacher.getAddress1() + "</td></tr>";
		str += "	<tr><th>과외과목</th><td>";
		str += (teacher.getSubject().equals("무관")) ? "수학 , 영어" : "";
		str += (teacher.getSubject().equals("수학")) ? "수학 " : "";
		str += (teacher.getSubject().equals("영어")) ? "영어" : "";
		str += "	</td></tr>";
		str += "	<tr>";
		str += "		<th>과외학년</th>";
		str += "		<td>";
		str += (teacher.getGrade().contains("all")) ? "전 학년 " : "";
		str += (teacher.getGrade().contains("e")) ? "초 " : "";
		str += (teacher.getGrade().contains("m1")) ? "중1 " : "";
		str += (teacher.getGrade().contains("m2")) ? "중2 " : "";
		str += (teacher.getGrade().contains("m3")) ? "중3 " : "";
		str += (teacher.getGrade().contains("h1")) ? "고1 " : "";
		str += (teacher.getGrade().contains("h2")) ? "고2 " : "";
		str += (teacher.getGrade().contains("h3")) ? "고3 " : "";
		str += "		</td>";
		str += "	</tr>";
		str += "	<tr>";
		str += "		<th>과외가능요일</th>";
		str += "		<td>";
		str += (teacher.getDay().equals("all")) ? "월 화 수 목 금 토 일" : "";
		str += (teacher.getDay().contains("월")) ? "월 " : "";
		str += (teacher.getDay().contains("화")) ? "화 " : "";
		str += (teacher.getDay().contains("수")) ? "수 " : "";
		str += (teacher.getDay().contains("목")) ? "목 " : "";
		str += (teacher.getDay().contains("금")) ? "금 " : "";
		str += (teacher.getDay().contains("토")) ? "토 " : "";
		str += (teacher.getDay().contains("일")) ? "일 " : "";
		str += "		</td>";
		str += "	</tr>";
		str += "	<tr><th>과외가능시간</th><td>" + teacher.getTime() + "</td></tr>";
		str += "	<tr><th>자기소개</th><td>" + teacher.getIntroduce() + "</td></tr>";
		str += "<h2>선생님으로부터 매칭 시도가 도착했습니다.</h2>";
		str += "<h2><a href='http://localhost:8088/Portfolio_TSMS/teacher/info.do?id="+teacher.getId()+"'>TSMS바로가기</a></h2>";
		str += "</table>";

		bodyPart.setContent(str, "text/html; charset=UTF-8");
		multi.addBodyPart(bodyPart);

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465); // 네이버는 587,구글은 465 - smtp서버와 통신하는 포트
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.getUser(), mail.getPassword());
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getUser(), "TSMS 관리자", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("TSMS 쪽지가 도착했습니다.", "UTF-8");
			// message.setText(str);
			message.setContent(multi, "text/html; charset=UTF-8");
			Transport.send(message);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return "result:success";
	}

}
