package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @Transactional 은 테스트케이스에 달면
 * 테스트를 실행할 때 트랜잭션을 먼저 실행 하고
 * db에 인서트 쿼리를 하고 다 넣은 다음에 테스트가 끝나면
 * 롤백을 해준다.
 */
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
//테스트 할 메서드가 있는 클래스에서 ctrl+shift+T 로 바로 테스트 클래스 생성 가능

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");


        //when
        Long saveId = memberService.join(member);
        System.out.println(">>>> Join ID : " + saveId);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void checkDuplicateMember() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("중복 회원입니다.");
        /*try {
            memberService.join(member2);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("중복 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}