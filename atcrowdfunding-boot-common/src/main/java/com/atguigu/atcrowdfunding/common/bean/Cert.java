package com.atguigu.atcrowdfunding.common.bean;

import java.io.Serializable;

public class Cert implements Serializable{
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	@Override
	public String toString() {
		return "Cert [id=" + id + ", name=" + name + "]";
	}
    
}