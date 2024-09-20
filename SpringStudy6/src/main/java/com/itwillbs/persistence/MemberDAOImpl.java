package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

/**
 *  MemberDAO 동작을 수행
 *
 */

// @Repository : 스프링이 해당클래스를 DAO객체(Bean)로 인식하게 함
//				 -> root-context.xml 파일에서 해당 객체를 사용하도록 설정 --> component-scan

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	// 공통변수, DB연결, 자원해제
	// DB연결 객체(SqlSessionFactory)가 필요함 --> 의존관계 주입
	// @Inject
	// private SqlSessionFactory sqlFactory;
	
	@Inject
	private SqlSession sqlSession; // 자동으로 연결, 자원해제, SQL 실행, mybatis ...
	
	// Mapper namespace 정보 저장
	private static final String NAMESPACE ="com.itwillbs.mapper.MemberMapper";
	
	@Override
	public String getTime() {
		System.out.println(" ( •̀ ω •́ )✧ DAO : getTime() 실행 ");
		// 1,2. DB연결
			//SqlSession sqlSession = sqlFactory.openSession(); --> 생략가능
		// 3. SQL 구문 & pstmt 객체
		// 4. SQL 실행
			// sqlFactory.selectOne(SQL구문);
			// sqlFactory.selectOne(SQL구문,전달정보);
			String result
				= sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
				// 	--> Mapper의 sql 구문 id를 호출
				// = sqlSession.selectOne("select now()");
				// 	--> 직접적으로 SQL구문 호출 X
		// 5. 데이터 처리
			System.out.println(" ( •̀ ω •́ )✧ 결과 : "+result);
			
		return result;
	}

	@Override
	public void insertMember(MemberVO vo) {
		System.out.println(" ( •̀ ω •́ )✧ DAO : 회원가입 동작 실행 ");
		// 1,2. DB연결--> 생략 sqlSession객체 수행
		// 3. SQL 구문 (mapper 생성) & pstmt 객체 (mybatis 관리)
		// 4. SQL 실행
		// [com.itwillbs.mapper.MemberMapper.insertMember]
		int result = sqlSession.insert(NAMESPACE + ".insertMember", vo);
		
		System.out.println(" ( •̀ ω •́ )✧ DAO : "+result);
		System.out.println(" ( •̀ ω •́ )✧ DAO : 회원가입 완료 ");
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		System.out.println(" ( •̀ ω •́ )✧ DAO : loginMember(MemberVO vo) 실행 ");
		
		// SQL 구문을 mapper에 생성
		System.out.println(" ( •̀ ω •́ )✧ DAO : mapper SQL 생성완료 ");
		// SQL 구문 실행
		MemberVO resultVO 
			= sqlSession.selectOne(NAMESPACE + ".loginMember", vo);
		
		System.out.println(" ( •̀ ω •́ )✧ DAO : "+resultVO);
		
		return resultVO;
	}

	@Override
	public MemberVO loginMember(String userid, String userpw) {
		System.out.println(" ( •̀ ω •́ )✧ DAO : loginMember(userid, userpw) 실행");
		
		// sqlSession.selectOne(NAMESPACE + ".loginMember", userid,userpw); (X)
		// MemberVO vo = new MemberVO();
		// vo.setUserid(userid);
		// vo.setUserpw(userpw);
		// 	--> 전달받은 정보를 하나의 공통객체에 저장 (스프링은 이 단계를 알아서 해줌) --> 전달할 때 객체로 전달
		
		// ** userid(회원가입), userpw(게시판)는 하나의 객체(MemberVO)에
		// 저장이 불가능하다고 가정 --> 예) Join 구문 실행할 때
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// paramMap.put("mapper에서 호출하는 이름", 전달된 값);
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		MemberVO resultVO 
			= sqlSession.selectOne(NAMESPACE + ".loginMember", paramMap);
		// resultVO.setUserid(userid+"@@@");
		System.out.println(" ( •̀ ω •́ )✧ DAO : "+resultVO);
		return resultVO;
	}

	@Override
	public MemberVO getMember(String userid) {
		System.out.println(" ( •̀ ω •́ )✧ DAO : getMember(MemberVO vo) 실행 ");
		// mapper SQL 작성
		// sqlSession 사용 SQL 실행
		return sqlSession.selectOne(NAMESPACE + ".getMember", userid);
	}

	@Override
	public int updateMember(MemberVO uvo) {
		System.out.println(" ( •̀ ω •́ )✧ DAO : updateMember(Member uvo) 실행 ");
		// mapper - sql 작성
		// sqlSession - sql 실행 결과에 따른 정수데이터를 리턴
		return sqlSession.update(NAMESPACE + ".updateMember", uvo);
	}

	@Override
	public Integer deleteMember(MemberVO dvo) {
		System.out.println(" ( •̀ ω •́ )✧ DAO : deleteMember(MemberVO dvo) 실행");
		// mapper - sql 작성
		// sqlSession - sql 실행 결과에 따른 정수데이터를 리턴
		return sqlSession.delete(NAMESPACE + ".deleteMember", dvo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		System.out.println(" ( •̀ ω •́ )✧ DAO : getMemberList() 실행 ");
		// mapper - sql 작성
		// sqlSession - sql 실행
		return sqlSession.selectList(NAMESPACE + ".getMemberList");
	}
	
	
	

}
