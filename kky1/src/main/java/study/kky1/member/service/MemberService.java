package study.kky1.member.service;

import org.springframework.ui.Model;
import study.kky1.member.domain.Member;

public interface MemberService {
    String addMember(Member member, Model model);
}
