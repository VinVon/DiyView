package com.demo.diyview.view15;

import org.jetbrains.annotations.NotNull;

/**
 * Created by raytine on 2017/8/8.
 */

public class MathUtil {
    public static boolean checkInRound(float sx,float sy , float r ,float x,float y){

        return Math.sqrt((sx-x)*(sx-x)+(sy-y)*(sy-y))<r;
    }

    @NotNull
    public static int distance(double toDouble, double toDouble1, double toDouble2, double toDouble3) {
        return (int) Math.sqrt((toDouble-toDouble2)*(toDouble-toDouble2)+(toDouble1-toDouble3)*(toDouble1-toDouble3));
    }
}
