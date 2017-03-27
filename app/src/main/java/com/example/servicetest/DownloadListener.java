package com.example.servicetest;

/**
 * Created by ZouYuan on 2017/3/27.
 */

public interface DownloadListener {
    void onProgress(int progress);//notify the current download progress
    void onSuccess();//notify if the download is success
    void onFailed();//notify if the download is failed
    void onPaused();//notify if the download is paused
    void onCanceled();//notify if the download is canceled
}
