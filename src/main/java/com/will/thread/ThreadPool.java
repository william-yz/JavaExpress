package com.will.thread;

import com.will.function.Callback;

import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 */
public class ThreadPool {

    List<CallbackThread> pool;
    void apply(Callback callback) {
        Thread thread = new Thread(() -> callback.apply());
        thread.start();
    }
}
