package com.example.demo.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String name;
    private int age;
    private String password;

    public Member(String name, int age, String password){
        this.name = name;
        this.age = age;
        this.password= password;
    }
}
