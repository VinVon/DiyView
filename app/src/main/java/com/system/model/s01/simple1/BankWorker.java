package com.system.model.s01.simple1;

/**
 * 代理模式--代理对象
 * Created by raytine on 2017/10/16.
 */

public class BankWorker implements IBank{
    private User mUser;

    public BankWorker(User mUser) {
        this.mUser = mUser;
    }

    @Override
    public void applyCard() {
        System.out.println("员工开始受理");
        mUser.applyCard();
        System.out.println("员工操作完毕");
    }
}
