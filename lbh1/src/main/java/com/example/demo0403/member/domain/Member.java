package com.example.demo0403.member.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Member {
    private Long id;
    private String name;
    private int age;
    private String password;

    public Member(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}
