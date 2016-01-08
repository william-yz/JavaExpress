package com.will.event;

/**
 * Created by yangwi on 1/8/2016.
 */

@FunctionalInterface
public interface Listener {
    void listener(Object[] params);
}
