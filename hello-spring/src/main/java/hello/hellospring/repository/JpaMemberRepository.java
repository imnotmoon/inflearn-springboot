package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        // injection
        // jpa를 쓸라면 entity manager를 써야함
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);     // 영구저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }



    @Override
    public Optional<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList().stream().findAny();
    }


    @Override
    public List<Member> findAll() {
        // 객체를 대상으로 쿼리를 날림. 엔티티로 처리하니까 가능함
        // select m : 객체 자체를 검색
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
