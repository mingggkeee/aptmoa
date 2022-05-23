package com.pjt.aptmoa.service;

import java.security.NoSuchAlgorithmException;

import com.pjt.aptmoa.dto.UserDto;

public interface UserService {

	public UserDto login(UserDto userDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	public int idCheck(String checkId) throws Exception;
	
	public boolean registerUser(UserDto userDto) throws Exception;
	public boolean updateUser(UserDto userDto) throws Exception;
	public boolean deleteUser(String userId) throws Exception;
	public UserDto getUser(String userId) throws Exception;
	
//	public String encrypt(String text) throws NoSuchAlgorithmException;
//	boolean setFavorite(String userid, String sido, String gugun);
}
