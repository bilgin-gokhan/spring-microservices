package com.example.inventoryservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    List<Inventory> inventoryList = new ArrayList<Inventory>();

    @GetMapping("/inventory/{productid}")
    public Inventory getInventoryetails(@PathVariable Long productid) {
        Inventory inventory = getInventoryInfo(productid);
        return inventory;
    }

    private Inventory getInventoryInfo(Long productid) {
        populateInventoryList();
        for (Inventory i : inventoryList) {
            if (productid.equals(i.getProductID())) {
                return i;
            }
        }
        return null;
    }

    private void populateInventoryList() {
        inventoryList.add(new Inventory(301L, 101L, true));
        inventoryList.add(new Inventory(302L, 102L, true));
        inventoryList.add(new Inventory(303L, 103L, false));
    }
}
