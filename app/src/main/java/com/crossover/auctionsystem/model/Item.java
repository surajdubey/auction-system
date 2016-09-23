package com.crossover.auctionsystem.model;

/**
 * Created by suraj on 23/9/16.
 */

public class Item {

    public static final int ITEM_NOT_SOLD = 0;
    public static final int ITEM_SOLD = 1;

    private int itemId;
    private String itemName;
    private String itemDescription;
    private int minimumBidAmount;
    private int targetBidAmount;
    private boolean itemSold;

    private boolean sellerName;
    private boolean currentMaximumBidAmount;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getMinimumBidAmount() {
        return minimumBidAmount;
    }

    public void setMinimumBidAmount(int minimumBidAmount) {
        this.minimumBidAmount = minimumBidAmount;
    }

    public int getTargetBidAmount() {
        return targetBidAmount;
    }

    public void setTargetBidAmount(int targetBidAmount) {
        this.targetBidAmount = targetBidAmount;
    }

    public boolean isItemSold() {
        return itemSold;
    }

    public void setItemSold(int itemSoldInt) {
        this.itemSold = (itemSoldInt == ITEM_SOLD);
    }

    public boolean isSellerName() {
        return sellerName;
    }

    public void setSellerName(boolean sellerName) {
        this.sellerName = sellerName;
    }

    public boolean isCurrentMaximumBidAmount() {
        return currentMaximumBidAmount;
    }

    public void setCurrentMaximumBidAmount(boolean currentMaximumBidAmount) {
        this.currentMaximumBidAmount = currentMaximumBidAmount;
    }
}
