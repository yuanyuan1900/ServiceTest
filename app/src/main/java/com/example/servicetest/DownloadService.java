package com.example.servicetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownloadService extends Service {
    private DownloadTask downloadTask;
    private String dwonloadUrl;
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading...",progress));
        }

        @Override
        public void onSuccess() {
            dwonloadUrl = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
        }

        @Override
        public void onFailed() {
            dwonloadUrl = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));

        }

        @Override
        public void onPaused() {
            dwonloadUrl = null;
//            stopForeground(true);
//            getNotificationManager().notify(1,getNotification("Download Failed",-1));

        }

        @Override
        public void onCanceled() {

            dwonloadUrl = null;
            stopForeground(true);
//            getNotificationManager().notify(1,getNotification("Download Failed",-1));
        }

    };
    public DownloadService() {
    }
    private DownloadBinder mBinder = new DownloadBinder();
    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        return mBinder;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private Notification getNotification(String s, int progress) {
        return (Notification)null;
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    private class DownloadBinder extends Binder {
        public void startDownload(String url){
            if(downloadTask == null){
                dwonloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(dwonloadUrl);
                startForeground(1,getNotification("Downloading...",0));
            }

        }
        public void pauseDownload(){

        }
        public void cancelDownload(){

        }
    }
}
