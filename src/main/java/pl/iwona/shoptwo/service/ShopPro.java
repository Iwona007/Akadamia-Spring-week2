package pl.iwona.shoptwo.service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.iwona.shoptwo.model.Product;

@Service
@Profile("pro")
public class ShopPro implements Shop {

    @Value("${price.discount}")
    private BigDecimal discount;

    @Value("${price.tax}")
    private BigDecimal tax;

    public ShopPro() {

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
        BigDecimal sumTax = BigDecimal.valueOf(0);
        BigDecimal sumWithTax = BigDecimal.valueOf(0);

        for (Product product: productsList) {
            BigDecimal productTax = product.getPrice().multiply(tax).divide(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
            BigDecimal productWithTax = product.getPrice().add(productTax);
            System.out.println("Product: " + product.getName() + ", price: " + product.getPrice() +
                    " PLN, tax: " + productTax + " PLN, price with tax: " + productWithTax + " PLN.");
            sum = sum.add(product.getPrice());
            sumTax = sumTax.add(productTax);
            sumWithTax = sumWithTax.add(productWithTax);
        }
        System.out.println("Sum: " + sum + " PLN");
        System.out.println("Tax sum: " + sumTax + " PLN");
        System.out.println("Sum with tax: " + sumWithTax + " PLN");
        System.out.println("Sum with discount " + discount + "%: " +
                sumWithTax.subtract(discount.divide(new BigDecimal(100)).multiply(sumWithTax))
                        .setScale(2, RoundingMode.CEILING) + " PLN.");
    }
}



