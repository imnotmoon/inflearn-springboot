package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import javax.accessibility.Accessible;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /**
     * 인터페이스만 만들어도 Spring Data JPA가 인터페이스에 대한 구현체를 만들어서 Spring Bean에 등록한다.
     * 구현체에는 메소드같은게 다 만들어져있음. 우리가 일반적으로 생각하는 공통적인 쿼리들.
     * 못만드는것도 있긴함. 특정 열 기준으로 찾고 그런건 공통적으로 제공할 수가 없음. 비즈니스마다 다 달라서
     *
     */


    // 위에서 적은 비공통 메소드.
    // 규칙에 맞게 메소드 네이밍해주면 인터페이스 이름만으로도 개발이 끝남.
    // 오진다..
    // 메서드 이름이 중요

    // 실무에서는 JPA와 데이터 JPA를 기본으로 사용. 동적 쿼리는 Querydsl등을 사용
    @Override
    Optional<Member> findByName(String name);
}
