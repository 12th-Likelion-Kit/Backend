package study.kky1.member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String name;
    private int age;
    private String password;

    // Art+Insert : Generate 단축키
    public Member(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}
