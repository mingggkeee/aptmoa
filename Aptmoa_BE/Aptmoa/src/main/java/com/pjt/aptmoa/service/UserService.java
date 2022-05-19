package com.pjt.aptmoa.service;

import com.pjt.aptmoa.dto.UserDto;

public interface UserService {

	public UserDto login(UserDto memberDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	
}
