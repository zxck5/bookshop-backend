package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // spring makes entity manager and injects in em
//    @Autowired @PersistenceContext --> spring boot enables to do it automatically without them
    @PersistenceContext
    private final EntityManager em;


    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    public List<Member> findAll() {
        // queries are written. it is similar but we are accessing to entity.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByEmail(String email) { // :name refers to parameter
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email",email) // binding name as :email
                .getResultList();
    }
}
