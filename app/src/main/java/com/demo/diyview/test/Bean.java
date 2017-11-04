package com.demo.diyview.test;

/**
 * Created by raytine on 2017/8/16.
 */

public class Bean {
    private String date;
    private double point;

    public Bean(String date, double point) {
        this.date = date;
        this.point = point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
