package pl.iwona.shoptwo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import pl.iwona.shoptwo.model.Product;

public interface Shop {
    List<Product> productsList = new ArrayList<>();

    default void addProductToProductsList(Product product, BigDecimal price) {
        productsList.add(product);
    }

    void sumBasket();

    default BigDecimal sumPrice() {
        BigDecimal random  = new BigDecimal(Math.random()*250+50);
        return random.setScale(2, RoundingMode.CEILING);
    }
}
