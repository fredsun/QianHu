package org.qianhu;

import android.util.Log;

/**
 * Created by fred on 2017/5/26.
 */

public class NotificationFragment extends LazyLoadFragment {

    @Override
    public void lazyLoad() {
        Log.i("fragment","lazyLoad");
    }

    @Override
    protected int getLayoutId() {
        Log.i("fragment","getLayoutId");
        return R.layout.fragment_notification;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("fragment","stopLoad");
    }
}
