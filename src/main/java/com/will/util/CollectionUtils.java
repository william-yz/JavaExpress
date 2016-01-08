package com.will.util;

import java.util.Collection;

/**
 * Created by yangwi on 1/8/2016.
 */
public final class CollectionUtils {

    public static boolean isEmpty(Collection collection) {
        if (null == collection)
            return false;
        return collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

}
