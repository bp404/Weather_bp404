package cn.codingforfun.coolweather.model;

/**
 * 此页面部分代码来源于Github开源项目 https://github.com/qian2729/coolweather.git
 * Modified by bp404 on 16/11/3.
 * 本项目Android部分已经开源至Github: https://github.com/bp404/Weather_bp404
 */
public class Province {
    private int id;
    private String provinceName;
    private String provinceCode;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
