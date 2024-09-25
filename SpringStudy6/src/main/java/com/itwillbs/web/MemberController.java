package com.itwillbs.web;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.service.MemberService;

// @RequestMapping(value = "/member/*")
// 	--> 특정 동작의 형태를 구분 (*.me, *.bo, *.do)

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	// 객체 주입
	// @Inject
	// private MemberDAO mdao;
	
	// @Inject
	private MemberService mService;
	
	@Autowired
	public MemberController(MemberService mService) {
		this.mService = mService;
	}
	
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
		// 	--> web.xml filter 처리
		
		// 전달정보(파라메터) 저장
		logger.debug(" ( •̀ ω •́ )✧ vo : "+vo);
		
		// DB 객체 생성 - 회원가입
		// MemberDAO 객체 생성 --> 객체 주입
		// mdao.insertMember(vo);
		
		// MemberService 객체를 주입 -> 해당동작 수행
		mService.memberJoin(vo);
		logger.debug(" ( •̀ ω •́ )✧ 회원가입 성공! ");
		logger.debug(" ( •̀ ω •́ )✧ 로그인 페이지로 이동 /member/login ");
		
		return "redirect:/member/login";
	}
	
	
	// 로그인 처리 - 입력(GET)
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String loginMemberGET() {
		logger.debug(" ( •̀ ω •́ )✧ /member/login -> loginMemberGET() 실행 ");
		logger.debug(" ( •̀ ω •́ )✧ 연결된 뷰(jsp)페이지 출력 ");
		// return "./loginForm"; // --> /views/loginForm.jsp
		return "/member/loginForm"; // --> /views/member/loginForm.jsp
	}
	
	
	// 로그인 처리 - 처리(POST)
// public String loginMemberPOST(@RequestParam("userid") String userid,
// 							     @ModelAttribute("userpw") String userpw) {
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String loginMemberPOST(MemberVO vo, HttpSession session) { // 모두 bold 인걸로 선택
		logger.debug(" ( •̀ ω •́ )✧ /member/login(post) -> loginMemberPOST() 실행 ");
		
		// 전달정보를 저장(userid,userpw)
		logger.debug(" ( •̀ ω •́ )✧ vo : "+vo);
		// logger.debug(" ( •̀ ω •́ )✧ id : "+userid);
		// logger.debug(" ( •̀ ω •́ )✧ pw : "+userpw);
		
		// 서비스 -> 회원정보 확인 -> DAO 호출
		MemberVO resultVO = mService.memberLoginCheck(vo);
		if(resultVO == null) {
			// 로그인 실패 - 로그인 페이지로 이동
			return "redirect:/member/login";
		}
		
		// 로그인 성공
		
		// 사용자의 아이디 정보를 세션 영역에 저장
		// session.setAttribute (X)
		session.setAttribute("id", resultVO.getUserid());
		
		// 메인 페이지로 이동
		
		return "redirect:/member/main";
		
	}
	
}//class controller
