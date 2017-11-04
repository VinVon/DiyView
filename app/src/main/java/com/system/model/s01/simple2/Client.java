package com.system.model.s01.simple2;


import java.lang.reflect.Proxy;

/**
 * 动态代理
 * Created by raytine on 2017/10/16.
 */

public class Client {

    public static void main(String[] args) {
        User haha = new User();
        /**
         * 获取接口实例
         */
        IBank mBanck = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader(), new Class<?>[]{IBank.class}, new BankProxy(haha));
        mBanck.applyCard();
        mBanck.deleCard();
    }
}
