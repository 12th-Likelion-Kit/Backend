package com.example.likelion_2.member.controller;

import com.example.likelion_2.member.domain.Member;
import com.example.likelion_2.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/member/signup")
    public String signup(HttpServletRequest request) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String password = request.getParameter("password");
        Member member = new Member(name, age, password);
        memberService.addMember(member);
        return "redirect:/";
    }

    @GetMapping("/member/list")
    public String listPage(Model model) {
        List<Member> members = new ArrayList<>(memberService.getMemberCollection());
        model.addAttribute("members", members);
        return "list";
    }
}
