package com.system.model.s01.simple1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.diyview.R;
import com.demo.diyview.view12.Activity12;

/**
 * Created by raytine on 2017/10/16.
 */

public class Client {
    public static void main(String[] args){
        User man = new User("hehe");
        BankWorker salesman = new BankWorker(man);
        salesman.applyCard();
    }
}
