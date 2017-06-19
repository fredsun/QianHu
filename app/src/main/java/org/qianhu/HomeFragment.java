package org.qianhu;

import android.content.Context;
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
        Log.i("homeFragment","LazyLoad");
    }

    @Override
    protected int getLayoutId() {
        Log.i("homeFragment","GetLayoutId");
        return R.layout.fragment_home;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("homeFragment","HomeStopLoad");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("homeFragment","DestroyView");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("homeFragment","setUserVisibleHint: "+isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("homeFragment","onResume");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("homeFragment","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("homeFragment","onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("homeFragment","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("homeFragment","onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("homeFragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("homeFragment","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("homeFragment","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("homeFragment","onDetach");
    }
}
