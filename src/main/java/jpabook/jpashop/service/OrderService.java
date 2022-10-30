package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /*
    * order
    *
    * */

    // order
    @Transactional
    public Long order(Long memberId, List<Long> itemIds, int count) {
        //Entity
        Member member = memberRepository.findOne(memberId);

        List<Item> itemList = new ArrayList<>();
        for (Long itemId : itemIds) {
            Item item = itemRepository.findOne(itemId);
            itemList.add(item);
        }
        //delivery info
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //orderitem


        List<OrderItem> orderItemList = new ArrayList<>();
        for (Item item : itemList) {
            OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
            orderItemList.add(orderItem);
        }

        // Order create
        Order order = Order.createOrder(member, delivery, orderItemList);

        // order save
        orderRepository.save(order);
        // cascade --> delivery, orderItems persist automatically

        return order.getId();

    }

    // cancel

    @Transactional
    public void cancelOrder(Long orderId) {
        // order entity
        Order order = orderRepository.findOne(orderId);

        // order cancel
        order.cancel();
    }


    // search function
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
