package com.util;

import java.util.Properties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
public class EmailUtil {

	public static String username = "bysj20@qq.com"; // 邮箱,开通POP3
	public static String pwd = "elmezysacvjsd"; // 授权码，不是登录密码

	public static JavaMailSenderImpl javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.qq.com");
		mailSender.setUsername(username);
		mailSender.setPassword(pwd);
		mailSender.setPort(465);
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.timeout", 25000);
		mailSender.setJavaMailProperties(properties);
		return mailSender;
	}

	public static void sendEmail(String email, String info) {
		SimpleMailMessage message = new SimpleMailMessage();// 消息构造器
		message.setFrom(username);// 发件人
		message.setTo(email);// 收件人
		message.setSubject("用户警告提醒");// 主题

		StringBuffer content = new StringBuffer();
		content.append(info);
		message.setText(content.toString());// 正文
		EmailUtil.javaMailSender().send(message);
	}
	
	public static void main(String[] args) {
	 
	}

}
