package com.pjt.aptmoa.mapper;

import java.sql.SQLException;
import java.util.List;

import com.pjt.aptmoa.dto.UserDto;


public interface UserMapper {
	public List<UserDto> getUsers() throws SQLException;
	public UserDto login(UserDto userDto) throws SQLException;
	public UserDto userInfo(String userid) throws SQLException;
	public int idCheck(String checkId) throws Exception;
	
	public int registerUser(UserDto userDto) throws Exception;
	public int updateUser(UserDto userDto) throws Exception;
	public int deleteUser(String userId) throws Exception;
	public int checkIdName(UserDto userDto) throws Exception;
	public UserDto getUser(String userId) throws Exception;
	
}