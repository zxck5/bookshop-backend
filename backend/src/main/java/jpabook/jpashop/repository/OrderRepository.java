package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class,id);
    }


    // dynamic query.
     public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m";


        return em.createQuery("select o from Order o join o.member m " +
                        "where o.status = :status " +
                        "and m.email like :email", Order.class)
                 // parmeter binding.
                .setParameter("status",orderSearch.getOrderStatus())
                .setParameter("email",orderSearch.getMemberEmail())
//                .setFirstResult(100) //range 100~1000
                .setMaxResults(1000) // max is 1000
                .getResultList();
     }
    public List<Order> findAllByString(OrderSearch orderSearch) {
        //language=JPQL
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;
        //search status of order
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }
            //search member email
        if (StringUtils.hasText(orderSearch.getMemberEmail())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.email like :email";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberEmail())) {
            query = query.setParameter("email", orderSearch.getMemberEmail());
        }
        return query.getResultList();
    }

}
