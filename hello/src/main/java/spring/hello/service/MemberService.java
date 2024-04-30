package spring.hello.service;

import org.springframework.stereotype.Service;
import spring.hello.domain.Member;
import spring.hello.repository.MemberRepository;
import spring.hello.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    회원 가입
    */
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증. 이름이 같을 수 없음.

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            try {
                throw new IllegalAccessException("이미 존재하는 회원입니다.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
    /*
        전체 회원 조회
    */
    public List<Member> findMembers() {
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
