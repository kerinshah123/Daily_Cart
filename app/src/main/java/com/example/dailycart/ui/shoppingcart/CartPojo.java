package com.example.dailycart.ui.shoppingcart;
/**
 *
 * @author Harsh  Shah
 */
public class CartPojo {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String quantity;
    String status;

}
