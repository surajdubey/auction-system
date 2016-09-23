package com.crossover.auctionsystem.model;

/**
 * Created by suraj on 23/9/16.
 */

public class Seller {
    private int sellId;
    private int userId;
    private int itemId;

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
