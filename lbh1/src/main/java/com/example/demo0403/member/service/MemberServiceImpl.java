package com.example.demo0403.member.service;

import com.example.demo0403.member.domain.Member;
import com.example.demo0403.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public String addMember(Member member) {
        List<Member>members = memberRepository.findAll();
        for(Member m : members) {
            if (member.getPassword().equals(m.getPassword())) {
                log.error("Member's password already exists");
                return "redirect:/member";
            }
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}
