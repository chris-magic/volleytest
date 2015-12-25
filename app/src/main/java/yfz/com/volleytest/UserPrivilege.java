package yfz.com.volleytest;

import android.util.Log;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import yfz.com.volleytest.network.DataRequest;
import yfz.com.volleytest.network.GsonUtil;
import yfz.com.volleytest.network.RequestMap;
import yfz.com.volleytest.urls.URLS;
import yfz.com.volleytest.utils.RSAUtils;

/**
 * ***************************************************************************
 * E城到家 Home 页面，这里主要是实现了底部菜单栏
 * ****************************************************************************
 * Authors:chris on 12/25/15 11:10
 * Email：zhangyanlongcodec@gmail.com
 */
public class UserPrivilege implements  DataRequest.RequestListener{

    /**
     * 定义网络请求requestid
     */
    public static final int REQUEST_ID_GET_ROLE_INFOR = 0x1;


    /**
     * 获取权限管理接口
     */
    public void getRoleInfor(String cityName) {

//
//        // 参数 用户名+密码
//        RequestMap requestMap = new RequestMap();
//        // 调试模式要加
//        requestMap.put("cityName", cityName);

        HashMap map = new HashMap();
        map.put("cityName", cityName);
        // 发起网络请求
        DataRequest.getInstance().postWithCustomParams(URLS.GET_ROLER_INFO_URL,
                map,
                this,
                REQUEST_ID_GET_ROLE_INFOR);

    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int requestId) {
        Log.i("===", "__in onRequest success function");

        try {
            JSONArray mJsonArray = new JSONArray(response);
            for (int i = 0; i < mJsonArray.length(); i++) {
                // 获取第k个权限JSON格式
                String data = mJsonArray.get(i).toString();
                //roleDataList.add(data);
            }
            //Handler.sendEmptyMessage(Constants.DB_READ_SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onError(String errorMsg, String url, int requestId) {
        Log.i("===", "__in onRequest onError function");
    }



}
