package com.pjt.aptmoa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HouseInfoDto {
	private long aptCode;
	private String apartmentName;
	private String dongCode;
	private String dong;
	private String sidoName;
	private String gugunName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String dealAmount;
	private String floor;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String area;
	private int dealMonth;
	private int dealDay;
}
