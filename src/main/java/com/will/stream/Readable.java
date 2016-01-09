package com.will.stream;

import com.will.event.EventEmitter;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/1/9.
 */
public class Readable extends EventEmitter implements Stream {
    private InputStream is;


    public int read() throws IOException {
        return is.read();
    }
}
