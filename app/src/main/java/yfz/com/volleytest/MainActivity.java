package yfz.com.volleytest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import yfz.com.volleytest.network.RequestManager;
import yfz.com.volleytest.network.VolleyWrapper;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // 获取网络请求主类
        RequestManager requestManager = RequestManager.getInstance(this);

        StringRequest stringRequest = new StringRequest("http://www.baidu.com",

            new Response.Listener<String>(){

                @Override
                public void onResponse(String response) // 服务器端返回的字符串
                {
                    Log.e("TAG", response);

                }
            },

            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Log.e("TAG", error.getMessage(), error);
                }
            });


        //
        requestManager.addToRequestQueue(stringRequest,"tag");


//        VolleyWrapper volleyWrapper=new VolleyWrapper<>(Request.Method.GET,
//                "http://www.baidu.com" ,
//                null,new RequestSuccessListener<T>(),new RequestErrorListener());
        //volleyWrapper.addUrlParameter(urlParams); // 添加请求参数
        //volleyWrapper.sendGETRequest(tag);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
