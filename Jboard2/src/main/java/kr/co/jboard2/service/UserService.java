package kr.co.jboard2.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.UserDAO;
import kr.co.jboard2.dto.UserDTO;

public class UserService {
	private UserDAO dao = new UserDAO();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static String generatedCode;

	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}

	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}

	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}

	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}

	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}

	public int sendCodeByEmail(String receiver) {
		// 인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		generatedCode = code + "";
		// 기본정보
		String sender = "slothfkdla@gmail.com";
		String password = "yqisfhwfkdhdmret"; // 앱 비밀번호
		String title = "Jboard2 인증코드 입니다.";
		String content = "<h1>인증코드는" + code + "</h1>";

		// GMail SMTP Server 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// EMail SMTP 세션 생성
		Session gmailSession = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});

		// 메일 발송
		Message message = new MimeMessage(gmailSession);
		try {
			message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
		} catch (Exception e) {
			logger.error("sendCodeByEmail error : " + e.getMessage());
		}
		logger.info("code : " + code);
		return code;
	}

	public int findUserCountByNameAndEmail(String name, String email) {
		return dao.findUserCountByNameAndEmail(name, email);
	};
	
	public UserDTO findUserByNameAndEmail(String name, String email) {
		return dao.findUserByNameAndEmail(name, email);
	};

	public int checkCode(String code) {
		logger.info("code : " + code);
		logger.info("generatedCode : " + generatedCode);
		if (generatedCode.equals(code)) {
			return 1;
		}
		return 0;
	}
}