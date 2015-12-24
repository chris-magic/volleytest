package yfz.com.volleytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import yfz.com.volleytest.network.RequestManager;
import yfz.com.volleytest.network2.manager.LoadController;
import yfz.com.volleytest.network2.manager.RequestMap;

public class MainActivity extends AppCompatActivity implements yfz.com.volleytest.network2.manager.RequestManager.RequestListener {

    public static final String TAG = "MainActivity";
    private LoadController mLoadController;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        yfz.com.volleytest.network2.manager.RequestManager.getInstance().init( this );

//        testRequest();
//        testGet();
        testGet2();
        testPost();
        testUploadFile();
    }

    private void testUploadFile () {

    }

    private void testPost () {
        RequestMap requestMap = new RequestMap();
        requestMap.put( "key", "value" );
        yfz.com.volleytest.network2.manager.RequestManager.getInstance().post( "http://www.baidu.com", requestMap, this, 100002 );
    }

    private void testGet2 () {
        mLoadController = yfz.com.volleytest.network2.manager.RequestManager.getInstance().get( "http://www.baidu.com", this, 100001 );
    }

    @Override
    public void onRequest () {
        Log.i( "hwd", "request send ..." );
    }

    @Override
    public void onSuccess ( String response, Map< String, String > headers, String url, int requestId ) {

//        HttpEntity httpEntity = GsonUtil.json2Obj( response, HttpEntity.class );    //如果有可用json  , 使用GsonUtil进行转换

        switch ( requestId ) {
            case 100001:
                Log.i( "hwd", response );
                break;
            case 10002:
                break;
            default:
                break;
        }
    }

    @Override
    public void onError ( String errorMsg, String url, int requestId ) {
        switch ( requestId ) {
            case 10001:
                break;
            case 10002:
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        mLoadController.cancel();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        mLoadController.cancel();//不合理
    }


//    private void testGet () {
//        VolleyWrapper wrapper = new VolleyWrapper( Request.Method.GET, "http://www.baidu.com", Void.class, new Response.Listener() {
//            @Override
//            public void onResponse ( Object response ) {
//                if ( response == null ) {
//                    Log.i( "hwd", "null" );
//                }
//                Log.i( "hwd", response.toString() );
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse ( VolleyError error ) {
//                Log.i( "hwd", "错误    " + error.getMessage() );
//            }
//        } );
//        wrapper.sendRequest( this.getClass().getSimpleName() );
//    }

    private void testRequest () {
        // 获取网络请求主类
        RequestManager requestManager = RequestManager.getInstance( this );

        StringRequest stringRequest = new StringRequest( "http://www.baidu.com",

                new Response.Listener< String >() {

                    @Override
                    public void onResponse ( String response ) // 服务器端返回的字符串
                    {
                        Log.e( "TAG", response );

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse ( VolleyError error ) {
                        Log.e( "TAG", error.getMessage(), error );
                    }
                } );


        //
        requestManager.addToRequestQueue( stringRequest, "tag" );


//        VolleyWrapper volleyWrapper=new VolleyWrapper<>(Request.Method.GET,
//                "http://www.baidu.com" ,
//                null,new RequestSuccessListener<T>(),new RequestErrorListener());
        //volleyWrapper.addUrlParameter(urlParams); // 添加请求参数
        //volleyWrapper.sendGETRequest(tag);

    }

}
