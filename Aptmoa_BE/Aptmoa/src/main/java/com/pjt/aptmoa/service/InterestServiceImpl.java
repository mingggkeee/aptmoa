package com.pjt.aptmoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.aptmoa.dto.Interest;
import com.pjt.aptmoa.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService{

	@Autowired
	private InterestMapper interestMapper;
	
	@Override
	public List<Interest> retrieveInterest(String userid) {
		return interestMapper.selectInterestById(userid);
	}

	@Override
	public boolean writeInterest(Interest interest) {
		return interestMapper.insertInterest(interest) == 1;
	}

	@Override
	public boolean deleteInterest(int no) {
		return interestMapper.deleteInterest(no) == 1;
	}

}
