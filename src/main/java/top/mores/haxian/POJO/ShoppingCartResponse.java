package top.mores.haxian.POJO;

import java.util.List;

public class ShoppingCartResponse {
    private List<ShoppingCart> items;
    private double totalCartPrice;

    public ShoppingCartResponse(List<ShoppingCart> items, double totalCartPrice) {
        this.items = items;
        this.totalCartPrice = totalCartPrice;
    }

    public List<ShoppingCart> getItems() {
        return items;
    }

    public double getTotalCartPrice() {
        return totalCartPrice;
    }
}
