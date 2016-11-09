package cn.codingforfun.coolweather.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.codingforfun.coolweather.R;
import cn.codingforfun.coolweather.service.AutoUpdateService;
import cn.codingforfun.coolweather.util.HttpCallbackListener;
import cn.codingforfun.coolweather.util.HttpUtil;
import cn.codingforfun.coolweather.util.Utility;

/**
 * 此页面进行天气情况的展示
 * Created by bp404 on 16/11/1.
 * 本项目Android部分已经开源至Github: https://github.com/bp404/Weather_bp404
 */
public class WeatherActivity extends Activity implements View.OnClickListener {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            new AlertDialog.Builder(WeatherActivity.this)
                    .setTitle("主人")
                    .setMessage("已更新至最新数据")
                    .setPositiveButton("朕知道了", null)
                    .show();
            bar.setVisibility(bar.INVISIBLE);
            //text.setText(msg.obj.toString());
            String json = msg.obj.toString();
            try {
                JSONObject jsonob = new JSONObject(json);
                String city = jsonob.get("city").toString();
                String temperature = jsonob.get("temperature").toString();
                String info = jsonob.get("info").toString();
                String date = jsonob.get("date").toString();
                String time = jsonob.get("time").toString();
                String chuanyi_0 = jsonob.get("chuanyi_0").toString();
                String chuanyi_1 = jsonob.get("chuanyi_1").toString();
                String ziwaixian_0 = jsonob.get("ziwaixian_0").toString();
                String ziwaixian_1 = jsonob.get("ziwaixian_1").toString();
                cityText.setText(city);
                temperatureText.setText(temperature);
                infoText.setText("天气  "+info);
                dateText.setText(date);
                timeText.setText("最后更新时间:"+time);
                clothingName.setText("穿衣推荐-"+chuanyi_0);
                clothingText.setText("        "+chuanyi_1);
                radiationName.setText("紫外线强度:"+ziwaixian_0);
                radiationText.setText("        "+ziwaixian_1);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(WeatherActivity.this, "服务器崩溃了，请联系管理员", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private String webString;
    private Button switchCity;
    private ProgressBar bar;
    private TextView cityText;
    private TextView infoText;
    private TextView temperatureText;
    private TextView dateText;
    private TextView timeText;
    private TextView clothingName;
    private TextView clothingText;
    private TextView radiationName;
    private TextView radiationText;
    private Button refreshweather;
    public static final String TAG = "WeatherActivity_D";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);
        bar = (ProgressBar)findViewById(R.id.progressBar);
        cityText = (TextView)findViewById(R.id.city);
        infoText = (TextView)findViewById(R.id.info);
        temperatureText = (TextView)findViewById(R.id.temperature);
        dateText = (TextView)findViewById(R.id.date);
        switchCity = (Button)findViewById(R.id.switch_city);
        timeText = (TextView)findViewById(R.id.time);
        clothingName = (TextView)findViewById(R.id.clothingName);
        clothingText = (TextView)findViewById(R.id.clothingText);
        radiationName = (TextView)findViewById(R.id.radiationName);
        radiationText = (TextView)findViewById(R.id.radiationText);
        refreshweather = (Button)findViewById(R.id.refresh_weather);
        final String countyCode = getIntent().getStringExtra("county_code");
        if (!TextUtils.isEmpty(countyCode)){
            queryWeatherCode(countyCode);
        } else{
            showWeather();
        }


        new Thread() {
            @Override
            public void run() {
                try {
                    String urlStr = "http://www.bp404.com/api/weather.php?city="+countyCode+"";
                    URL url = new URL(urlStr);
                    try {
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setConnectTimeout(80000);
                        webString = "hi";
                        InputStream in = null;
                        if (conn.getResponseCode() == 200) {
                            in = conn.getInputStream();
                        }
                        InputStreamReader inReader = new InputStreamReader(in, "UTF-8");

                        BufferedReader bufferedReader = new BufferedReader(inReader);
                        StringBuffer sb = new StringBuffer();
                        String temp = null;

                        while ((temp = bufferedReader.readLine()) != null) {
                            sb.append(temp);
                        }
                        webString = sb.toString();

                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Message message = new Message();
                message.obj = webString;
                handler.sendMessage(message);
                //handler.sendEmptyMessage(1);//发送消失到handler，通知主线程下载完成
            }
        }.start();
        switchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, ChooseAreaActivity.class);
                intent.putExtra("from_weather_activity", true);
                startActivity(intent);
                finish();
            }
        });
        refreshweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(bar.VISIBLE);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String urlStr = "http://www.bp404.com/api/weather.php?city="+countyCode+"";
                            URL url = new URL(urlStr);
                            try {
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setConnectTimeout(80000);
                                webString = "hi";
                                InputStream in = null;
                                if (conn.getResponseCode() == 200) {
                                    in = conn.getInputStream();
                                }
                                InputStreamReader inReader = new InputStreamReader(in, "UTF-8");

                                BufferedReader bufferedReader = new BufferedReader(inReader);
                                StringBuffer sb = new StringBuffer();
                                String temp = null;

                                while ((temp = bufferedReader.readLine()) != null) {
                                    sb.append(temp);
                                }
                                webString = sb.toString();

                            } catch (IOException io) {
                                io.printStackTrace();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        Message message = new Message();
                        message.obj = webString;
                        handler.sendMessage(message);
                        //handler.sendEmptyMessage(1);//发送消失到handler，通知主线程下载完成
                    }
                }.start();
            }
        });
    }


    private void queryWeatherCode(String countyCode){
        String address = "http://www.weather.com.cn/data/list3/city" + countyCode + ".xml";

    }
    private void queryWeatherInfo(String weatherCode){
        String address = "http://www.weather.com.cn/data/cityinfo/" + weatherCode + ".html";
        Log.d(TAG, "query weather info address:" + address);

    }

    private void showWeather(){
        Log.d(TAG, "showWeather executed");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d(TAG, "city_name" + prefs.getString("city_name", ""));
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
