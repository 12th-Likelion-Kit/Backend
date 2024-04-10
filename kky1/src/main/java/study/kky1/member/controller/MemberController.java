package study.kky1.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import study.kky1.member.domain.Member;
import study.kky1.member.repository.MemberRepository;
import study.kky1.member.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @RequestMapping("/member")
    public String signupPage(){
        return "signup";
    }

    @RequestMapping("/member/signup")
    public String signup(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String password = request.getParameter("password");

        Member member = new Member(name, age, password);
        return memberService.addMember(member, model);
    }

    @RequestMapping("/member/list")
    public String list(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "list";
    }
}
