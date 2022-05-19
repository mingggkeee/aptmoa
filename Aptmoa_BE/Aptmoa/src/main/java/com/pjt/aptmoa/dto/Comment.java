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
@ApiModel(value = "Comment (댓글정보)", description = "댓글번호, 문의글번호, 작성자아이디, 내용, 작성일을 가진   Domain Class")
public class Comment {
	private int commentno;
	private int qnano;
	private String userid;
	private String content;
	private String regtime;
}
