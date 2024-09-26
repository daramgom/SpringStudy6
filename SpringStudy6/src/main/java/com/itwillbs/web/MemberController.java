package com.itwillbs.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	// http://localhost:8088/member/login
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
	
	
	// 메인페이지 - 화면만 보여줌 --> GET
	// http://localhost:8088/member/main
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public void mainMemberGET() {
		logger.debug(" ( •̀ ω •́ )✧ /member/main --> mainMemberGET() 실행 ");
		logger.debug(" ( •̀ ω •́ )✧ 연결된 뷰페이지 (views/member/main.jsp)로 이동 ");
		
	}
	
	
	// 로그아웃 - GET(정보입력,조회,출력)/POST(처리-insert,update,delete...)
	// http://localhost:8088/member/logout
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logoutMemberGET(HttpSession session) {
		logger.debug(" ( •̀ ω •́ )✧ /member/logout -> logoutMemberGET() 실행 ");
		
		// 로그아웃 처리 --> 세션정보 초기화
		logger.debug(" ( •̀ ω •́ )✧ 사용자 정보 로그아웃 ");
		session.invalidate();
		
		// 페이지 이동
		return "redirect:/member/login";
	}
	
	
	// 회원정보 조회 - GET(조회)
	// http://localhost:8088/member/info
	// @RequestMapping(value = "/info",method = RequestMethod.GET)
	@GetMapping(value = "/info")
	public void infoMemberGET(Model model,HttpSession session) {
		logger.debug(" ( •̀ ω •́ )✧ /member/info -> infoMemberGET() 실행 ");
		
		// 아이디 정보가 필요함
		String id = (String) session.getAttribute("id"); // Object 타입 --> String 타입으로 다운캐스팅
		logger.debug(" ( •̀ ω •́ )✧ 아이디 : "+id);
		
		// 서비스 -> DAO : 특정 아이디를 사용해서 회원의 정보를 조회
		MemberVO resultVO = mService.memberInfo(id);
		logger.debug(" ( •̀ ω •́ )✧ vo : "+resultVO);
		
		// 서비스에서 가져온 데이터를 연결된 뷰페이지에 전달해서 출력
		model.addAttribute(resultVO); // --> memberVO
		// -> 이름이 없을 경우 첫글자를 소문자로 바꾼 클래스명을 이름으로 사용
		
		logger.debug(" ( •̀ ω •́ )✧ 연결된 뷰페이지로 이동 /views/member/info.jsp ");
		
	}
	
	
	// 회원정보 수정 - GET(정보입력)
	// @RequestMapping(value = "/update",method = RequestMethod.GET)
	@GetMapping(value = "/update")
	public String updateMemberGET(Model model, HttpSession session) {
		logger.debug(" ( •̀ ω •́ )✧ /member/update -> updateMemberGET() 실행 ");
		
		// 아이디 정보가 필요함
		String id = (String) session.getAttribute("id"); // Object 타입 --> String 타입으로 다운캐스팅
		logger.debug(" ( •̀ ω •́ )✧ 아이디 : "+id);
		
		// 서비스 -> DAO : 회원정보 가져오기
		// MemberVO resultVO = mService.memberInfo(id);

		
		// 서비스에서 가져온 데이터를 연결된 뷰페이지에 전달해서 출력
		// -> 이름이 없을 경우 첫글자를 소문자로 바꾼 클래스명을 이름으로 사용
		// model.addAttribute(resultVO);
		model.addAttribute(mService.memberInfo(id));
		logger.debug(" ( •̀ ω •́ )✧ 연결된 뷰페이지 출력(/views/member/update.jsp) ");
		
		return "/member/update";
		
	}

	// 회원정보 수정 - POST(처리)
	@PostMapping(value = "/update")
	public String updateMemberPOST(MemberVO vo, HttpServletResponse response) {
		logger.debug(" ( •̀ ω •́ )✧ /member/update -> updateMemberPOST() 실행 ");
		// 한글처리 인코딩 --> web.xml 필터 처리
		logger.debug(" ( •̀ ω •́ )✧ 전달받은 정보(파라메터)를 저장 ");
		logger.debug(" ( •̀ ω •́ )✧ vo "+vo);
		
		// 서비스 -> DAO : 전달받은 정보를 사용해서 정보 수정하는 동작
		int result = mService.memberUpdate(vo);
		
		if(result == 0) {
			// SQL - update 실행결과가 없음(수정X)
			return "redirect:/member/update";
		}
		
		// 수정 성공
		return "/member/main";
		
	}
	
	
	// 회원탈퇴 - GET(정보입력)
	@GetMapping(value = "/delete")
	public void deleteMemberGET(Model model, HttpSession session) {
		logger.debug(" ( •̀ ω •́ )✧ /member/delete -> deleteMemberGET() 실행 ");
		// 아이디 저장
		String id = (String) session.getAttribute("id");
		logger.debug(" ( •̀ ω •́ )✧ 아이디 : "+id);
		model.addAttribute(mService.memberInfo(id)); // 이후에 input hidden 없이 사용하거나 페이지 제어에 사용
		logger.debug(" ( •̀ ω •́ )✧ 연결된 뷰(jsp)페이지 출력 ");
	}
	
	// 회원탈퇴 - POST(처리)
	@PostMapping(value = "/delete")
	public String deleteMemberPOST(MemberVO vo, HttpSession session) {
		logger.debug(" ( •̀ ω •́ )✧ /member/delete -> deleteMemberPOST() 실행 ");
		logger.debug(" ( •̀ ω •́ )✧ 전달받은 파라메터 저장 (userid,userpw) ");
		logger.debug(" ( •̀ ω •́ )✧ dvo "+vo);
		
		
		// 서비스 -> DAO : 회원탈퇴
		int result = mService.memberDelete(vo);
		
		
		if(result == 0) {
			// SQL - delete 실패
			return "redirect:/member/delete";
		}
		
		// 세션객체 정보를 초기화
		session.invalidate();
		
		return "redirect:/member/main";
		
	}
	
	
	// 회원목록 조회 - GET(조회)
	@GetMapping(value = "/list")
	public void listMemberGET(Model model,HttpSession session) {
		logger.debug(" ( •̀ ω •́ )✧ /member/list -> listMemberGET(Model model,HttpSession session) 실행 ");
		
		// 아이디 정보
		String id = (String) session.getAttribute("id");
		logger.debug(" ( •̀ ω •́ )✧ 아이디 : "+id);
		
		// 서비스 -> DAO : 회원정보목록 조회
		List<MemberVO> listVO = mService.memberList();
		logger.debug(" ( •̀ ω •́ )✧ volist : "+listVO);
		
		// 서비스에서 가져온 데이터를 연결된 뷰페이지에 전달해서 출력
		model.addAttribute("listVO",listVO);
		// model.addAttribute(listVO); --> memberVOList 이름
		
		logger.debug(" ( •̀ ω •́ )✧ 연결된 뷰페이지로 이동 /views/member/list.jsp ");
		
	}
	
	
}//class controller
