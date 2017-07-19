package org.qianhu.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.qianhu.MainActivity;
import org.qianhu.R;
import org.qianhu.Thread.DownloadTask;
import org.qianhu.listener.DownloadListener;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downloadUrl;
    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download success", -1));
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "Download failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class DownloadBinder extends Binder{
       public void startDownload(String url){
           if (downloadTask == null){
               downloadUrl =  url;
               downloadTask = new DownloadTask(downloadListener);
               downloadTask.execute(downloadUrl);
               startForeground(1, getNotification("Downloading...", 0 ));
               Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
           }
       }

       public void pauseDownload(){
           if (downloadTask != null){
               downloadTask.pauseDownload();
           }
       }

       public void cancelDownload(){
           if (downloadTask != null){
               downloadTask.cancelDownload();
           }else {
               if (downloadUrl != null){
                   //取消下载时，删除文件，关闭通知
                   String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                   String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                   File file = new File(directory + fileName);
                   if (file.exists()){
                       file.delete();
                   }
                   getNotificationManager().cancel(1);
                   stopForeground(true);
                   Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
               }
           }
       }
    }


    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress >= 0){
            //当progress >= 0 时才现实进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }


}
