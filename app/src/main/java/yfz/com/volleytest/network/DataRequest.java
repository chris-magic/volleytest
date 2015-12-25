package yfz.com.volleytest.network;

import android.content.Context;
import android.util.SparseArray;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yfz.com.volleytest.urls.URLS;

public final class DataRequest {

    /** 测试模式 **/
    public static final boolean TAG_DEBUG = true;

    private static final int TIMEOUT_COUNT = 10 * 1000;

    private static final int RETRY_TIMES = 1;

    private volatile static DataRequest INSTANCE = null;

    private RequestQueue mRequestQueue = null;

    private SparseArray<LoadController> mControllerArray = new SparseArray<>();

    public interface RequestListener {

        void onSuccess(String response, Map<String, String> headers,
                       String url, int requestId);

        void onError(String errorMsg, String url, int requestId);
    }

    private DataRequest() {
    }

    public void init(Context context) {
        this.mRequestQueue = Volley.newRequestQueue(context);
    }

    public static DataRequest getInstance() {
        if (null == INSTANCE) {
            synchronized (DataRequest.class) {
                if (null == INSTANCE) {
                    INSTANCE = new DataRequest();
                }
            }
        }
        return INSTANCE;
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public void get(String url, RequestListener requestListener,
                    int requestId) {
        this.get(url, requestListener, true, requestId);
    }

    public void get(String url, RequestListener requestListener,
                    boolean shouldCache, int requestId) {
        this.request(Method.GET, url, null, null, requestListener,
                shouldCache, TIMEOUT_COUNT, RETRY_TIMES, requestId);
    }


    /**
     * 网络请求post
     *
     * @param url
     *          网络请求地址
     * @param data
     *          请求参数
     * @param requestListener
     *          请求监听函数
     * @param requestId
     *          请求id
     */
    public void post(final String url, Object data,
                                     final RequestListener requestListener, int requestId) {
        this.post(url, data, requestListener, false, TIMEOUT_COUNT,
                RETRY_TIMES, requestId);
    }
    /**
     * 网络post 请求, 内部封装了定制的parameter 参数集合
     *
     * @param url
     *          网络请求地址
     * @param paramsData
     *          请求参数
     * @param requestListener
     *          请求监听函数
     * @param requestId
     *          请求id
     */
    public void postWithCustomParams(final String url, Object paramsData,
                     final RequestListener requestListener, int requestId) {

        Object data = paramsData;
        if (paramsData instanceof RequestMap) {
            data = ((RequestMap) paramsData).getUrlParams();
        }
        String jsonData = GsonUtil.obj2json(data).toString();

        // 请求参数集
        RequestMap requestParamsMap = new RequestMap();
        if (TAG_DEBUG) {
            requestParamsMap.put("jsonData", jsonData);
            requestParamsMap.put("debug", "true");

        } else {
//            requestParamsMap.put("debug",
//                    XXTEATool.encryptString(jsonData));
        }
        // add 统一传 smartId
        //requestParamsMap.put("smartId", UserUtil.getSmartId(context));
        requestParamsMap.put("v", URLS.REQVERSION);

        this.post(url, requestParamsMap, requestListener, false, TIMEOUT_COUNT,
                RETRY_TIMES, requestId);
    }



    public void post(final String url, Object data,
                     final RequestListener requestListener, boolean shouldCache,
                     int timeoutCount, int retryTimes, int requestId) {
        request(Method.POST, url, data, null, requestListener,
                shouldCache, timeoutCount, retryTimes, requestId);
    }

    public void request(int method, final String url, Object data,
                        final Map<String, String> headers,
                        final RequestListener requestListener, boolean shouldCache,
                        int timeoutCount, int retryTimes, int requestId) {
        this.sendRequest(method, url, data, headers,
                new RequestListenerHolder(requestListener), shouldCache,
                timeoutCount, retryTimes, requestId);
    }

    public void sendRequest(int method, final String url, Object data,
                            final Map<String, String> headers,
                            final RequestListenerHolder requestListener, boolean shouldCache,
                            int timeoutCount, int retryTimes, int requestId) {
        if (requestListener == null)
            throw new NullPointerException();

        final LoadController loadController = new LoadController(requestListener, requestId);

        Request<?> request = null;
        if (data != null && data instanceof RequestMap) {//传参类型为RequestMap , 则POST请求
            request = new ByteArrayRequest(Method.POST, url, data,
                    loadController);
            request.setShouldCache(false);
        } else {
            request = new ByteArrayRequest(method, url, data, loadController);
            request.setShouldCache(shouldCache);
        }

        if (headers != null && !headers.isEmpty()) {
            try {
                request.getHeaders().putAll(headers);
            } catch (AuthFailureError e) {
                e.printStackTrace();
            }
        }

        RetryPolicy retryPolicy = new DefaultRetryPolicy(timeoutCount,
                retryTimes, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(retryPolicy);

        loadController.bindRequest(request);//将request绑定到controller

        if (this.mRequestQueue == null)
            throw new NullPointerException();

        this.mRequestQueue.add(request);

        mControllerArray.put(requestId, loadController);
    }

    public final void cancelRequest(int requestId) {
        LoadController loadController = mControllerArray.get(requestId);
        mControllerArray.delete(requestId);
        if (loadController != null) {
            loadController.cancel();
        }
    }

    public final void cancelRequests(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        cancelRequests(array);
    }

    public final void cancelRequests(int[] array) {
        for (int requestId : array) {
            cancelRequest(requestId);
        }
    }

    public final void cancelAllRequests() {
        for (int i = 0; i < mControllerArray.size(); i++) {
            cancelRequest(mControllerArray.keyAt(i));
        }
    }

}
