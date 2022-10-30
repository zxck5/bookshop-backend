package jpabook.jpashop.controller.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemInfo {

//    private Long id;
    private int orderPrice;
    private int count;
    private String itemName;

    public OrderItemInfo(int orderPrice, int count, String itemName){
//        this.id = id;
        this.orderPrice = orderPrice;
        this.count = count;
        this.itemName = itemName;
    }
}
