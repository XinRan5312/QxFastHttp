package com.xinran.fastokhttp.utils;

import android.util.Log;

/**
 * Created by qixinh on 16/3/30.
 */
public class L
{
    private static boolean debug = false;

    public static void e(String msg)
    {
        if (debug)
        {
            Log.e("OkHttp", msg);
        }
    }

}

