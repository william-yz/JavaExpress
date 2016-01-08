package com.will.event;

import com.will.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangwi on 1/8/2016.
 */

public class AbstractEventEmitter implements EventEmitter{

    private Map<String, List<Listener>> events;

    @Override
    public EventEmitter on(String event, Listener listener) {
        List<Listener> existListener = events.get(event);
        if (CollectionUtils.isEmpty(existListener)) {
            List<Listener> newListeners = new ArrayList<>();
            newListeners.add(listener);
            events.put(event, newListeners);
        } else {
            existListener.add(listener);
        }
        return this;
    }

    @Override
    public void emit(String event, Object[] params) {
        List<Listener> listeners = events.get(event);
        if (CollectionUtils.isEmpty(listeners)) {
            return;
        } else {
            listeners.forEach(listener -> {

            });
        }
    }


}


