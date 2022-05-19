package com.pjt.aptmoa.service;

import java.util.List;

import com.pjt.aptmoa.dto.Interest;

public interface InterestService {
	public List<Interest> retrieveInterest(String userid);
	public boolean writeInterest(Interest interest);
	public boolean deleteInterest(int no);
}
