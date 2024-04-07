package com.example.demo0403.member.controller;

import com.example.demo0403.member.domain.Member;
import com.example.demo0403.member.repository.MemberRepository;
import com.example.demo0403.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @RequestMapping("/member")
    public String signupPage() {
        return "signup";
    }

    @RequestMapping("/member/signup")
    public String signup(HttpServletRequest request) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String password = request.getParameter("password");

        Member member = new Member(name, age, password);
        return memberService.addMember(member);
    }

    @RequestMapping(value = {"/list", "/member/list"})
    public String list(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "list";
    }
}
