package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * spring jpa repository
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /**
     * JPQL  >>  select m from Member m where m.name = ?
     * findByName, findByEmail 등 이름만으로 서비스 제공
     * ex) findByNameAndId, findByNameOrId 등..
     * 페이징 기능까지 제공
     *
     * 복잡한 동적쿼리의 경우 Querydsl 이라는 라이브러리로 사용
     */
    @Override
    Optional<Member> findByName(String name);

}
