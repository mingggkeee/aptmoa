package com.pjt.aptmoa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pjt.aptmoa.dto.Interest;
import com.pjt.aptmoa.service.InterestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("")
@RestController
@RequestMapping("/interest")
@CrossOrigin("*")
public class InterestController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private InterestService iService;

    @ApiOperation(value = "관심 목록", notes = "모든 관심목록를 반환한다.", response = List.class)    
	@GetMapping("{userid}")
	public ResponseEntity<List<Interest>> retrieveInterest(@PathVariable String userid) {
		logger.debug("retrieveInterest - 호출");
		return new ResponseEntity<List<Interest>>(iService.retrieveInterest(userid), HttpStatus.OK);
	}

    @ApiOperation(value = "관심 등록", notes = "새로운 관심 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNews(@RequestBody Interest Interest) {
		logger.debug("writeNews - 호출");
		if (iService.writeInterest(Interest)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "관심 삭제", notes = "번호에 해당하는 관심의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteInterest(@PathVariable int no) {
		logger.debug("deleteInterest - 호출");
		if (iService.deleteInterest(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
    @ApiOperation(value = "중복체크", notes = "중복체크")
    @GetMapping("/dupcheck")
	public ResponseEntity<String> checkInterest(@RequestParam String userId, @RequestParam String aptName, @RequestParam String dong) {
		logger.debug("checkInterest - 호출");
		int cnt = iService.checkInterest(userId, aptName, dong);
		if (cnt==0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		else if(cnt==1) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
