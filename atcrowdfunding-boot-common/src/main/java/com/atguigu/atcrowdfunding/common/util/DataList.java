package com.atguigu.atcrowdfunding.common.util;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.atcrowdfunding.common.bean.Advert;
import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.MemberCert;
import com.atguigu.atcrowdfunding.common.bean.Role;

public class DataList {
	private List<Integer> ids=new ArrayList<>();
	
	private List<Role> roledatas=new ArrayList<>();
	private List<Cert> certdatas=new ArrayList<>();
	private List<Advert> advertdatas=new ArrayList<>();
	private List<MemberCert> mcs=new ArrayList<>();
	
	
	
	



	public List<Role> getRoledatas() {
		return roledatas;
	}

	public void setRoledatas(List<Role> roledatas) {
		this.roledatas = roledatas;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<Cert> getCertdatas() {
		return certdatas;
	}

	public void setCertdatas(List<Cert> certdatas) {
		this.certdatas = certdatas;
	}

	public List<Advert> getAdvertdatas() {
		return advertdatas;
	}

	public void setAdvertdatas(List<Advert> advertdatas) {
		this.advertdatas = advertdatas;
	}

	public List<MemberCert> getMcs() {
		return mcs;
	}

	public void setMcs(List<MemberCert> mcs) {
		this.mcs = mcs;
	}
}
