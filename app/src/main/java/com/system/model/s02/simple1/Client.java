package com.system.model.s02.simple1;

/**
 * Created by raytine on 2017/10/16.
 */

public class Client {
    public static void main(String[] args){
        PushObserverable pushObserverable = new PushObserverable();
        pushObserverable.register(new Person());
        pushObserverable.Update();
    }
}
