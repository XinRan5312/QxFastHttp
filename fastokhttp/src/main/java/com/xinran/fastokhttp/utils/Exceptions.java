package com.xinran.fastokhttp.utils;

/**
 * Created by qixinh on 16/3/30.
 */
public class Exceptions
{
    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }


}
