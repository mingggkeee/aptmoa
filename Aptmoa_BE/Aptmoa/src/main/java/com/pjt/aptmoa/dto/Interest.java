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
@ApiModel(value = "Interest (관심지역정보)", description = "번호, 작성자아이디, 아파트코드, 동코드를 가진   Domain Class")
public class Interest {
	private int no;
	private String userId;
	private String aptName;
	private long aptCode;
}
