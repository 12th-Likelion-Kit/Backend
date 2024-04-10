package study.kky1.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import study.kky1.member.domain.Member;
import study.kky1.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public String addMember(Member member, Model model) {
        List<Member> members = memberRepository.findAll();
        for(Member m : members){
            if(member.getPassword().equals(m.getPassword())){
                model.addAttribute("error", "이미 가입된 회원입니다.");
                return "forward:/member";
            }
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}
