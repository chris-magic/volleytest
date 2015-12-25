package yfz.com.volleytest.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public class LoadController implements
        Listener< NetworkResponse >, ErrorListener {

    private RequestListenerHolder mOnLoadListener;

    private int mRequestId = 0;

    protected Request< ? > mRequest;


    public LoadController ( RequestListenerHolder requestListener, int requestId ) {
        this.mOnLoadListener = requestListener;
        this.mRequestId = requestId;
    }

    public void bindRequest ( Request< ? > request ) {
        this.mRequest = request;
    }

    public void cancel () {
        if ( this.mRequest != null ) {
            this.mRequest.cancel();
        }
    }

    protected String getOriginUrl () {
        return this.mRequest.getOriginUrl();
    }

    @Override
    public void onErrorResponse ( VolleyError error ) {
        String errorMsg = null;
        if ( error.getMessage() != null ) {
            errorMsg = error.getMessage();
        } else {
            try {
                errorMsg = "Server Response Error ("
                        + error.networkResponse.statusCode + ")";
            } catch ( Exception e ) {
                errorMsg = "Server Response Error";
            }
        }
        this.mOnLoadListener.onError( errorMsg, getOriginUrl(), this.mRequestId );
        DataRequest.getInstance().cancelRequest( this.mRequestId );
    }

    @Override
    public void onResponse ( NetworkResponse response ) {
        this.mOnLoadListener.onSuccess( response.data, response.headers,
                getOriginUrl(), this.mRequestId );
        DataRequest.getInstance().cancelRequest( this.mRequestId );
    }
}