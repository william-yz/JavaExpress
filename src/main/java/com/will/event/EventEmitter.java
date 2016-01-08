package com.will.event;

/**
 * Created by yangwi on 1/8/2016.
 */
public interface EventEmitter {
    EventEmitter on(String event, Listener listener);
    void emit(String event, Object[] params);
}
