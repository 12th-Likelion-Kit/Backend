package com.example.demo.member.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	List<MemberVO> list = new ArrayList<MemberVO>();


	@Override
	public List<MemberVO> memberList() {
		return list;
	}

	@Override
	public boolean memberInsert(MemberVO member) {
		return list.add(member);
	}

	@Override
	public boolean memberPwCheck(String pw) {
		
		for(int i = 0 ; i <list.size();i++) {
			MemberVO member = list.get(i);
			if(member.getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}


}
