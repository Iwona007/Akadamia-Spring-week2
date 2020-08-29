package pl.iwona.shoptwo.service;

import java.math.BigDecimal;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.iwona.shoptwo.model.Product;

@Profile("Start")
@Service
public class ShopStart implements Shop {

    public ShopStart() {
        productsList.add(new Product("memory card", sumPrice()));
        productsList.add(new Product("pendrive", sumPrice()));
        productsList.add(new Product("procesor Intel Core", sumPrice()));
        productsList.add(new Product("headphones", sumPrice()));
        productsList.add(new Product("RAM", sumPrice()));
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void sumBasket() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Product product : productsList) {
            System.out.println("Product: " + product.getName() + ", price: " + product.getPrice() + " PLN");
            sum = sum.add(product.getPrice());
        }
        System.out.println("Sum: " + sum + " PLN");
    }
}

