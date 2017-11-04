package com.system.model.s01.simple2;


/**
 * 代理模式---被代理对象
 * Created by raytine on 2017/10/16.
 */

public class User implements IBank {


    @Override
    public void applyCard() {
        System.out.println("用户操作");
    }

    @Override
    public void deleCard() {
        System.out.println("用户操作");
    }
}
