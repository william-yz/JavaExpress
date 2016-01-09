package com.will.thread;

import com.will.function.Callback;

/**
 * Created by Administrator on 2016/1/9.
 */
public class CallbackThread implements Runnable{
    private Callback callback;

    public CallbackThread(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        callback.apply();
    }
}
