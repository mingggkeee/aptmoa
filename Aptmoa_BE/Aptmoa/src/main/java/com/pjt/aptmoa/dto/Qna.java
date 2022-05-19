package com.pjt.aptmoa.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Qna (문의사항정보)", description = "글번호, 제목, 내용, 작성자아이디, 작성일을 가진   Domain Class")
public class Qna {
	@ApiModelProperty(value = "글번호")
	private int qnano;
	@ApiModelProperty(value = "작성자아이디")
	private String userid;
	@ApiModelProperty(value = "제목")
	private String subject;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "조회수")
	private int hit;
	@ApiModelProperty(value = "작성일")
	private String regtime;

}
