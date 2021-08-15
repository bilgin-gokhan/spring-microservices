package com.example.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    List<ProductInfo> productList = new ArrayList<ProductInfo>();

    @GetMapping("product/details/{productid}")
    public Product getProductDetails(@PathVariable Long productid) {
        //Get Name and Desc from product-service
        ProductInfo productInfo = getProductInfo(productid);
        //Get Price from pricing-service
        //Get Stock Avail from inventory-service
        return new Product(productInfo.getProductID(), productInfo.getProductName(), productInfo.getProductDesc(), 25000, true);
    }

    private ProductInfo getProductInfo(Long productid) {
        populateProductList();
        for (ProductInfo p : productList) {
            if (productid.equals(p.getProductID())) {
                return p;
            }
        }
        return null;
    }

    private void populateProductList() {
        productList.add(new ProductInfo(101L, "iPhone", "iPhone 12"));
        productList.add(new ProductInfo(102L, "Book", "Clean Code Book"));
        productList.add(new ProductInfo(103L, "MacBook", "MacBook Air"));
    }
}
