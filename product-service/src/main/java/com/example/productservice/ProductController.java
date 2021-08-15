package com.example.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("product/details/{productid}")
    public Product getProductInfo(@PathVariable Long productid) {
        return new Product(productid, "iPhone", "iPhone 12", 25000, true);
    }
}
