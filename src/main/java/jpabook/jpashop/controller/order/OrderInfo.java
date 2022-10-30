package jpabook.jpashop.controller.order;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
public class OrderInfo {

    private Long id;

    private List<OrderItemInfo> orderItemInfos = new ArrayList<>();


    private String memberEmail;

    private String orderDate;
    private String status;

    public OrderInfo(Long id,String memberEmail, String orderDate, String status) {
        this.id = id;
        this.memberEmail = memberEmail;

        this.orderDate = orderDate;
        this.status = status;
    }

    public void transfer(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems){
            OrderItemInfo info = new OrderItemInfo(
                    orderItem.getOrderPrice(),orderItem.getCount(),orderItem.getItem().getName()
            );
            orderItemInfos.add(info);
        }
    }
}
