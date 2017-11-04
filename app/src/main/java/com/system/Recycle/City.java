package com.system.Recycle;

/**
 * Created by raytine on 2017/11/4.
 */

public class City {
    private String province;
    private String name;

    public City(String province, String name) {
        this.province = province;
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
