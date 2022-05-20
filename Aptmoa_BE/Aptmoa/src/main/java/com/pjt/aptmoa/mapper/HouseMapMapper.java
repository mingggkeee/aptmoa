package com.pjt.aptmoa.mapper;

import java.sql.SQLException;
import java.util.List;

import com.pjt.aptmoa.dto.HouseInfoDto;
import com.pjt.aptmoa.dto.SidoGugunCodeDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptInSearch(String apartName) throws SQLException;
}
