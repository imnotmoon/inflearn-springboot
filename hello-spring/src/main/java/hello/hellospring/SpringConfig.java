package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
//        datasource 주입
        this.dataSource = dataSource;
    }


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

        /**
         * 개방-폐쇄 원칙(OCP; Open-Closed Principle)
         * 확장에는 열려있고, 수정에는 닫혀있다.
         * 객체지향에서 말하는 다형성이라는 개념을 잘 활용하면
         * 기능을 완전히 변경해도 애플리케이션 전체를 수정할 필요가 없음(조립은 수정해야함)
         * (같은 interface를 상속받은 전혀 다른 기능을 하는 여러 클래스)
         *
         * Spring의 DI를 통해 아주 조금의 수정만으로 애플리케이션의 기능을 변경
         */

//        1. access db via memory
//        return new MemoryMemberRepository();
//        2. access db via pure jdbc
//        return new JdbcMemberRepository(dataSource);
//        3. access db via jdbcTemplate
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
