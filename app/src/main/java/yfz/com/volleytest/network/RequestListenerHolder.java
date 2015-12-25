package yfz.com.volleytest.network;

import android.app.Activity;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Map;

public class RequestListenerHolder {

    private static final String CHARSET_UTF_8 = "UTF-8";

    private WeakReference< DataRequest.RequestListener > mRequestListenerRef;

    private DataRequest.RequestListener mRequestListener;

    public RequestListenerHolder ( DataRequest.RequestListener requestListener ) {
        if ( requestListener instanceof Activity ) {
            this.mRequestListenerRef = new WeakReference<>(
                    requestListener );
        } else {
            this.mRequestListener = requestListener;
        }
    }

    public void onSuccess ( byte[] data, Map< String, String > headers, String url,
                            int requestId ) {
        String parsed = null;
        try {
            parsed = new String( data, CHARSET_UTF_8 );
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        }

        if ( mRequestListenerRef != null ) {
            DataRequest.RequestListener requestListener = mRequestListenerRef.get();
            if ( requestListener != null ) {
                requestListener.onSuccess( parsed, headers, url, requestId );
                return;
            }
        }

        if ( this.mRequestListener != null ) {
            this.mRequestListener.onSuccess( parsed, headers, url, requestId );
        }
    }

    public void onError ( String errorMsg, String url, int requestId ) {
        if ( mRequestListenerRef != null ) {
            DataRequest.RequestListener requestListener = mRequestListenerRef.get();
            if ( requestListener != null ) {
                requestListener.onError( errorMsg, url, requestId );
                return;
            }
        }

        if ( this.mRequestListener != null ) {
            this.mRequestListener.onError( errorMsg, url, requestId );
        }
    }
}