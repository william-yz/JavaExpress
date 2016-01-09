package com.will.event;

import com.will.function.Callback;
/**
 * Created by yangwi on 1/8/2016.
 */
public interface Event {
    Event on(String event, Callback listener);
    Event once(String event, Callback listener);
    Event emit(String event, Object ...params);
    Event removeListener(String event, Callback callback);
    Event removeAllListeners(String event);

    int listenerCount(String event);
    int eventCount();
}
