package pl.iwona.shoptwo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@Profile("plus")
public class ShopPlus {

    @Value("${price.tax}")
    private double tax;

    private ShopStart shopStart;

    @Autowired
    public ShopPlus(ShopStart shopStart) {
        this.shopStart = shopStart;
    }

//        public double priceWithTax2() {
//        double withTax = 0;
//        for (int i = 0; i < shopStart.getProducts().size(); i++) {
//            withTax = shopStart.getProducts().get(i).getPrice() * (1 + tax);
//            System.out.println("caena prodóktów z podatkiem" + " " +withTax);
//        }
//        return withTax;
//    }

    public void priceWithTax() {
        shopStart.getProducts().stream()
                .map(price -> price.getPrice() * (1 + tax))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void showPlus() {
        priceWithTax();
    }
}
