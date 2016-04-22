package com.xinran.fasthttp;

import android.app.Application;
import android.content.Context;



/**
 * Created by qixinh on 16/4/22.
 */
public class QxApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

}
