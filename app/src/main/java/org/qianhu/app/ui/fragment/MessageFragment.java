package org.qianhu.app.ui.fragment;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


import org.qianhu.R;
import org.qianhu.app.ui.activity.ChatActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fred on 2017/5/26.
 */

public class MessageFragment extends LazyLoadFragment {
    @BindView(R.id.btn_to_chat)
    Button btnToChat;

    @Override
    public void lazyLoad() {
        Log.i("fragment","lazyLoad");

    }

    @OnClick(R.id.btn_to_chat)
    public void jump(){
        getActivity().startActivity(new Intent(getActivity(), ChatActivity.class));
    }

    @Override
    protected int getLayoutId() {
        Log.i("fragment","getLayoutId");
        return R.layout.fragment_message;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
        Log.i("fragment","stopLoad");
    }
}
