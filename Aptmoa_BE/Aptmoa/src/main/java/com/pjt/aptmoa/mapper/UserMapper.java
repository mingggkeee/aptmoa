package com.pjt.aptmoa.mapper;

import java.sql.SQLException;

import com.pjt.aptmoa.dto.UserDto;


public interface UserMapper {

	public UserDto login(UserDto userDto) throws SQLException;
	public UserDto userInfo(String userid) throws SQLException;
	
}