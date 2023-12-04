package com.boneless.code.neighborhood;

public class PaintBucket {
    private int x;
    private int y;
    private int amount;

    public PaintBucket(int x, int y, int initialAmount) {
        this.x = x;
        this.y = y;
        this.amount = initialAmount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAmount() {
        return amount;
    }

    public void decreaseAmount() {
        if (amount > 0) {
            amount--;
        }
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // Merge the provided paint bucket with this paint bucket
    public void merge(PaintBucket other) {
        if (other != null && this.x == other.x && this.y == other.y) {
            this.amount += other.amount;
        }
    }
}
