package jpabook.jpashop.controller.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemInfo {

    private Long id;

    protected String name;

    protected int price;

    protected int stockQuantity;

    private String author;

    private String isbn;

    public ItemInfo(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }
}
