package com.example.likelion_2.member.service;

import com.example.likelion_2.member.domain.Member;
import com.example.likelion_2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Collection<Member> getMemberCollection() {
        return memberRepository.getStore().values();
    }

    public Member addMember(Member member) {
        if (isInOtherMemberPassword(member)) {
            return null;
        }
        memberRepository.save(member);
        return member;
    }

    private boolean isInOtherMemberPassword(Member member) {
        for (Member m : getMemberCollection()) {
            if (member.equalsPassword(m)) {
                return true;
            }
        }
        return false;
    }
}
