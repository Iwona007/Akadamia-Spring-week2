package pl.iwona.shoptwo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Profile("pro")
public class ShopPro {

    @Value("${price.discount}")
    private double discount;

    @Value("${price.tax}")
    private double tax;

    private ShopStart shopStart;

    public ShopPro(ShopStart shopStart) {
        this.shopStart = shopStart;
    }

    public void priceWithDiscount() {
        shopStart.getProducts().stream()
                .map(price -> Math.round(price.getPrice() * (1 + tax) * (1 - discount)))
                .collect(Collectors.toList())
                .forEach(System.out::println);

//        for (int i = 0; i < shopStart.getProducts().size(); i++) {
//            double withDisc = shopStart.getProducts().get(i).getPrice() * (1 + shopPlus.getTax()) *(1-discount);
//            System.out.println("caena prodóktów z rabatem " + " " + withDisc);
        }
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void showPlus() {
        priceWithDiscount();
    }
}

