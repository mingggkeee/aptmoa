package com.pjt.aptmoa.algo;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class EmergencyAlgo {
	
	public String getInfo() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
		/*URL*/
		
		urlBuilder.append("/" + URLEncoder.encode("6c6a474e666d6b733730566d457853","UTF-8") ); /*인증키
		(sample사용시에는 호출시 제한됩니다.)*/
		
		urlBuilder.append("/" + URLEncoder.encode("xml","UTF-8") ); /*요청파일타입
		(xml,xmlf,xls,json) */
		
		urlBuilder.append("/" + URLEncoder.encode("TvEmgcHospitalInfo","UTF-8"));
		/*서비스명 (대소문자 구분 필수입니다.)*/
		
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치
		(sample인증키 사용시 5이내 숫자)*/
		
		urlBuilder.append("/" + URLEncoder.encode("62","UTF-8"));
		/*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결
		자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;
		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
		rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			// System.out.println(line);
			if(line.length() > 8 && line.substring(1, 9).equals("DUTYNAME")) {
				sb.append(line.substring(10, line.length()-11)+"\n");
			} else if(line.length() > 8 && line.substring(1, 9).equals("WGS84LON")) {
				sb.append(line.substring(10, line.length()-11)+"\n");
			} else if(line.length() > 8 && line.substring(1, 9).equals("WGS84LAT")) {
				sb.append(line.substring(10, line.length()-11)+"\n");
			} else if(line.length() > 8 && line.substring(1, 9).equals("DUTYADDR")) {
				sb.append(line.substring(10, line.length()-11)+"\n");
			}
		}
		rd.close();
		conn.disconnect();
		
		// System.out.println(sb.toString());
		
		return sb.toString();
	}

	public String[] search(double lat, double lng) throws IOException {
		String a = getInfo();
		String[] answer = new String[6];
		Double compare = Double.MAX_VALUE;
		
		String[] splits = a.split("\n");
		String[][] infos = new String[62][4];
		int i = 0;
		int j = 0;
		
		for(String sp : splits) {
			infos[i][j++] = sp;
			if(j==4) {
				j = 0;
				i++;
			}
		}
		
		for(int r=0; r<62; r++) {
			String name = infos[r][1];
			double comLat = Double.parseDouble(infos[r][2]);
			double comLng = Double.parseDouble(infos[r][3]);
			
			double distance = distance(lat, lng, comLng, comLat, "meter");
			
			if(distance < compare) {
				answer[0] = name;
				answer[1] = infos[r][2];
				answer[2] = infos[r][3];
				answer[3] = infos[r][0];
				compare = distance;
				answer[4] = Double.toString(Math.round(distance));
			}
		}
		
		return answer;
	}
	
    /**
     * 두 지점간의 거리 계산
     *
     * @param lat1 지점 1 위도
     * @param lon1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lon2 지점 2 경도
     * @param unit 거리 표출단위
     * @return
     */
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
         
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
         
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
         
        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }
 
        return (dist);
    }
     
 
    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
     
    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
