package org.example.entities;

import org.example.strategy.SlotStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    Map<String, Product> products;
    Map<String, List<Slot>> productSlots;
    Map<String, Integer> productQuantities;
    SlotStrategy slotStrategy;

    public Inventory(SlotStrategy slotStrategy) {
        this.products = new HashMap<>();
        this.productSlots = new HashMap<>();
        this.productQuantities = new HashMap<>();
        slotStrategy = slotStrategy;
    }

    public void restock(List<Product> restockProducts, Slot slot) {
        slot.restockSlot(restockProducts);
        if (products.get(restockProducts.get(0).getItemCode()) == null) {
            products.put(restockProducts.get(0).getItemCode(), restockProducts.get(0));
            productSlots.put(restockProducts.get(0).getItemCode(), List.of(slot));
            productQuantities.put(restockProducts.get(0).getItemCode(), restockProducts.size());
        } else {
            if (!productSlots.get(restockProducts.get(0).getItemCode()).contains(slot))
                productSlots.get(restockProducts.get(0).getItemCode()).add(slot);
            productQuantities.put(restockProducts.get(0).getItemCode(), productQuantities.get(restockProducts.get(0).getItemCode()) + restockProducts.size());
        }
    }

    public Boolean isProductAvailable(String itemCode) {
        return productQuantities.get(itemCode) != null && productQuantities.get(itemCode) > 0;
    }

    public Product deductProduct(Slot slot) {
        String itemCode = slot.getProducts().get(0).getItemCode();
        if (slot.getProducts().size() == 0) {
            System.out.println("Product not available");
            return null;
        }
        if (productQuantities.get(itemCode) == 1) {
            productQuantities.remove(itemCode);
            products.remove(itemCode);
        }
        productQuantities.put(itemCode, productQuantities.get(itemCode) - 1);
        Product dispensedProduct = slot.getProducts().remove(0);
        if (slot.getProducts().size() == 0)
            productSlots.get(itemCode).remove(slot);
        return dispensedProduct;
    }

    public Product getProduct(String itemCode) {
        return products.get(itemCode);
    }

    public Slot getSlotForProduct(String itemCode) {
        return slotStrategy.selectSlot(productSlots.get(itemCode));
    }
}
