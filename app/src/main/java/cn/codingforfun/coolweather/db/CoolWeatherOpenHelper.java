package cn.codingforfun.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 此页面部分代码来源于Github开源项目 https://github.com/qian2729/coolweather.git
 * Modified by bp404 on 16/11/3.
 * 本项目Android部分已经开源至Github: https://github.com/bp404/Weather_bp404
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
    /**Create table sql sentence*/
    public static final String CREATE_PROVINCE = "CREATE TABLE Province (" +
            "id integer primary key autoincrement," +
            "province_name text," +
            "province_code text" +
            ");";
    public static final String CREATE_CITY = "CREATE TABLE City (" +
            "id integer primary key autoincrement," +
            "city_name text," +
            "city_code text," +
            "province_id integer" +
            ");";
    public static final String CREATE_COUNTRY = "CREATE TABLE Country(" +
            "id integer primary key autoincrement," +
            "country_name text," +
            "country_code text," +
            "city_id integer" +
            ");";
    public CoolWeatherOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
