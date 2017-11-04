package com.system.model.fanxing.class_fanxing;

/**
 * Created by raytine on 2017/10/19.
 */

public class Client     {
    public static void main(String[] args) {
        Demo<String> stirngDemo = new Demo<String>();
        stirngDemo.setObject("hello world");
        System.out.println(stirngDemo.getObject());
        Demo<Integer> Demo = new Demo<Integer>();
        Demo.setObject(3);
        System.out.println(stirngDemo.getObject());
    }
}
