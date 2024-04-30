package spring.hello.repository;

import spring.hello.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // 값이 null일경우 null로 반환하지않고 Optional로 감싸서 반환하는 방식
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
