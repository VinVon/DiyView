package com.system.model.s01.simple1;

import android.util.Log;

/**
 * 代理模式---被代理对象
 * Created by raytine on 2017/10/16.
 */

public class User implements IBank {
    private  String name ;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void applyCard() {

        System.out.println("用户操作");
    }
}
