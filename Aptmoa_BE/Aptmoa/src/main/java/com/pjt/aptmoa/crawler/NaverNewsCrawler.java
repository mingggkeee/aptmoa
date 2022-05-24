package com.pjt.aptmoa.crawler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pjt.aptmoa.util.DBUtil;

public class NaverNewsCrawler {
	
	private static DBUtil dbUtil = DBUtil.getInstance();
	
	public static void insert(String subject, String content, String newsurl, String imgurl, String regtime) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerNews = new StringBuilder();
			registerNews.append("insert into news (subject, content, newsurl, imgurl, regtime) \n");
			registerNews.append("values (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(registerNews.toString());
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, newsurl);
			pstmt.setString(4, imgurl);
			pstmt.setString(5, regtime);
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt,conn);
		}
		
	}
	
	public static void crawling(String regtime) throws IOException {
		int pages = 5;
		
		for(int page=1; page<=pages; page++) {
			
			String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid2=260&sid1=101&date="+ regtime +"&page=" + page;
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByAttributeValue("class", "photo");
			
			Element element = elements.get(0);
			Elements photoElements = element.getElementsByAttributeValue("class", "photo");
			
			for(int i=0; i<photoElements.size(); i++) {
				Element newsElement = photoElements.get(i);
				Elements aElements = newsElement.select("a");
				Element aElement = aElements.get(0);
				
				String newsurl = aElement.attr("href");		// 뉴스 링크
				
				Element imgElement = aElement.select("img").get(0);
				String imgurl = imgElement.attr("src");		// 사진 링크
				String subject = imgElement.attr("alt");	// 뉴스 제목
				
				Document subDoc = Jsoup.connect(newsurl).get();
				Element contentElement = subDoc.getElementById("newsct_article");
				String content = contentElement.text();		// 기사 내용
				
				insert(subject, content, newsurl, imgurl, regtime);
			}
			
			element = elements.get(2);
			photoElements = element.getElementsByAttributeValue("class", "photo");
			
			for(int i=0; i<photoElements.size(); i++) {
				Element newsElement = photoElements.get(i);
				Elements aElements = newsElement.select("a");
				Element aElement = aElements.get(0);
				
				String newsurl = aElement.attr("href");		// 뉴스 링크
				
				Element imgElement = aElement.select("img").get(0);
				String imgurl = imgElement.attr("src");		// 사진 링크
				String subject = imgElement.attr("alt");	// 뉴스 제목
				
				Document subDoc = Jsoup.connect(newsurl).get();
				Element contentElement = subDoc.getElementById("newsct_article");
				String content = contentElement.text();		// 기사 내용
				
				insert(subject, content, newsurl, imgurl, regtime);
			}
			
			System.out.println(page+" page 크롤링 완료.");
			
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		
		crawling("20220522");
		
	}
}
