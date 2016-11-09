package cn.codingforfun.coolweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.codingforfun.coolweather.service.AutoUpdateService;

/**
 * 此页面部分代码来源于Github开源项目 https://github.com/qian2729/coolweather.git
 * Modified by bp404 on 16/11/3.
 * 本项目Android部分已经开源至Github: https://github.com/bp404/Weather_bp404
 */
public class AutoUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }
}
