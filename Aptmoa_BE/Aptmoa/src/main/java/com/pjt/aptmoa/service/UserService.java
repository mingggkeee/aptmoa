package com.pjt.aptmoa.service;

import java.util.List;

import com.pjt.aptmoa.dto.UserDto;

public interface UserService {

	public UserDto login(UserDto userDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	public int idCheck(String checkId) throws Exception;
	
	public boolean registerUser(UserDto userDto) throws Exception;
	public boolean updateUser(UserDto userDto) throws Exception;
	public boolean deleteUser(String userId) throws Exception;
	public UserDto getUser(String userId) throws Exception;
	public List<UserDto> getUsers() throws Exception;
	


}
