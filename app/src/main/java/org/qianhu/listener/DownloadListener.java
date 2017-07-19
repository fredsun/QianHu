package org.qianhu.listener;

/**
 * Created by fred on 2017/7/18.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
