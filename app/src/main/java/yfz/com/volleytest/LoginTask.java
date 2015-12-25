package yfz.com.volleytest;

import android.util.Log;

import org.json.JSONObject;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import yfz.com.volleytest.network.DataRequest;
import yfz.com.volleytest.network.RequestMap;
import yfz.com.volleytest.urls.URLS;
import yfz.com.volleytest.utils.RSAUtils;

/**
 * ***************************************************************************
 * E城到家 用户登录测试
 * ****************************************************************************
 * Authors:chris on 12/25/15 10:15
 * Email：zhangyanlongcodec@gmail.com
 */
public class LoginTask implements  DataRequest.RequestListener{

    /**
     * 在这里可以定义网络请求的 request id .
     */

    // login

    public  void login(String... params){
        // 标志请求成功或者失败信息
        String resultFlag = "";
        // 用于保存后台返回的数据
        String result = "";
        try {
            // 使用模和指数生成公钥和私钥
            RSAPublicKey pubKey = RSAUtils.getPublicKey(
                    RSAUtils.PUCLIC_KEY_Modulus, RSAUtils.PUCLIC_Exponent);
            RSAPrivateKey priKey = RSAUtils.getPrivateKey(
                    RSAUtils.PUCLIC_KEY_Modulus, RSAUtils.PRIVATE_Exponent);

            // 获取请求服务器地址
            String requestUrl = URLS.getServerUrl() + URLS.USER_LOGIN;

            // 参数 用户名+密码
            RequestMap requestMap = new RequestMap();
            requestMap.put("txtUsername", RSAUtils.encryptByPublicKey(params[0], pubKey));
            requestMap.put("txtPassword", RSAUtils.encryptByPublicKey(params[1], pubKey));

            // 发起网络请求
            DataRequest.getInstance().post( requestUrl, requestMap, this, 100002 );

//            HttpUtils httputils = new HttpUtils();
//            httputils.getHttpClient();
//            result = httputils.doPost(requestUrl, loginParams);
//            if (!result.toString().equals("")) {
//                // 实例化一个存储处理结果数据的JSON OBJECTß
//                JSONObject resultAllata = new JSONObject(result.toString());
//                boolean flag = resultAllata.getBoolean("success");
//                userinfo.setReturn_statue(flag);
//                userinfo.setReturn_message(resultAllata.getString("message"));
//                if (flag) {
//                    if (resultAllata.getString("nickname").equals("")) {
//                        userinfo.setNickName(params[0]);// 默认为登录账户
//                    } else {
//                        userinfo.setNickName(RSAUtils.decryptByPrivateKey(
//                                resultAllata.getString("nickname"), priKey));
//                    }
//                    userinfo.setUserid(resultAllata.getString("userid"));
//                    userinfo.setActivity_no(resultAllata
//                            .getString("activities"));
//                    userinfo.setSmartId(resultAllata.getString("smartId"));
//                }
//                resultFlag = URLS.REQUEST_SUCCESS;
//            } else {// 请求处理失败
//                resultFlag = loginactivity.getString(R.string.socket_error);
//            }
        } catch (Exception e) {
            //resultFlag = loginactivity.getString(R.string.login_error);
            Log.i("==" ,"====>");
        }

    }



    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int requestId) {

        Log.i("==" ,"==response=" +response);
    }

    @Override
    public void onError(String errorMsg, String url, int requestId) {

        Log.i("==" ,"==errorMsg=" +errorMsg);
    }
}
