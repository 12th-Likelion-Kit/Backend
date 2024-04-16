package com.example.demo.member.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.repository.MemberDAO;
import com.example.demo.member.repository.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO dao; // 빈 자동 주입
	
	@Override
	public List<MemberVO> memberList() {
		return dao.memberList();
	}

	@Override
	public boolean memberInsert(MemberVO member) {
		if(member != null) {
			dao.memberInsert(member);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean memberPwCheck(String pw) {
		if(pw != null) {
		return dao.memberPwCheck(pw);
		}
		return false;
	}



}
