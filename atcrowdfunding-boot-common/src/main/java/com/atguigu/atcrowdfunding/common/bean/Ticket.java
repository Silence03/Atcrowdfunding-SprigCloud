package com.atguigu.atcrowdfunding.common.bean;

import java.io.Serializable;

public class Ticket implements Serializable{
	private Integer id;
	private Integer memberid;
	private String piid;
	private String status;
	private String authcode;
	private String pstep;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getPstep() {
		return pstep;
	}
	public void setPstep(String pstep) {
		this.pstep = pstep;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", memberid=" + memberid + ", piid=" + piid + ", status=" + status + ", authcode="
				+ authcode + ", pstep=" + pstep + "]";
	}
	
	
}
