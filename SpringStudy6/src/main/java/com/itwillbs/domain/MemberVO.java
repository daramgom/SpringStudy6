package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *  VO (Value Object) : 데이터 저장 객체 (값을 저장하는 동작 이외의 동작 O)
 *  DTO (Data Transfer Object) : 데이터 전송 객체 (값을 저장하는 동작 이외의 동작 X)
 *  
 *  
 *  tbl_member 테이블 정보를 저장하는 객체
 */

// @Data --> set/get 메서드 자동생성
// @Setter
// @Getter
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
@Data
public class MemberVO {
	
	// private String Uid;
	// private String uId; // getUId(); (X)
	// private String Userid; // getUserId(); (△) --> 소문자형태로 변수명 설정하는 것이 좋음
	
	private String userid; // getUserid();
	private String userpw;
	private String username;
	private String useremail;
	private Timestamp regdate;
	private Timestamp updatedate;
	
}
