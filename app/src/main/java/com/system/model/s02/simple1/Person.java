package com.system.model.s02.simple1;

import java.net.SocketPermission;
import java.net.StandardProtocolFamily;

/**
 * Created by raytine on 2017/10/16.
 */

public class Person implements IObserver {

    @Override
    public void update(String s) {
        System.out.println(s);
    }
}
