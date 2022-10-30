package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Autowired EntityManager em;

    @Test
//    @Rollback(value = false)
    public void register() {
        //given
        Member member = new Member();
        member.setEmail("kim");

        //when
        Long savedId = memberService.join(member);

        //then
//        em.flush(); // it helps to commit all the persists
        Assertions.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    // if you use this, you don't have to use try and catch
    public void validateDuplicate() {
        //given
        Member member1 = new Member();
        member1.setEmail("kim1");
        Member member2 = new Member();
        member2.setEmail("kim1");

        //when
        memberService.join(member1);
        memberService.join(member2); // Exception

//        try {
//            memberService.join(member2);
//        }catch (IllegalStateException e){
//            return;
//        }

        //then
        Assertions.fail("Error");
    }

}