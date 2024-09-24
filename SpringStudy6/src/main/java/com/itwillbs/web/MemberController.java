package com.itwillbs.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @RequestMapping(value = "/member/*")
// 	--> 특정 동작의 형태를 구분 (*.me, *.bo, *.do)

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	// 객체 주입
	@Inject
	private MemberDAO mdao;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// http://localhost:8088/web/member/test
	// http://localhost:8088/web/member/test (X) 서버 path 수정 후
	// http://localhost:8088/member/test (O)
	/* @RequestMapping(value = "/test",method = RequestMethod.GET)
	public void test() {
		logger.debug(" ( •̀ ω •́ )✧ test() 실행 ");
	} */
	
	
	// 회원가입 - 정보입력
	// http://localhost:8088/member/join
	@RequestMapping(value = "/join",method = RequestMethod.GET)
	public void joinMemberGet() {
		logger.debug(" ( •̀ ω •́ )✧ /member/join -> joinMemberGet() 실행 ");
		logger.debug(" ( •̀ ω •́ )✧ 연결된 뷰(jsp)를 보여주기 ");
		// 페이지 이동(X) --> 스프링이 자동으로 연결
		logger.debug(" ( •̀ ω •́ )✧ /views/member/join.jsp 뷰페이지 연결 ");
	}
	
	// 회원가입 - 정보처리
	// http://localhost:8088/member/join
	// @RequestMapping(value = "/joinMemberAction")
	@RequestMapping(value = "/join",method = RequestMethod.POST)
	public String joinMemberPost(MemberVO vo) {
		logger.debug(" ( •̀ ω •́ )✧ /member/join -> joinMemberPost() 실행 ");
		// 한글 인코딩 처리
		
		// 전달정보(파라메터) 저장
		logger.debug(" ( •̀ ω •́ )✧ vo : "+vo);
		
		// DB 객체 생성 - 회원가입
		// MemberDAO 객체 생성 --> 객체 주입
		mdao.insertMember(vo);
		logger.debug(" ( •̀ ω •́ )✧ 회원가입 성공! ");
		
		return "";
	}
	
	
	
	
}//class
