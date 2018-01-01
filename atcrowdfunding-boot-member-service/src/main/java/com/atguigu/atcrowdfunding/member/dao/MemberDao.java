package com.atguigu.atcrowdfunding.member.dao;

import org.apache.ibatis.annotations.Select;

import com.atguigu.atcrowdfunding.common.bean.Member;


public interface MemberDao {
	@Select("select * from t_member where loginacct=#{loginacct}")
	Member getMemberByLoginacct(String loginacct);

}
