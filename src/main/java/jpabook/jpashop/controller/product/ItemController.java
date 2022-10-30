package jpabook.jpashop.controller.product;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/items")
    public List<ItemInfo> listItem () {
        List<Item> itemList = itemService.findItem();
        List<ItemInfo> itemInfos = new ArrayList<>();
        for (Item item : itemList) {
            itemInfos.add(new ItemInfo(item.getId(),item.getName(),item.getPrice(),item.getStockQuantity(), ((Book) item).getAuthor(), ((Book) item).getIsbn()));
        }
        return itemInfos;
    }


    @PostMapping("/items/new")
    public String createBook (BookForm form) {
        Book book = new Book();
        book.changeProp(form.getName(),form.getPrice(),form.getStockQuantity(),form.getAuthor(),form.getIsbn());

        itemService.saveItem(book);
        return "true";
    }


    @PostMapping("/items/{itemId}/edit")
    public String modifyItemForm(@PathVariable("itemId") Long itemId, BookForm form){
        Book item = (Book) itemService.findOne(itemId);
        form.setId(item.getId());

        Book book = new Book();
        book.changeProp(form.getName(),form.getPrice(),form.getStockQuantity(),form.getAuthor(),form.getIsbn());

        itemService.updateBook(itemId,book);

        return "true";
    }
}
