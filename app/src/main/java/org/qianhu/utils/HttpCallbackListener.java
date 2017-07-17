package org.qianhu.utils;

/**
 * Created by fred on 2017/7/17.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
