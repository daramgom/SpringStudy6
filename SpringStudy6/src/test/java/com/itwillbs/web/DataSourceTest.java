package com.itwillbs.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *    스프링(root-context.xml)에 있는 DB연결정보(DataSource)객체를 사용해서
 *    DB연결을 테스트
 *
 */

// @RunWith(SpringJUnit4ClassRunner.class) --> 스프링을 사용해서 테스트진행
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)

public class DataSourceTest {
	
	// DB연결정보(직접 작성X)
	// 생성된 객체 정보를 가져오기(의존주입)
	@Inject // root-context.xml에 정보를 찾으러 감
	private DataSource ds;
	// private DataSource ds = new DataSource();
	
	@Test
	public void testDataSource() {
		System.out.println(" ds : "+ds);
	}
	
	@Test
	public void testConnect() {
		try {
			Connection con = ds.getConnection();
			System.out.println(" DB연결성공 ");
			System.out.println(" con : "+con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
