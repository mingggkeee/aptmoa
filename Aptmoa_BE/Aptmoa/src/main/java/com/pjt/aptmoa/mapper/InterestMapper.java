package com.pjt.aptmoa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.aptmoa.dto.Interest;

@Mapper
public interface InterestMapper {

	public List<Interest> selectInterestById(String userId);
	public int insertInterest(Interest interest);
	public int deleteInterest(int no);
	public int checkInterest(String userId, String aptName);
	
}
