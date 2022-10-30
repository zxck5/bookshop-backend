package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
public class Book extends Item{

    private String author;
    private String isbn;


    @Override
    public void changeProp(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void changeProp(String name, int price, int stockQuantity, String author, String isbn){
        this.changeProp(name,price,stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
