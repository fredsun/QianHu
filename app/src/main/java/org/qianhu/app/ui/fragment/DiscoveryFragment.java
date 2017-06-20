package org.qianhu.app.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.qianhu.R;
import org.qianhu.app.ui.fragment.LazyLoadFragment;

/**
 * Created by fred on 2017/5/26.
 */

public class DiscoveryFragment extends LazyLoadFragment {

    @Override
    public void lazyLoad() {
        Log.i("discoveryFragment","LazyLoad");
    }

    @Override
    protected int getLayoutId() {
        Log.i("discoveryFragment","GetLayoutId");
        return R.layout.fragment_discovery;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("discoveryFragment","StopLoad");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("discoveryFragment","DestroyView");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("discoveryFragment","setUserVisibleHint: "+isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("discoveryFragment","onResume");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("discoveryFragment","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("discoveryFragment","onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("discoveryFragment","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("discoveryFragment","onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("discoveryFragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("discoveryFragment","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("discoveryFragment","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("discoveryFragment","onDetach");
    }
}
