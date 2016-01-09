package com.will.function;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2016/1/9.
 */

@FunctionalInterface
public interface Callback {

    void apply(Object ...params);

    default Callback andThen(Callback after) {
        Objects.requireNonNull(after);
        return (Object ...params) -> {
            apply(params);
            after.apply(params);
        };
    }
}
