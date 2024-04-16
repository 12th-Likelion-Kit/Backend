package com.example.demo.member.repository;

import java.sql.Timestamp;

// 한명회언의 데이터를 넣을 클래스
public class MemberVO { // DTO 라고 이름 지어도 됨
	private String pw;
	private String name;
	private Integer age;

	public MemberVO() {}

	public MemberVO(String name , String pw, Integer age) {
		this.name = name;
		this.pw = pw;
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
}
