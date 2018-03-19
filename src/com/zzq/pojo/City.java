package com.zzq.pojo;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = -3158487381315586433L;
	private Integer cId;
	private String cname;
	private Integer pId;
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public City(Integer cId, String cname, Integer pId) {
		super();
		this.cId = cId;
		this.cname = cname;
		this.pId = pId;
	}
	public City() {
		super();
	}
	@Override
	public String toString() {
		return "City [cId=" + cId + ", cname=" + cname + ", pId=" + pId + "]";
	}
	
}
