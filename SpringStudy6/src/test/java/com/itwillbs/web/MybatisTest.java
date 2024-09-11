package com.itwillbs.web;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MybatisTest {
	
	// DB연결(+Mybatis) --> sqlSessionFactory 객체가 필요
	// @Inject
	@Autowired
	private SqlSessionFactory factory;
	
	// @Test
	@Before // @Test 동작 이전에 실행
	public void getBean() {
		System.out.println(" ( •̀ ω •́ )✧ factory : "+factory);
	}
	
	@Test
	public void testConnect() {
		// DB연결
		SqlSession sqlSession = factory.openSession();
		
		System.out.println(" ( •̀ ω •́ )✧ DB연결 성공");
		System.out.println(" ( •̀ ω •́ )✧ sqlSession : "+sqlSession);
		
		// SQL 실행
		// sqlSession.insert(statement);
		
	}
	
	
	
}
