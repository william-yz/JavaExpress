package com.will.event;

import com.will.function.Callback;
import com.will.util.CollectionUtils;

import java.util.*;

/**
 * Created by yangwi on 1/8/2016.
 */

public class EventEmitter implements Event {

    private Map<String, List<Callback>> storedEvents;
    private Map<String, List<Callback>> storedOnceEvents;

    private Set<String> events;

    public EventEmitter() {
        storedEvents = new HashMap<>();
        storedOnceEvents = new HashMap<>();
        events = new HashSet<>();
    }

    @Override
    public Event on(String event, Callback listener) {
        addListener(event, listener, storedEvents);
        return this;
    }

    @Override
    public Event once(String event, Callback listener) {
        addListener(event, listener, storedOnceEvents);
        return this;
    }

    @Override
    public Event emit(String event, Object... params) {
        List<Callback> listeners = storedEvents.get(event);
        if (CollectionUtils.isNotEmpty(listeners)) {
            listeners.forEach(listener -> {
                new Thread(() -> listener.apply(params)).start();
            });
        }
        List<Callback> onceListeners = storedOnceEvents.get(event);
        if (CollectionUtils.isNotEmpty(onceListeners)) {
            onceListeners.forEach(listener -> {
                new Thread(() -> listener.apply(params)).start();
            });
            removeStoredOnceEvents(event);
        }
        return this;
    }

    @Override
    public Event removeListener(String event, Callback listenter) {
        List<Callback> listeners = storedEvents.get(event);
        if (CollectionUtils.isNotEmpty(listeners)) {
            listeners.remove(listenter);
        }
        listeners = storedOnceEvents.get(event);
        if (CollectionUtils.isNotEmpty(listeners)) {
            listeners.remove(listenter);
        }
        checkStoredEvent(event);
        return this;
    }

    @Override
    public Event removeAllListeners(String event) {
        removeStoredEvent(event);
        removeStoredOnceEvents(event);
        return this;
    }

    @Override
    public int listenerCount(String event) {
        return CollectionUtils.count(storedEvents.get(event)) + CollectionUtils.count(storedOnceEvents.get(event));
    }

    @Override
    public int eventCount() {
        return events.size();
    }

    public static int listenerCount(Event eventEmitter, String event) {
        return eventEmitter.listenerCount(event);
    }

    public static int eventCount(Event event) {
        return event.eventCount();
    }

    private void addListener(String event, Callback listener, Map<String, List<Callback>> storeEvents) {
        List<Callback> existListeners = storeEvents.get(event);
        if (CollectionUtils.isEmpty(existListeners)) {
            List<Callback> newListeners = new ArrayList<>();
            newListeners.add(listener);
            storeEvents.put(event, newListeners);
        } else {
            existListeners.add(listener);
        }
        events.add(event);
        this.emit("newListener", listener);
    }

    private void removeStoredEvent(String event) {
        storedEvents.remove(event);
        checkStoredEvent(event);
    }

    private void removeStoredOnceEvents(String event) {
        storedOnceEvents.remove(event);
        checkStoredEvent(event);
    }

    private void checkStoredEvent(String event) {
        boolean needRemoveEvent = CollectionUtils.isEmpty(storedEvents.get(event)),
                needRemoveOnceEvent = CollectionUtils.isEmpty(storedOnceEvents.get(event));

        if (needRemoveEvent) {
            storedEvents.remove(event);
        }
        if (needRemoveOnceEvent) {
            storedOnceEvents.remove(event);
        }
        if (needRemoveEvent && needRemoveOnceEvent) {
            events.remove(event);
        }
    }
}


