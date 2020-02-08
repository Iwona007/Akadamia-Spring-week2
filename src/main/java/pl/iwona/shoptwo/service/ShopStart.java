package pl.iwona.shoptwo.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.iwona.shoptwo.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopStart {

    private List<Product> products;
    private double sum;

    public ShopStart() {
        Product prod1 = new Product("memory card", priceBetween(50, 300));
        Product prod2 = new Product("pendrive", priceBetween(50, 300));
        Product prod3 = new Product("procesor Intel Core", priceBetween(50, 300));
        Product prod4 = new Product("headphones", priceBetween(50, 300));
        Product prod5 = new Product("RAM", priceBetween(50, 300));
        products = new ArrayList<>();
        products.add(prod1);
        products.add(prod2);
        products.add(prod3);
        products.add(prod4);
        products.add(prod5);
    }

    public List<Product> getProducts() {
        return products;
    }

    private static double priceBetween(double min, double max) {
        double range = max - min + 1;
        double a1 = (int) (Math.random() * range) + min;
        return a1;
    }

    private void sumOfPrice() {
        for (int i = 0; i < products.size(); i++) {
            sum += products.get(i).getPrice();
//            Math.round(sum);
        }
        System.out.println("Suma razem: " + sum);
    }

    private void showProductList() {
        products.forEach(System.out::println);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void show() {
        showProductList();
        sumOfPrice();
    }
}
