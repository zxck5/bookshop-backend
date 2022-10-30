package jpabook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
public class Movie extends Item{
    private String director;
    private String actor;

    @Override
    public void changeProp(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void changeProp(String name, int price, int stockQuantity, String director, String actor){
        this.changeProp(name,price,stockQuantity);
        this.director = director;
        this.actor = actor;
    }
}
