package yfz.com.volleytest.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * ***************************************************************************
 * E城到家 自定义gson 网络请求
 * 我们服务器大多返回的都是 json 串，这里我把json串 借助gson 解析了。
 * ****************************************************************************
 * Authors:chris on 12/23/15 16:57
 * Email：zhangyanlongcodec@gmail.com
 */
public class GsonRequest<T> extends Request<T> {

    private final static String TAG = "==>GsonRequest";

    /**
     * class of type of response
     */
    private Class<T> mClass;

    /**
     * JSON 解析引擎
     */
    private Gson mGson;

    /**
     * result listener
     */
    private Response.Listener<T> mListener;

    /**
     * Header parameters
     */
    private Map<String, String> mHeaders;

    public GsonRequest(int method,
                       String url,
                       Class<T> clazz,
                       Response.Listener listener,
                       Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        this.mClass = clazz;
        this.mListener = listener;
        mGson = new Gson();
    }

    public GsonRequest(int method,
                       String url,
                       Class<T> clazz,
                       Map<String, String> headers,
                       Response.Listener listener,
                       Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        this.mClass = clazz;
        this.mListener = listener;
        mGson = new Gson();
        mHeaders = headers;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders != null ? mHeaders : super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.i(TAG, json);

            return Response.success(mGson.fromJson(json, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}