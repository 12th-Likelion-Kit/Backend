package com.example.demo.member.service;

import java.util.List;

import com.example.demo.member.repository.MemberVO;

public interface MemberService {
	
	// 회원 전체 목록을 출력하기 위해 데이터를 얻는 기능
	// 한명의 회원의 데이터는 MemberVo 객체에 저장함. 여러명 회원의 데이터를 반환
	public List<MemberVO> memberList();

// 회원 가입 폼에서 넘어온 데이터를 받아 회원 데이터를 저장하는 기능
 // MemberVO 객체로 한명의 여러 속성을 간편하게 받음
	// 잘 대입되면 true 반환해줌
	public boolean memberInsert(MemberVO member);

// 회원 가입 전에 중복된 pw 가 있는지 확인해주는 기능
	public boolean memberPwCheck(String pw);


	


}
