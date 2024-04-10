package study.kky1.member.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import study.kky1.member.domain.Member;

import java.util.HashMap;
import java.util.List;

@Repository
@Getter
public class MemberRepository {
    private HashMap<Long, Member> store = new HashMap<>();
    private Long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll() {
        return store.values().stream().toList();
    }
}
