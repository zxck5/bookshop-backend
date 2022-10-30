package jpabook.jpashop.controller.member;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/members")
    public List<MemberInfo> listMember(){
        MemberInfo[] memberInfos = memberService.findMembers().stream().map(
                m -> new MemberInfo(m.getId(),m.getEmail(),m.getAddress().getCity(),m.getAddress().getStreet(),m.getAddress().getZipcode()))
               .toArray(MemberInfo[]::new);

        return new ArrayList<>(Arrays.asList(memberInfos));

    }


    @PostMapping("/members/new")
    public Object createForm(@Valid MemberForm memberForm, BindingResult result){
//        Address address = new Address(memberForm.getCity(),memberForm.getStreet(),memberForm.getZipcode());
//        Member member = new Member();
//        member.setEmail(memberForm.getEmail());
//        member.setAddress(address);
//        memberService.join(member);
//        return "true";
        if (result.hasErrors()){
            String message = result.getAllErrors().stream().map(e ->e.getDefaultMessage()).findFirst().orElse(null);

            result.getAllErrors().stream().map(e ->e.getCode()).forEach(e1 -> System.out.println(e1));
            return message;
        } else {
            Address address = new Address(memberForm.getCity(),memberForm.getStreet(),memberForm.getZipcode());
            Member member = new Member();
            member.setEmail(memberForm.getEmail());
            member.setAddress(address);
            try {
                memberService.join(member);
            }catch (IllegalStateException e){
                return e.getMessage();
            }
            return "true";
        }

    }

}
