package com.hust.hydroelectric_backend.Entity.VO;


/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 9:34
 */
public class RunningDevice {
    private Integer waterMeterCnt;
    private Integer waterMeterSucCnt;
    private Integer ammeterCnt;
    private Integer ammeterSucCnt;

    public Integer getWaterMeterCnt() {
        return waterMeterCnt;
    }

    public void setWaterMeterCnt(Integer waterMeterCnt) {
        this.waterMeterCnt = waterMeterCnt;
    }

    public Integer getWaterMeterSucCnt() {
        return waterMeterSucCnt;
    }

    public void setWaterMeterSucCnt(Integer waterMeterSucCnt) {
        this.waterMeterSucCnt = waterMeterSucCnt;
    }

    public Integer getAmmeterCnt() {
        return ammeterCnt;
    }

    public void setAmmeterCnt(Integer ammeterCnt) {
        this.ammeterCnt = ammeterCnt;
    }

    public Integer getAmmeterSucCnt() {
        return ammeterSucCnt;
    }

    public void setAmmeterSucCnt(Integer ammeterSucCnt) {
        this.ammeterSucCnt = ammeterSucCnt;
    }
}
