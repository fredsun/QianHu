package org.qianhu.app.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import org.qianhu.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by fred on 2017/5/26.
 */

public class NotificationFragment extends LazyLoadFragment {
    TextView tv_get_result;
    @Override
    public void lazyLoad() {
        Log.i("notificationFragment","lazyLoad");
        tv_get_result = (TextView) findViewById(R.id.tv_get_result);
        Button btn_get = (Button) findViewById(R.id.btn_get);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpURLConnection();
            }
        });
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally{
                    if (reader != null){
                        try{
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String s) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_get_result.setText(s);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        Log.i("notificationFragment","getLayoutId");
        return R.layout.fragment_notification;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("notificationFragment","stopLoad");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("notificationFragment","DestroyView");
    }




}
