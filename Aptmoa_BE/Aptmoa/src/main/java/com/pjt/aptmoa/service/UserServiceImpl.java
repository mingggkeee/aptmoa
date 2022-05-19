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
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getUserid() == null || userDto.getUserpwd() == null)
			return null;
		return memberMapper.login(userDto);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

}
