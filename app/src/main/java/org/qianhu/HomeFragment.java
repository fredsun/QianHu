package org.qianhu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;

/**
 * Created by fred on 2017/5/26.
 */

public class HomeFragment extends LazyLoadFragment {

    @Override
    public void lazyLoad() {
        Log.i("fragment","lazyLoad");
    }

    @Override
    protected int getLayoutId() {
        Log.i("fragment","getLayoutId");
        return R.layout.fragment_home;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("fragment","stopLoad");
    }
}
