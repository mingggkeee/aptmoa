package com.pjt.aptmoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.aptmoa.dto.UserDto;
import com.pjt.aptmoa.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper memberMapper;
	
	@Override
	public UserDto login(UserDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

}
