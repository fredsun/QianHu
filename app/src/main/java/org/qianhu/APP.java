package org.qianhu;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.litepal.LitePalApplication;
import org.qianhu.broadcast.NetworkConnectChangedReceiver;

/**
 * Created by fred on 2017/7/3.
 */

public class APP extends Application {
    private static APP app;
    public NetworkConnectChangedReceiver mNetworkChangeListener;

    //表示是否连接
    public boolean isConnected;
    //    表示是否是移动网络
    public boolean isMobile;
    //    表示是否是WiFi
    public boolean isWifi;
    //    表示WiFi开关是否打开
    public boolean isEnablaWifi;
    //    表示移动网络数据是否打开
    public boolean isEnableMobile;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initReceiver();
        LitePalApplication.initialize(app);
    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        mNetworkChangeListener = new NetworkConnectChangedReceiver();
        registerReceiver(mNetworkChangeListener,filter);
    }

    public static APP getInstance(){
        return app;
    }

    public void setEnablaWifi(boolean enablaWifi) {
        isEnablaWifi = enablaWifi;
    }
    public void setWifi(boolean wifi) {
        isWifi = wifi;
    }
    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }
    public void setConnected(boolean connected) {
        isConnected = connected;
    }


}
