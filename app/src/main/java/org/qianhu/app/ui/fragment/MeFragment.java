package org.qianhu.app.ui.fragment;

import android.util.Log;

import org.qianhu.R;
import org.qianhu.app.ui.fragment.LazyLoadFragment;

/**
 * Created by fred on 2017/5/26.
 */

public class MeFragment extends LazyLoadFragment {

    @Override
    public void lazyLoad() {
        Log.i("fragment","lazyLoad");
    }

    @Override
    protected int getLayoutId() {
        Log.i("fragment","getLayoutId");
        return R.layout.fragment_me;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("fragment","stopLoad");
    }
}
