package com.example.demo.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.member.repository.MemberVO;
import com.example.demo.member.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService service;




    // 회원 목록페이지 요청
    @RequestMapping("/member/list")
    public String memberList(Model model) {
        List<MemberVO> list = service.memberList();
        model.addAttribute("memberList",list);
        return "list";

    }

    // 회원 가입시 회원 가입 폼페이지 요청
    @GetMapping("/member")
    public String joinForm() {
        return "signup";
    }


    // 회원 가입 후에 등록 요청
    @PostMapping("/member")
    public String join( MemberVO member) {

        boolean pwCheck = service.memberPwCheck(member.getPw());
        if(pwCheck) { // 같은 비밀번호가 있는 경우
            return "redirect:/";
        }

        // 같은 비밀번호가 없는 경우
        boolean result = service.memberInsert(member);
        return "redirect:/member/list";

    }

}
