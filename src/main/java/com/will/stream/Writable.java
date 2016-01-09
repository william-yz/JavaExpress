package com.will.stream;

import com.will.event.EventEmitter;

import java.io.OutputStream;

/**
 * Created by Administrator on 2016/1/9.
 */
public class Writable extends EventEmitter implements Stream{
    private OutputStream os;
}
