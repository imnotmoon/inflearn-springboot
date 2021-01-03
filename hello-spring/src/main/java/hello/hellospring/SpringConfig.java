package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    /**
     * 스프링이 시작하면 아래 두 빈을 컨테이너에 등록하고
     * memberRepository()로 만든 memory member repository를 MemberService에 등록
     * autowired와 유사
     * Controller는 어쩔 수 없음. 스프링이 어차피 관리하는거라 컴포넌트 스캔으로 올라감.
     *
     * 1. Component Scan - Autowired, notation
     * 2. Java코드로 직접 코딩.
     * 각각 장단이 있음.
     *
     * 정형화되지 않거나 상황에 따라 구현 클래스를 변경해야 할 경우 스프링빈을 코드로 등록한다.
     * Config를 사용하면 유동적인 코드에서 코드수정이 적음.
     */

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
