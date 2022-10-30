package jpabook.jpashop.controller.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OrderForm {
//    @NotEmpty(message = "member required")
    private String member_id;

//    @NotEmpty(message = "product required")
    private String product_id [];

//    @NotEmpty(message = "quantity required")
    private String quantity;



}
