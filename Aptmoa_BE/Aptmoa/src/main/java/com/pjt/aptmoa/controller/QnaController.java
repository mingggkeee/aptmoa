package com.pjt.aptmoa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt.aptmoa.dto.Qna;
import com.pjt.aptmoa.service.QnaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("QnaController V1")
@RestController
@RequestMapping("/qna")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaService qService;
	
	@ApiOperation(value = "qna 글목록", notes = "모든 qna글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Qna>> retrieveBoard() throws Exception {
		logger.debug("retrieveQna - 호출");
		return new ResponseEntity<List<Qna>>(qService.retrieveQna(), HttpStatus.OK);
	}

    @ApiOperation(value = "Qna 글보기", notes = "Qna번호에 해당하는 게시글의 정보를 반환한다.", response = Qna.class)    
	@GetMapping("{qnano}")
	public ResponseEntity<Qna> detailBoard(@PathVariable int qnano) {
		logger.debug("detailNotice - 호출");
		return new ResponseEntity<Qna>(qService.detailQna(qnano), HttpStatus.OK);
	}

    @ApiOperation(value = "Qna 글등록", notes = "새로운 Qna글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Qna qna) {
		logger.debug("writeNotice - 호출");
		if (qService.writeQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "Qna 글정보 수정", notes = "Qna번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{qnano}")
	public ResponseEntity<String> updateBoard(@RequestBody Qna qna) {
		logger.debug("updatNotice - 호출");
		
		if (qService.updateQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "Qna 글삭제", notes = "Qna번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{qnano}")
	public ResponseEntity<String> deleteBoard(@PathVariable int qnano) {
		logger.debug("deleteNotice - 호출");
		if (qService.deleteQna(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
