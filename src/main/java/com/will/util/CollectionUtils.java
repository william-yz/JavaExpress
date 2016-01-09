package com.will.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by yangwi on 1/8/2016.
 */
public final class CollectionUtils {

    public static boolean isEmpty(Collection collection) {
        if (null == collection)
            return true;
        return collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map collection) {
        if (null == collection)
            return true;
        return collection.isEmpty();
    }

    public static boolean isNotEmpty(Map collection) {
        return !isEmpty(collection);
    }

    public static int count(Collection collection) {
        if (isEmpty(collection))
            return 0;
        return collection.size();
    }

    public static int count(Map collection) {
        if (isEmpty(collection))
            return 0;
        return collection.size();
    }
}
