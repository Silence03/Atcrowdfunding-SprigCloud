package com.atguigu.atcrowdfunding.common.bean;

import com.atguigu.atcrowdfunding.common.bean.ajaxresult.Ajaxresult;

public class BaseController {
	ThreadLocal<Ajaxresult> result=new ThreadLocal<>();
	
	protected void start() {
		result.set(new Ajaxresult());
	}
	protected void success(boolean flag) {
		result.get().setSuccess(flag);
	}
	protected void message(String message) {
		result.get().setMessage(message);
	}
	protected void data( Object data ) {
		result.get().setData(data);
	}
	protected Object end() {
		Ajaxresult results = result.get();
		result.remove();
		return results;
	}


}
