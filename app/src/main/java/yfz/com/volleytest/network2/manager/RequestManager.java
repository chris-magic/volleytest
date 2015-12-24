package yfz.com.volleytest.network2.manager;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class RequestManager {

	private static final int TIMEOUT_COUNT = 10 * 1000;

	private static final int RETRY_TIMES = 1;

	private volatile static RequestManager INSTANCE = null;

	private RequestQueue mRequestQueue = null;

	public interface RequestListener {

		void onRequest ();

		void onSuccess( String response, Map< String, String > headers,
						String url, int requestId );

		void onError( String errorMsg, String url, int requestId );
	}

	private RequestManager() {

	}

	public void init(Context context) {
		this.mRequestQueue = Volley.newRequestQueue( context );
	}

	public static RequestManager getInstance() {
		if (null == INSTANCE) {
			synchronized (RequestManager.class) {
				if (null == INSTANCE) {
					INSTANCE = new RequestManager();
				}
			}
		}
		return INSTANCE;
	}

	public RequestQueue getRequestQueue() {
		return this.mRequestQueue;
	}

	public LoadController get(String url, RequestListener requestListener,
			int requestId) {
		return this.get(url, requestListener, true, requestId);
	}

	public LoadController get(String url, RequestListener requestListener,
			boolean shouldCache, int requestId) {
		return this.request( Method.GET, url, null, null, requestListener,
				shouldCache, TIMEOUT_COUNT, RETRY_TIMES, requestId);
	}

	public LoadController post(final String url, Object data,
			final RequestListener requestListener, int requestId) {
		return this.post(url, data, requestListener, false, TIMEOUT_COUNT,
				RETRY_TIMES, requestId);
	}

	public LoadController post(final String url, Object data,
			final RequestListener requestListener, boolean shouldCache,
			int timeoutCount, int retryTimes, int requestId) {
		return request( Method.POST, url, data, null, requestListener,
				shouldCache, timeoutCount, retryTimes, requestId);
	}

	public LoadController request(int method, final String url, Object data,
			final Map<String, String> headers,
			final RequestListener requestListener, boolean shouldCache,
			int timeoutCount, int retryTimes, int requestId) {
		return this.sendRequest(method, url, data, headers,
				new RequestListenerHolder(requestListener), shouldCache,
				timeoutCount, retryTimes, requestId);
	}

	public LoadController sendRequest(int method, final String url, Object data,
			final Map<String, String> headers,
			final LoadListener requestListener, boolean shouldCache,
			int timeoutCount, int retryTimes, int requestId) {
		if (requestListener == null)
			throw new NullPointerException();

		final ByteArrayLoadController loadController = new ByteArrayLoadController(
				requestListener, requestId);

		Request<?> request = null;
		if (data != null && data instanceof RequestMap) {
			request = new ByteArrayRequest( Method.POST, url, data,
					loadController, loadController);
			request.setShouldCache(false);
		} else {
			request = new ByteArrayRequest(method, url, data, loadController,
					loadController);
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

		loadController.bindRequest(request);

		if (this.mRequestQueue == null)
			throw new NullPointerException();
		requestListener.onStart();
		this.mRequestQueue.add(request);

		return loadController;
	}

}
