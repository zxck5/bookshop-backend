package jpabook.jpashop.controller.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberInfo {

    private Long id;

    private String email;

    private String city;
    private String street;
    private String zipcode;


    public MemberInfo(Long id, String email, String city, String street, String zipcode) {
        this.id = id;
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
