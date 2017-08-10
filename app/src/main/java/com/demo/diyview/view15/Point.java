package com.demo.diyview.view15;

/**
 * Created by raytine on 2017/8/9.
 */

public class Point {
    private int centX;
    private int centY;
    private int index;

    private int STATUS_MORMAL = 1;
    private int STATUS_PRESSED = 2;
    private int STATUS_ERROE = 3;
    private int status = STATUS_MORMAL;

    public Point(int centX, int centY, int index) {
        this.centX = centX;
        this.centY = centY;
        this.index = index;
    }

    public void setSTATUS_MORMAL() {
        this.status = STATUS_MORMAL;
    }

    public void setSTATUS_PRESSED() {
        this.status = STATUS_PRESSED;
    }

    public void setSTATUS_ERROE() {
        this.status = STATUS_ERROE;
    }

    public boolean getSTATUS_MORMAL() {
        return status ==STATUS_MORMAL;
    }

    public boolean getSTATUS_PRESSED() {
        return status ==STATUS_PRESSED;
    }

    public boolean getSTATUS_ERROE() {
        return status == STATUS_ERROE;
    }

    public int getCentX() {
        return centX;
    }

    public int getCentY() {
        return centY;
    }

    public int getIndex() {
        return index;
    }
}
