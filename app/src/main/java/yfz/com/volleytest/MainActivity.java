package yfz.com.volleytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.util.Map;

import yfz.com.volleytest.network.DataRequest;
import yfz.com.volleytest.network.RequestMap;

public class MainActivity extends AppCompatActivity implements DataRequest.RequestListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getDataRequest().init( this );

//        testRequest();
//        testGet();
//        testGet2();
//        testPost();
//        testUploadFile();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                /**
                 * 用户登录 测试
                 */
//                new LoginTask().login("13391538173" ,"a19861010");

                /**
                 * 用户权限 测试
                 */
                new UserPrivilege().getRoleInfor("廊坊");
            }
        }).start();
    }


    private void testUploadFile () {
        RequestMap params = new RequestMap();
        params.put( "key", "value" );
        params.put( "uploadedfile", new File( getFilesDir(), "uplodate.txt" ) );
        getDataRequest().post( "url", params, this, 100000 );
    }

    private void testPost () {
        RequestMap params = new RequestMap();
        params.put( "key", "value" );

        getDataRequest().post( "http://www.baidu.com", params, this, 100001 );
    }

    private void testGet () {
        getDataRequest().get( "http://www.taobao.com", this, 100002 );
    }

    @Override
    public void onSuccess ( String response, Map< String, String > headers, String url, int requestId ) {
        Log.i( "hwd", "requestId  " + requestId + "  json " + response );
//        HttpEntity httpEntity = GsonUtil.json2Obj( response, HttpEntity.class );    //如果有可用json  , 使用GsonUtil进行转换
        switch ( requestId ) {
            case 100000:
                //TODO 处理上传文件回调
                break;
            case 100001:
                //TODO 处理Post请求回调
                break;
            case 100002:
                //TODO 处理Get请求回调
                break;
            default:
                break;
        }
    }

    @Override
    public void onError ( String errorMsg, String url, int requestId ) {
        //关闭ProgressBar
        switch ( requestId ) {
            default:
                break;
        }
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        getDataRequest().cancelAllRequests();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        getDataRequest().cancelAllRequests();
    }


    protected DataRequest getDataRequest () {
        return DataRequest.getInstance();

    }


}
