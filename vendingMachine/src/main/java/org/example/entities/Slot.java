package org.example.entities;

import java.util.List;
import java.util.UUID;

public class Slot extends Dimension {
    private final List<Product> products;
    String id;
    private final Integer slotLimit;
    public Slot(Double length, Double width, Double height, List<Product> products, Integer slotLimit) {
        super(length, width, height);
        id = UUID.randomUUID().toString();
        this.products = products;
        this.slotLimit = slotLimit;
    }
    public Integer getSlotLimit() { return slotLimit; }
    public List<Product> getProducts() { return products; }
    public String getId() { return id; }

    // Please note that we'll only allow restocking of one object type per slot
    public List<Product> emptySlot() {
        List <Product> removedProducts = List.copyOf(products);
        products.clear();
        return removedProducts;
    }
    public void restockSlot(List<Product> newProducts) {
        if (!newProducts.get(0).fitsIn(this)) {
            System.out.println("Can't fit products in this slot");
            return;
        } else if (products.size() + newProducts.size() > slotLimit) {
            System.out.println("Slot limit exceeded");
            return;
        }
        for (Product product: newProducts)
            products.add(product);
    }
}
