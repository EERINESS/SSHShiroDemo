package com.zzq.pojo;

import java.io.Serializable;

public class County implements Serializable {
	private static final long serialVersionUID = -6600977847442102146L;
	private Integer countyId;
	private String countyName;
	private Integer cId;
	public Integer getCountyId() {
		return countyId;
	}
	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public County() {
		super();
	}
	public County(Integer countyId, String countyName, Integer cId) {
		super();
		this.countyId = countyId;
		this.countyName = countyName;
		this.cId = cId;
	}
	@Override
	public String toString() {
		return "County [countyId=" + countyId + ", countyName=" + countyName + ", cId=" + cId + "]";
	}
	

}
