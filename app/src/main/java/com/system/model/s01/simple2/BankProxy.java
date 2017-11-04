package com.system.model.s01.simple2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理设计模式
 * Created by raytine on 2017/10/16.
 */

public class BankProxy implements InvocationHandler {
    private Object object;

    public BankProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        Object invoke = method.invoke(object,args);
        System.out.println("完毕");
        return invoke;
    }
}
