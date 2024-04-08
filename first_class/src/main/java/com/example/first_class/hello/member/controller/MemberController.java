package com.example.first_class.hello.member.controller;

import com.example.first_class.hello.member.domain.Member;
import com.example.first_class.hello.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @RequestMapping("/member")
    public String signupPage() {
        return "signup";
    }

    @RequestMapping("/member/signup")
    public String signup(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String password = request.getParameter("password");

        for (Member member : memberRepository.getStore().values()) {
            if (member.getPassword().equals(password)) {
                model.addAttribute("errorMessage", "이미 가입된 회원");
                return "signup";
            }
        }
        Member member = new Member(name, age, password);
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/member/list")
    public String list(Model model) {
        List<Member> members = new ArrayList<>(memberRepository.getStore().values());
        model.addAttribute("members", members);
        return "list";
    }
}