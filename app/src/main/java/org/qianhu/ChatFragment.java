package org.qianhu;

import android.util.Log;

/**
 * Created by fred on 2017/5/26.
 */

public class ChatFragment extends LazyLoadFragment {

    @Override
    public void lazyLoad() {
        Log.i("fragment","lazyLoad");
    }

    @Override
    protected int getLayoutId() {
        Log.i("fragment","getLayoutId");
        return R.layout.fragment_chat;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("fragment","stopLoad");
    }
}
