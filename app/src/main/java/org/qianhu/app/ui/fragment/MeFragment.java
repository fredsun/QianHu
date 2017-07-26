package org.qianhu.app.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.qianhu.MainActivity;
import org.qianhu.R;
import org.qianhu.service.DownloadService;

/**
 * Created by fred on 2017/5/26.
 */

public class MeFragment extends LazyLoadFragment {
    private Activity mActivity;
    private AppCompatActivity mAppCompatActivity;
    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    public static final int UPDATE_TEXT = 1;
    private TextView textView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.backup:
            Toast.makeText(getActivity(), "you click the backup", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                Toast.makeText(getActivity(), "you click the delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.settings:
                Toast.makeText(getActivity(), "you click the settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void lazyLoad() {
        Log.i("fragment","lazyLoad");
        mActivity = getActivity();
        mAppCompatActivity = (AppCompatActivity)mActivity;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppCompatActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        textView = (TextView) findViewById(R.id.tv_me_title);
        Button btn_change_text = (Button) findViewById(R.id.btn_change_text);
        btn_change_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        Button btn_start_download = (Button) findViewById(R.id.btn_start_download);
        btn_start_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadBinder == null){
                    return;
                }
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                downloadBinder.startDownload(url);

            }
        });

        Button btn_pause_download = (Button) findViewById(R.id.btn_pause_download);
        btn_pause_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadBinder == null){
                    return;
                }
                downloadBinder.pauseDownload();
            }
        });

        Button btn_cancel_download = (Button) findViewById(R.id.btn_cancel_download);
        btn_cancel_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadBinder == null){
                    return;
                }
                downloadBinder.cancelDownload();
            }
        });

        Intent intent = new Intent(getActivity(), DownloadService.class);
        getContext().startService(intent);
        getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length >0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getContext(), "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unbindService(connection);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
    }
}
