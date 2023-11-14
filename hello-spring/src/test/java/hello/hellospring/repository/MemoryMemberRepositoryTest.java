package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 현재 테스트코드는 먼저 개발을 끝낸 뒤 테스트코드를 작성했음.
 * 이 방법을 뒤집어서 테스트코드를 작성한 후 개발을 할 수가 있음
 * 이것을 테스트주도개발 TDD 라고 함.
 */
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 테스트는 메서드 서로 순서나 의존관계 없이 설계가 되어야 한다.
     * 그러다보니 먼저 repository 에 저장시키는 테스트코드가 있을 경우
     * 다른 곳에서 에러가 날 수 있으므로
     * After Each를 통해 메서드가 끝나면 repository 를 clear 해준다.
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
