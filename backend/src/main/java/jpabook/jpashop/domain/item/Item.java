package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.CategoryItem;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "dtype",
        discriminatorType = DiscriminatorType.STRING
        // default : String
)
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    protected String name;

    protected int price;

    protected int stockQuantity;

//    @OneToMany(mappedBy = "item")
//    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItemList = new ArrayList<>();

    /*
    * stock add
    * */

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
    * stock decrease
    * */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity -quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    abstract void changeProp (String name, int price, int stockQuantity);

}
