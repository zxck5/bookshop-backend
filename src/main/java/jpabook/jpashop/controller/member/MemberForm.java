package jpabook.jpashop.controller.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@ToString
public class MemberForm {

    @NotEmpty(message = "email is required")
    @Email
    private String email;

    private String city;
    private String street;
    private String zipcode;


    public MemberForm(String email, String city, String street, String zipcode) {
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
