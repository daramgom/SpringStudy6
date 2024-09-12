package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {
	
	// MemberDAO 객체의 메서드 호출
	@Inject
	private MemberDAO mdao;
	
	// @Test
	public void getBean() {
		System.out.println(" ( •̀ ω •́ )✧ mdao : "+mdao);
	}
	// @Test
	public void getTime() {
		mdao.getTime();
	}
	
	// @Test
	public void 회원가입테스트() { // --> 한글 메서드명 테스트에만 쓸 수 있고 권장 X
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원가입테스트() 시작");
		
		// 회원가입정보
		MemberVO vo = new MemberVO();
		vo.setUserid("fourjumin");
		vo.setUserpw("1234");
		vo.setUsername("너이주민");
		vo.setUseremail("fourjumin@onejumin.com");
		
		mdao.insertMember(vo);
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원가입테스트() 끝");
	}
	
	// @Test
	public void 로그인테스트() {
		System.out.println(" ( •̀ ω •́ )✧ TEST : 로그인테스트() 시작 ");
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("1234");
		
		MemberVO resultVO = mdao.loginMember(vo);
		
		System.out.println(" ( •̀ ω •́ )✧ TEST : "+resultVO);
		
		if (resultVO != null) {
			System.out.println(" ( •̀ ω •́ )✧ TEST : 로그인 성공 ");
		} else {
			System.out.println(" ( •̀ ω •́ )✧ TEST : 로그인 실패 ");
		}
		
		System.out.println(" ( •̀ ω •́ )✧ TEST : 로그인테스트() 끝 ");
	}
	
	// @Test
	public void 로그인테스트2() {
		System.out.println(" ( •̀ ω •́ )✧ TEST : 로그인테스트2() 시작 ");
		
		MemberVO resultVO = mdao.loginMember("admin","1234");
		
		System.out.println(" ( •̀ ω •́ )✧ TEST : "+resultVO);
		
		if (resultVO != null) {
			System.out.println(" ( •̀ ω •́ )✧ TEST : 로그인 성공 ");
		} else {
			System.out.println(" ( •̀ ω •́ )✧ TEST : 로그인 실패 ");
		}
		
		System.out.println(" ( •̀ ω •́ )✧ TEST : 로그인테스트2() 끝 ");
	}
	
	// @Test
	public void 회원정보조회테스트() {
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보조회테스트() 시작 ");
		
		MemberVO resultVO = mdao.getMember("admin");
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보 : "+resultVO);
		System.out.println(" ( •̀ ω •́ )✧ TEST : 아이디 : "+resultVO.getUserid());
		System.out.println(" ( •̀ ω •́ )✧ TEST : 이름 : "+resultVO.getUsername());
		System.out.println(" ( •̀ ω •́ )✧ TEST : 이메일 : "+resultVO.getUseremail());
		
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보조회테스트() 끝 ");
	}
	
	// @Test
	public void 회원정보수정테스트() {
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보수정테스트() 시작 ");
		MemberVO uvo = new MemberVO();
		uvo.setUserid("admin");
		uvo.setUserpw("1234");
		uvo.setUsername("관리자(진)");
		uvo.setUseremail("adminreal@admin.com");
		
		int result = mdao.updateMember(uvo);
		System.out.println(" ( •̀ ω •́ )✧ TEST : "+result);
		
		if(result > 0) {
			System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보 수정완료 ");
		} else {
			System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보 수정실패 ");			
		}
		MemberVO resultVO = mdao.getMember("admin");
		System.out.println(" ( •̀ ω •́ )✧ TEST : 수정된 회원정보 확인 "+resultVO);
		System.out.println( " ( •̀ ω •́ )✧ TEST : 회원정보수정테스트() 끝 ");
	}
	
	// @Test
	public void 회원정보삭제테스트() {
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보삭제테스트() 시작 ");
		MemberVO dvo = new MemberVO();
		dvo.setUserid("threejumin");
		dvo.setUserpw("1234");
		
		int result = mdao.deleteMember(dvo);
		System.out.println(" ( •̀ ω •́ )✧ TEST : "+result);
		
		if(result > 0) {
			System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보 삭제완료 ");
		} else {
			System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보 삭제실패 ");			
		}
		
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원정보삭제테스트() 끝 ");
	}
	
	@Test
	public void 회원목록리스트() {
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원목록리스트() 시작 ");
		List<MemberVO> memberList = mdao.getMemberList();
		
		for (MemberVO vo : memberList) {
			System.out.println(" ( •̀ ω •́ )✧ TEST : vo : "+vo);
		}
		System.out.println(" ( •̀ ω •́ )✧ TEST : 회원목록리스트() 끝 ");
	}
	
}
