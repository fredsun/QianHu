package org.qianhu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by fred on 2017/5/26.
 */

public class NotificationFragment extends LazyLoadFragment {

    @Override
    public void lazyLoad() {
        Log.i("notificationFragment","lazyLoad");
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("notificationFragment","setUserVisibleHint: "+isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("notificationFragment","onResume");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("notificationFragment","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("notificationFragment","onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("notificationFragment","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("notificationFragment","onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("notificationFragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("notificationFragment","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("notificationFragment","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("notificationFragment","onDetach");
    }
}
