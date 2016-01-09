package com.will.event;

import com.will.function.Callback;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 */
public class EventTest {

    @Test
    public void canAddListener() throws InterruptedException {
        Event listeners = new EventEmitter();

        final List<String> resList = new ArrayList<>();
        listeners
                .on("start", (objects) -> {
                    resList.add(objects[0].toString());
                })
                .emit("start", "haha");
        Thread.sleep(1000);
        Assert.assertEquals(1, resList.size());
        Assert.assertEquals("haha", resList.get(0));
    }

    @Test
    public void canOnceEmitEvent() throws InterruptedException {
        Event listeners = new EventEmitter();

        final List<String> resList = new ArrayList<>();
        listeners
                .once("start", (objects) -> {
                    resList.add(objects[0].toString());
                })
                .emit("start", "haha");
        Thread.sleep(1000);
        Assert.assertEquals(1, resList.size());
        Assert.assertEquals("haha", resList.get(0));

        resList.clear();
        listeners.emit("start", "haha");
        Assert.assertEquals(0, resList.size());
    }

    @Test
    public void canCountListeners() {
        Event listener = new EventEmitter();
        listener.on("start",(objects)->{});
        listener.on("start",(objects)->{});
        listener.on("start",(objects)->{});
        listener.on("end",(objects)->{});
        listener.on("end",(objects)->{});

        listener.once("start",(objects)->{});
        listener.once("start",(objects)->{});
        listener.once("end",(objects)->{});

        Assert.assertEquals(5,listener.listenerCount("start"));
        Assert.assertEquals(3,listener.listenerCount("end"));

        Assert.assertEquals(5, EventEmitter.listenerCount(listener,"start"));
        Assert.assertEquals(3, EventEmitter.listenerCount(listener,"end"));
    }

    @Test
    public void canCountEvents() {
        Event listener = new EventEmitter();
        listener.on("one",(objects)->{});
        listener.on("two",(objects)->{});

        listener.once("start",(objects)->{});
        listener.once("end",(objects)->{});

        Assert.assertEquals(4,listener.eventCount());

        Assert.assertEquals(4, EventEmitter.eventCount(listener));
    }

    @Test
    public void canRemoveEvents() {
        Event listener = new EventEmitter();

        Callback cb1 = ($) -> {

        };

        Callback cb2 = ($) -> {

        };
        listener.on("one",cb1);
        listener.on("two",cb1);
        listener.once("one",cb1);
        listener.once("two",cb1);
        listener.on("one",cb2);
        listener.on("two",cb2);

        Assert.assertEquals(2,listener.eventCount());
        Assert.assertEquals(3,listener.listenerCount("one"));
        Assert.assertEquals(3,listener.listenerCount("two"));

        listener.removeListener("one",cb1);
        Assert.assertEquals(2,listener.eventCount());
        Assert.assertEquals(1, listener.listenerCount("one"));
        Assert.assertEquals(3,listener.listenerCount("two"));

        listener.removeListener("one",cb2);
        Assert.assertEquals(1,listener.eventCount());
        Assert.assertEquals(0, listener.listenerCount("one"));
        Assert.assertEquals(3,listener.listenerCount("two"));

        listener.removeAllListeners("two");
        Assert.assertEquals(0,listener.eventCount());
        Assert.assertEquals(0, listener.listenerCount("one"));
        Assert.assertEquals(0,listener.listenerCount("two"));
    }
}
