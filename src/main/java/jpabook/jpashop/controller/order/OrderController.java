package jpabook.jpashop.controller.order;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;


    @PostMapping("/order/new")
    public String orderCreate(@Valid OrderForm orderForm, BindingResult result) {

        // error handling
        Long memberId = Long.parseLong(orderForm.getMember_id());
        int quantity = Integer.parseInt(orderForm.getQuantity());
        List<Long> itemIds = new ArrayList<>();
        for (String id : orderForm.getProduct_id()) {
            itemIds.add(Long.parseLong(id));
        }
        // Map<ItemID, quantity>
        orderService.order(memberId,itemIds,quantity);

        return "true";
    }


    @PostMapping("/orders")
    public List<OrderInfo> orderList(OrderSearch orderSearch) {

        List<Order> orders = orderService.findOrders(orderSearch);
        List<OrderInfo> orderInfos = new ArrayList<>();

        for (Order order : orders) {
            OrderInfo info = new OrderInfo(order.getId(),order.getMember().getEmail(),order.getOrderDate().toString(),order.getStatus().toString());
            info.transfer(order.getOrderItems());
            orderInfos.add(info);
        }

        return orderInfos;
    }







}
