package com.btcbrunch;

public class Candlestick {

    long openTime;
    float high;
    float low;
    float open;
    float close;

    Candlestick(long openTime, float open, float high, float low, float close) {
        this.openTime = openTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }


    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }
}
