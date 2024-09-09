package com.itwillbs.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MysqlConnectTest {
	
	// DB연결정보
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/springdb";
	private static String DBID = "root";
	private static String DBPW = "1234";
	
	@Test // junit --> 4.12 버전
	public void dbConnectTest() {
		
		/*
		try {
			// DB연결
			// 1. 드라이버 로드 Class.forName(DRIVER);
			System.out.println(" 드라이버 로드 성공 / Class.forName() ");
			// 2. DB연결
			DriverManager.getConnection(URL, DBID, DBPW);
			System.out.println(" DB연결 성공 / DriverManager.getConnection() ");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		*/
		
		try {
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			System.out.println( " 드라이버 로드 성공 / Class.forName() " );
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// try-with 구문 : try() 괄호 안에 있는 객체의 자원을 자동으로 해제
		//    JDK 1.7 이상 사용   (AutoClosable 인터페이스를 상속한 객체만)
		try (Connection con = DriverManager.getConnection(URL,DBID,DBPW);) {
			// DB연결
			// 2. DB연결
			
			System.out.println(" DB연결 성공 / DriverManager.getConnection() ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
