package yfz.com.volleytest.network2.manager;

import java.util.Map;

public interface LoadListener {
	
	void onStart ();

	void onSuccess( byte[] data, Map< String, String > headers, String url, int actionId );

	void onError( String errorMsg, String url, int actionId );
}
