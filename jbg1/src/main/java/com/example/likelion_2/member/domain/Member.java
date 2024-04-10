package com.example.likelion_2.member.domain;

import lombok.Getter;
import lombok.Setter;

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

    public boolean equalsPassword(Member other) {
        return other.password.equals(this.password);
    }
}
