package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@RequiredArgsConstructor
public class OrderSearch {
    private String memberEmail;
    private OrderStatus orderStatus; // order status[ORDER, CANCEL]



}
