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
@ApiModel(value = "News (뉴스정보)", description = "글번호, 제목, 내용, 작성일을 가진   Domain Class")
public class News {

	private int newsno;
	private String subject;
	private String content;
	private String regtime;
	
}
