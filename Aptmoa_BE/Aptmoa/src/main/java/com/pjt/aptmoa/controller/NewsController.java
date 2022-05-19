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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt.aptmoa.dto.News;
import com.pjt.aptmoa.service.NewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("NewsController V1")
@RestController
@RequestMapping("/news")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NewsService nService;
	
	@ApiOperation(value = "news 목록", notes = "모든 news를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<News>> retrieveNews() throws Exception {
		logger.debug("retrieveNews - 호출");
		return new ResponseEntity<List<News>>(nService.retrieveNews(), HttpStatus.OK);
	}

    @ApiOperation(value = "news 글보기", notes = "news번호에 해당하는 news의 정보를 반환한다.", response = News.class)    
	@GetMapping("{newsno}")
	public ResponseEntity<News> detailNews(@PathVariable int newsno) {
		logger.debug("detailNews - 호출");
		return new ResponseEntity<News>(nService.detailNews(newsno), HttpStatus.OK);
	}

    @ApiOperation(value = "News 등록", notes = "새로운 News 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNews(@RequestBody News news) {
		logger.debug("writeNews - 호출");
		if (nService.writeNews(news)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "News 삭제", notes = "News번호에 해당하는 News의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{newsno}")
	public ResponseEntity<String> deleteNews(@PathVariable int newsno) {
		logger.debug("deleteNews - 호출");
		if (nService.deleteNews(newsno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	
}
