package jpabook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter
public class Album extends Item{


    private String artist;

    public void changeProp(String name, int price, int stockQuantity, String artist){
        this.changeProp(name,price,stockQuantity);
        this.artist = artist;
    }

    @Override
    public void changeProp(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
