package yfz.com.volleytest;

import android.app.Application;
import android.content.Context;

/**
 * **************************************************************
 * E城到家
 * <p/>
 * **************************************************************
 * Authors:huweidong on 2015/12/24 14:45
 * Email：huwwds@gmail.com
 */
public class EnApplication extends Application {

    public static EnApplication mInstance;

    @Override
    public void onCreate () {
        super.onCreate();
        mInstance = this;
    }

    public static Context getEnApplicationContext () {
        return mInstance;
    }
}
