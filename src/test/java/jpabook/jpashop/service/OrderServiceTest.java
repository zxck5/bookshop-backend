package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void order() {
        // given

        Member member = createMember("Jiwon","Toronto","Street","M9V 1N8");

        Book book = createBook("JPABOOK",10000,10);


        int orderCount = 2;
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(book.getId());
        // when
        Long orderId = orderService.order(member.getId(),itemIds,orderCount);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("The state of product is ORDER", OrderStatus.ORDER,getOrder.getStatus());
        Assert.assertEquals("Ordered item number", 1, getOrder.getOrderItems().size());
        Assert.assertEquals("Total price is price * items" , 10000*orderCount, getOrder.getTotalPrice());
        Assert.assertEquals("Did item inventory reduce as it is ordered?", 8, book.getStockQuantity());
    }



    @Test
    public void cancelOrder() {
        //given
        Member member = createMember("Jiwon","Toronto","Street","M9V 1N8");

        Item item = createBook("JPABOOK",10000,10);

        List<Long> itemIds = new ArrayList<>();
        itemIds.add(item.getId());

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), itemIds, orderCount);


        // when
        orderService.cancelOrder(orderId);

        // then
        Order getOrder = orderRepository.findOne(orderId);
        Assert.assertEquals("when you cancel the order, the status is cancelled", OrderStatus.CANCEL, getOrder.getStatus());
        Assert.assertEquals("when you cancel the order, the quantityStock is refilled", item.getStockQuantity(), 10);
    }


    @Test(expected = NotEnoughStockException.class)
    public void productOrder_exceedCase() throws Exception {
        // given
        Member member = createMember("Jiwon","Toronto","Street","M9V 1N8");

        Item item = createBook("JPABOOK",10000,10);

        int orderCount = 11;

        List<Long> itemIds = new ArrayList<>();
        itemIds.add(item.getId());
        
        // when
        orderService.order(member.getId(), itemIds, orderCount);


        // then
        fail("Exception should be thrown");

    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.setEmail(name);
        member.setAddress(new Address(city, street, zipcode ));
        em.persist(member);
        return member;
    }
}