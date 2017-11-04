package com.system.model.s02.simple1;

import java.util.Observer;

/**
 * Created by raytine on 2017/10/16.
 */

public interface IObserverable {
    public void register(IObserver o);
    public void Unregister(IObserver o);
    public void Update();
}
