package yfz.com.volleytest.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

/**
 * ***************************************************************************
 * E城到家 网络请求管理类[主类]
 * ****************************************************************************
 * Authors:chris on 12/23/15 16:38
 * Email：zhangyanlongcodec@gmail.com
 */
public class RequestManager {

    private static final String TAG = "RequestManager";


    /**
     * 实例
     */
    private static RequestManager sInstance = null;

    /**
     * 网络请求队列
     */
    private RequestQueue mRequestQueue;

    /**
     * app 上下文句柄
     */
    private Context mAppContext;

    private RequestManager ( Context appContext ) {
        mAppContext = appContext;
        mRequestQueue = Volley.newRequestQueue( mAppContext, new OkHttpStack() );
    }

    public static RequestManager getInstance ( Context context ) {

        if ( null == sInstance ) {
            synchronized ( RequestManager.class ) {
                if ( null == sInstance ) {
                    sInstance = new RequestManager( context.getApplicationContext() );
                }
            }
        }
        return sInstance;
    }


    public RequestQueue getRequestQueue () {
        return mRequestQueue;
    }

    public < T > void addToRequestQueue ( Request< T > req, String tag ) {

        req.setTag( TextUtils.isEmpty( tag ) ? TAG : tag );

        VolleyLog.d( "Adding request to queue: %s", req.getUrl() );
        getRequestQueue().add( req );
    }

    public void cancelRequestByTag ( String tag ) {
        getRequestQueue().cancelAll( tag );
    }


     /*  封装volley 的一些网络访问接口 --》 post ,get 方法  ,这些方法里面就会带 RequestListener 参数*/

}
