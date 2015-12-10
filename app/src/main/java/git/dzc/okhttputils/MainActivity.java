package git.dzc.okhttputils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;

import java.io.IOException;

import git.dzc.okhttputilslib.CacheType;
import git.dzc.okhttputilslib.JsonCallback;
import git.dzc.okhttputilslib.OKHttpUtils;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.this.getClass().getSimpleName();

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;

    private OKHttpUtils okHttpUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okHttpUtils = new OKHttpUtils.Builder(this).build();

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(CacheType.ONLY_NETWORK);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(CacheType.ONLY_CACHED);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(CacheType.NETWORK_ELSE_CACHED);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(CacheType.CACHED_ELSE_NETWORK);
            }
        });
    }


    private void getData(final CacheType cacheType){
        okHttpUtils.get("http://api.k780.com:88/?app=life.time&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json", cacheType,null, new JsonCallback<DateModule>() {


            @Override
            public void onStart() {
                Log.d(TAG,"onStart");
            }

            @Override
            public void onFinish() {
                Log.d(TAG,"onFinish");
            }

            @Override
            public void onFailure(Request request, Exception e) {
                Log.d(TAG,"onFailure");
            }

            @Override
            public void onResponse(final DateModule object) throws IOException {
                Log.d(TAG,"onResponse");
                tv5.post(new Runnable() {
                    @Override
                    public void run() {
                        //tv5.setText(cacheType.name()+"  "+str);
                        tv5.setText(object.getResult().getDatetime_2());
                    }
                });
            }
        });
    }



}
