package com.atguigu.atcrowdfunding.common.util;

import java.util.List;



public class Page<T> {
	/**
	 * 当前页码
	 */
	private Integer pageno;
	/**
	 * 当前每页显示条数
	 */
	private Integer pagesize;
	/**
	 * 总页码
	 */
	private Integer totalno;
	/**
	 * 总条数
	 */
	private Integer totalsize;
	/**
	 * 当前页码的数据
	 */
	private List<T> datas;
	
	
	public Page(Integer pageno,Integer pagesize) {
		this.pageno=(pageno<1)?1:pageno;
		this.pagesize=(pagesize<1)?10:pagesize;
	}
	
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getTotalno() {
		return totalno;
	}
	private void setTotalno(Integer totalno) {
		this.totalno = totalno;
	}
	public Integer getTotalsize() {
		return totalsize;
	}
	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
		this.totalno=(totalsize%pagesize)>0?((totalsize/pagesize)+1):(totalsize/pagesize);
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public Integer getStartindex() {
		return (pageno-1)*pagesize;
	}

	@Override
	public String toString() {
		return "Page [pageno=" + pageno + ", pagesize=" + pagesize + ", totalno=" + totalno + ", totalsize=" + totalsize
				+ ", datas=" + datas + "]";
	}
	
}
