package org.qianhu.app.ui.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import org.qianhu.MainActivity;
import org.qianhu.R;
import org.qianhu.app.ui.activity.ChatActivity;
import org.qianhu.bean.Fruit;
import org.qianhu.adapter.FruitAdapter;
import org.qianhu.database.Book;
import org.qianhu.database.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.jar.Manifest;

/**
 * Created by fred on 2017/5/26.
 */

public class HomeFragment extends LazyLoadFragment {
    @Override
    public void lazyLoad() {


        View viewById = findViewById(R.id.btn_call);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity()
                            , new String[]{android.Manifest.permission.CALL_PHONE}
                            , 1);
                }
                else {
                    call();
                }
            }
        });


        View viewById1 = findViewById(R.id.btn_notification);
        viewById1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getContext(),0,intent,0);
                NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(getContext())
                        .setContentTitle("this is a title")
                        .setContentText("this is a text")

                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setLights(Color.GREEN,1000,1000)
                        .setVibrate(new long[]{0,1000,1000,1000})
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                manager.notify(1,notification);
            }
        });
    }

    private void call() {
        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    Toast.makeText(getContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void stopLoad() {
        super.stopLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



}
