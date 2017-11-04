package com.system.model.s02.simple1;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by raytine on 2017/10/16.
 */

public class PushObserverable implements IObserverable {
    private ArrayList<IObserver> observers;

    public PushObserverable() {
        observers = new ArrayList<>();
    }

    @Override
    public void register(IObserver o) {
        observers.add(o);
    }

    @Override
    public void Unregister(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void Update() {
        for (IObserver observer : observers) {
            observer.update("推送一篇文章");
        }
    }
}
