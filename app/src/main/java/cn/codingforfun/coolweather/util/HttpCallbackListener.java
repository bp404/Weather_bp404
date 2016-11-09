package cn.codingforfun.coolweather.util;

/**
 * 此页面部分代码来源于Github开源项目 https://github.com/qian2729/coolweather.git
 * Modified by bp404 on 16/11/3.
 * 本项目Android部分已经开源至Github: https://github.com/bp404/Weather_bp404
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
