package com.pjt.aptmoa.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pjt.aptmoa.dto.UserDto;

@Service
public class MailService {
	private final char[] pwdTable={ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private UserService userService;
	
	public String excuteGenerate() {
		Random random = new Random(System.currentTimeMillis());
		int tablelength = pwdTable.length;
		StringBuffer buf = new StringBuffer();
		
		for(int i=0;i<8;i++) {
			buf.append(pwdTable[random.nextInt(tablelength)]);
		}
		
		return buf.toString();
	}
	
	public void sendMail(String userId, String emailAddr) throws Exception{
		
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		UserDto tempDto = userService.getUser(userId);
		
		simpleMessage.setFrom("관리자 <gpfls0506@gmail.com>");
		//수신자 설정
		simpleMessage.setTo(emailAddr);
		
		String tempPwd = excuteGenerate();
		tempDto.setUserPwd(tempPwd);
		userService.updateUser(tempDto);
		
		//메일 제목
		simpleMessage.setSubject("APTMOA 임시 비밀번호를 발급해드립니다.");
		
		//메일 내용
		simpleMessage.setText("임시 비밀번호 : "+tempPwd);
		
		//메일 발송
		javaMailSender.send(simpleMessage);
		
		
	}

}
