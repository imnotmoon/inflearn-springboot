package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /**
     * 각 테스트를 실행하기 전에
     * new MemberService에서 원래 클래스의 memberService Constructor를 실행하는거라
     * 원래의 memberRepository에 넣어줌
     * 그럼 test객체가 원래 MemberService의 객체와 겹치치 않아서 같은 MemoryMemberService가 사용됨
     *
     * "Dependency Ejection"
     *
     */
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  // 테스트케이스끼리의 의존성을 없앰.
    public void afterEach() {
        memberRepository.clearStore();
    }

    /**
     * given - when - then 문법
     */
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
//        memberService.join(member1);
//        try {
//            fail();
//        } catch (IllegalStateException e) {
//            // 정상 플로우
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }



//        try-catch보다 좀더 나은 문법
        memberService.join(member1);
        // 뒤에 로직을 태울때 앞의 에러가 터져야함. 성공이 정상
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 이건 실패가 정상. 다른 예외가 터져
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}