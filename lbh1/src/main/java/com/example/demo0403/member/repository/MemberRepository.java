package com.example.demo0403.member.repository;

import com.example.demo0403.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {
    private HashMap<Long, Member> store = new HashMap<>();
    private Long sequence = 0L;
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll() {
        return store.values().stream().toList();
    }
}
