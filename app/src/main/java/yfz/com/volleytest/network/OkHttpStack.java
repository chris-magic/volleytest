package yfz.com.volleytest.network;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

/**
 * ***************************************************************************
 * E城到家  OkHttpStack 协议栈
 * An {@link com.android.volley.toolbox.HttpStack HttpStack} implementation which
 * uses OkHttp as its transport.
 * ****************************************************************************
 * Authors:chris on 12/23/15 16:25
 * Email：zhangyanlongcodec@gmail.com
 */
public class OkHttpStack extends HurlStack {

    private final OkUrlFactory okUrlFactory;

    public OkHttpStack() {
        this(new OkUrlFactory(new OkHttpClient()));
    }

    public OkHttpStack(OkUrlFactory okUrlFactory) {
        if (okUrlFactory == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.okUrlFactory = okUrlFactory;
    }

}
