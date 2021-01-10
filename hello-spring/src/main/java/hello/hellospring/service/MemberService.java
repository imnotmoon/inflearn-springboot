package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * 1. Service 같은 클래스는 비즈니스 로직에 따라 네이밍하는게 좋음
 * 2. Optional과 관련 메소드
 */

/**
 * Service : spring이 올라올때 컨테이너에 등록해줌. 안하면 그냥 순수한 자바클래스
 *          비즈니스 로직을 만듦.
 */
//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * MemoryMemberRepository가 연결
     * @param memberRepository
     */
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        long start = System.currentTimeMillis();
        // 같은 이름이 있는 중복 회원X
        // findByName의 결과가 optional이라 바로 ifPresent 메소드 사용 가능
        validateDuplicateMember(member);    // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        long start = System.currentTimeMillis();
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {

        long start = System.currentTimeMillis();
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        long start = System.currentTimeMillis();
        return memberRepository.findById(memberId);
    }

}
