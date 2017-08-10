package com.demo.diyview.view16;

/**
 * Material Design 01
 * Created by raytine on 2017/8/10.
 */

public class Fruit {
    private  String name;
    private int drawable;

    public Fruit(String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
