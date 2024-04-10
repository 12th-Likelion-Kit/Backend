package com.example.likelion_2.member.controller;

import com.example.likelion_2.member.domain.Member;
import com.example.likelion_2.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

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
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/member/list")
    public String listPage(Model model) {
        List<Member> members = new ArrayList<>(memberRepository.getStore().values());
        model.addAttribute("members", members);
        return "list";
    }
}
