package com.hust.hydroelectric_backend.Entity;

import java.math.BigDecimal;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:54
 * 电表
 */
public class Ammeter {
    private Integer ammeterId;
    private String ammeterNo;
    private Integer uId;
    private Integer cId;
    private Long installTime;
    private Long readTime;
    private BigDecimal readValue;
    private Long preReadTime;
    private BigDecimal preReadValue;
    private Integer state;
    private String enprNo;
    private Integer isDelete;

    public Integer getAmmeterId() {
        return ammeterId;
    }

    public void setAmmeterId(Integer ammeterId) {
        this.ammeterId = ammeterId;
    }

    public String getAmmeterNo() {
        return ammeterNo;
    }

    public void setAmmeterNo(String ammeterNo) {
        this.ammeterNo = ammeterNo;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Long installTime) {
        this.installTime = installTime;
    }

    public Long getReadTime() {
        return readTime;
    }

    public void setReadTime(Long readTime) {
        this.readTime = readTime;
    }

    public BigDecimal getReadValue() {
        return readValue;
    }

    public void setReadValue(BigDecimal readValue) {
        this.readValue = readValue;
    }

    public Long getPreReadTime() {
        return preReadTime;
    }

    public void setPreReadTime(Long preReadTime) {
        this.preReadTime = preReadTime;
    }

    public BigDecimal getPreReadValue() {
        return preReadValue;
    }

    public void setPreReadValue(BigDecimal preReadValue) {
        this.preReadValue = preReadValue;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getEnprNo() {
        return enprNo;
    }

    public void setEnprNo(String enprNo) {
        this.enprNo = enprNo;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
