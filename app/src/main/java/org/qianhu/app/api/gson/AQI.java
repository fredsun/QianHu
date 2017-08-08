package org.qianhu.app.api.gson;

/**
 * Created by fred on 2017/8/7.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
