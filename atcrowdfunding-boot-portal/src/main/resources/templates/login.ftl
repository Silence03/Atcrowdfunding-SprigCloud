
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

      <form class="form-signin" role="form" id="loginform" action="${APP_PATH }/dologin.do" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback" style="color:red">
			${exception.message}
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" name="loginacct" value="${param.loginacct }" id="loginacct" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" name="userpswd" id="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select class="form-control" name="type" id="usertype">
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="${APP_PATH }/member/reg.htm">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH }/layer/layer.js"></script>
    <script>
    function dologin() {
    	var login=$("#loginacct");
    	if($.trim(login.val())==""){
    		//alert("用户名不能为空,请重新输入");
    		layer.msg("用户名不能为空,请重新输入", {time:2000, icon:5, shift:6});
    		login.val("").fous();
    		return false;
    	}
    	var userpswd=$("#userpswd");
    	if($.trim(userpswd.val())==""){
    		//alert("密码不能为空,请重新输入");
    		layer.msg("密码不能为空,请重新输入", {time:2000, icon:5, shift:6});
    		userpswd.val("").fous();
    		return false;
    	}
    	//异步开发登陆
    	$.ajax({
    		type:"post",
    		url:"${APP_PATH }/dologin",
    		data:{
    			loginacct:login.val(),
    			userpswd:userpswd.val(),
    			usertype:$("#usertype").val()
    			},
    		success: function(result){
    			if(result.success){
    				layer.msg("登录成功,即将跳转主页面", {time:3000, icon:6, shift:6});
    				window.location.href="${APP_PATH}/member";
    			}else{
    				layer.msg(result.message, {time:2000, icon:5, shift:6});
    			}
    		}
    		
    	});
    	
    	
    	
    	//$("#loginform").submit();
    	
    	
        /* var type = $(":selected").val();
        if ( type == "user" ) {
            window.location.href = "main.html";
        } else {
            window.location.href = "index.html";
        } */
    }
    </script>
  </body>
</html>