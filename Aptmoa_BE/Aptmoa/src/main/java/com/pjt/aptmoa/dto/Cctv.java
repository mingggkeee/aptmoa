package com.pjt.aptmoa.dto;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "CCTV (cctv정보)", description = "cctv번호, 주소, cctv개수, 위도, 경도를 가진   Domain Class")
public class Cctv {

	private int no;
	private String address;
	private int camnum;
	private String lat;
	private String lng;
	
}
