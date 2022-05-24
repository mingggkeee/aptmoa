package com.pjt.aptmoa.service;

import java.util.List;

import com.pjt.aptmoa.dto.HouseInfoDto;
import com.pjt.aptmoa.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptInSearch(String apartName, String sido) throws Exception;
	List<HouseInfoDto> getAptInfo(String aptCode) throws Exception;
}
