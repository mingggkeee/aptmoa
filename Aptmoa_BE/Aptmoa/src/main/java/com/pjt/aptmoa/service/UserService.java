package com.pjt.aptmoa.service;

import com.pjt.aptmoa.dto.UserDto;

public interface UserService {

	public UserDto login(UserDto userDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	
}
