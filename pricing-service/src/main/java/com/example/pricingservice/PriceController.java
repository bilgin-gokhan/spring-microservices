package com.example.pricingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PriceController {

    @Autowired
    private RestTemplate restTemplate;

    List<Price> priceList = new ArrayList<Price>();

    @GetMapping("price/{productid}")
    public Price getProductDetails(@PathVariable Long productid) {
        Price price = getPriceInfo(productid);
        // Get Exchange Value
        Integer exgVal = restTemplate.getForObject("http://localhost:8004/currexg/from/USD/to/YEN", ExgVal.class).getExgVal();
        return new Price(price.getPriceID(), price.getProductID(),
                price.getOriginalPrice(), Math.multiplyExact(exgVal, price.getDiscountedPrice()));
    }

    private Price getPriceInfo(Long productid) {
        populatePriceList();
        for (Price p : priceList) {
            if (productid.equals(p.getProductID())) {
                return p;
            }
        }
        return null;
    }

    private void populatePriceList() {
        priceList.add(new Price(101L, 101L, 1999, 999));
        priceList.add(new Price(102L, 102L, 199, 19));
        priceList.add(new Price(103L, 103L, 199, 99));

    }
}
