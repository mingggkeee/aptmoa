package com.pjt.aptmoa.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.aptmoa.dto.UserDto;
import com.pjt.aptmoa.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper memberMapper;
	
//	public String encrypt(String text) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(text.getBytes());
//
//        return bytesToHex(md.digest());
//    }
//	
//	private String bytesToHex(byte[] digest) {
//		StringBuilder builder = new StringBuilder();
//        for (byte b : digest) {
//            builder.append(String.format("%02x", b));
//        }
//        return builder.toString();
//	}
	
	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getUserId() == null || userDto.getUserPwd() == null)
			return null;
		return memberMapper.login(userDto);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public int idCheck(String checkId) throws Exception {
		return memberMapper.idCheck(checkId); // 0 or 1
	}

	@Override
	public boolean registerUser(UserDto userDto) throws Exception {
		//validation check
//		System.out.println("userDto.getUserPwd() : "+userDto.getUserpwd());
//		userDto.setUserpwd(encrypt(userDto.getUserpwd()));
		userDto.setUserPwd(userDto.getUserPwd());
//		System.out.println("암호화 userDto.getUserPwd() : "+userDto.getUserpwd());
		return memberMapper.registerUser(userDto)==1;
	}

	@Override
	public boolean updateUser(UserDto userDto) throws Exception {
//		userDto.setUserpwd(encrypt(userDto.getUserpwd()));
		userDto.setUserPwd((userDto.getUserPwd()));
		return memberMapper.updateUser(userDto)==1;
	}

	@Override
	public boolean deleteUser(String userId) throws Exception {
		return memberMapper.deleteUser(userId)==1;
	}

}
