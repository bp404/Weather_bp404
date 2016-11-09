package cn.codingforfun.coolweather.model;

/**
 * 此页面部分代码来源于Github开源项目 https://github.com/qian2729/coolweather.git
 * Modified by bp404 on 16/11/3.
 * 本项目Android部分已经开源至Github: https://github.com/bp404/Weather_bp404
 */
public class Country {
    private int id;
    private String countryName;
    private String countryCode;
    private int cityId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
