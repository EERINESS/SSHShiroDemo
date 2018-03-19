package com.zzq.pojo;

import java.io.Serializable;

public class Province implements Serializable {
	private static final long serialVersionUID = 516982346597602584L;
	private Integer pId;
	private String pname;
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Province(Integer pId, String pname) {
		super();
		this.pId = pId;
		this.pname = pname;
	}
	public Province() {
		super();
	}
	@Override
	public String toString() {
		return "Province [pId=" + pId + ", pname=" + pname + "]";
	}
	

}
