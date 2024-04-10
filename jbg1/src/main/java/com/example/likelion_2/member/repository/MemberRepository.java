package com.example.likelion_2.member.repository;

import com.example.likelion_2.member.domain.Member;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Getter
@Repository
public class MemberRepository {
    private HashMap<Long, Member> store = new HashMap<>();
    private Long sequence = 0L;

    public Member save(Member member) {
        if (isInOtherMemberPassword(member)) {
            return null;
        }
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    private boolean isInOtherMemberPassword(Member member) {
        for (Member m : store.values()) {
            if (member.equalsPassword(m)) {
                return true;
            }
        }
        return false;
    }
}
