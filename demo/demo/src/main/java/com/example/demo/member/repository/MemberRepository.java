package com.example.demo.member.repository;

import com.example.demo.member.domain.Member;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Getter
public class MemberRepository {
    private HashMap<Long, Member> store = new HashMap<>();
    private Long sequence = 0L;
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

}
