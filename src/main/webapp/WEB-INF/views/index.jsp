<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理</title>
    <link rel="stylesheet" href="${ctx}/static/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/static/css/admin.css">
    <script src="${ctx}/static/js/jquery.js"></script>
    <script src="${ctx}/static/js/pintuer.js"></script>
    <script src="${ctx}/static/js/respond.js"></script>
    <script src="${ctx}/static/js/admin.js"></script>
    <script type="text/javascript" >
    $(document).ready(function(){
    	  
    	
    	$("li").children("a").attr("onclick","test(this);");
    	$("li").first().addClass("active");
    	$("li").first().children().find("li").first().addClass("active");
    	show($("li").children("a").attr("id")); 
    	
    });
    
    function test(o){  	
  
    	  if($(o).parent().children("ul").length!=0){
    		$(o).parent().siblings().removeClass("active");
    		$(o).parent().addClass("active");
    		$(o).parent().children().find("li").first().addClass("active");
    		show($(o).attr("id")); 
    		
    	}else{
    		$(o).parent().siblings().removeClass("active");
    		$(o).parent().addClass("active");
    		show($(o).attr("id"));
    	}
    		return;
    };
    function show(url)
    {
     
     $.post(   		 
    		 url,    		 
    	 function(data) {
        $("#show").html(data);
     })
    };
    function submitFrom(url,formId){
    	
    	$('#'+formId).find('input[data-validate],textarea[data-validate],select[data-validate]').trigger("blur");
    	var numError = $('#'+formId).find('.check-error').length;
		if(numError){
			$('#'+formId).find('.check-error').first().find('input[data-validate],textarea[data-validate],select[data-validate]').first().focus().select();
			return false;
		}
		
    	$.ajax({		cache: true,
    					type: "POST",
    					url:url,
    					data:$('#'+formId).serialize(),
    					async: false,    				    
    				    success: function(data) {
    				    	$("#show").html(data);
    				    }
    				});

    };
    </script> 
    <link type="image/x-icon" href="${ctx}/static//favicon.ico" rel="shortcut icon" />
    <link href="${ctx}/static/favicon.ico" rel="bookmark icon" />
</head>

<body>
<div class="lefter">
    <div class="logo"><a href="#" target="_blank"><img src="${ctx}/static/images/logo.png" alt="后台管理系统" /></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
        <div class="admin-navbar">
       
            <span class="float-right">
            
            	<a class="button button-little bg-main" href="#" target="_blank">首页</a>
                <a class="button button-little bg-yellow" href="${ctx}/logout">注销登录</a>
            </span>
            <ul class="nav nav-inline admin-nav">
                <li ><a style="cursor:pointer;"  id="${ctx}/account/personInfo" class="icon-home"> 首页</a>
                    <ul><li ><a style="cursor:pointer;" id="${ctx}/account/personInfo">个人信息</a></li></ul>
                </li>
                
                <li ><a id=""  class="icon-file-text"> 系统</a>
					<ul>
					<li ><a href="#">物业管理</a></li>
					<li><a href="#">社区管理</a></li>
					<li><a href="#">社区信息管理</a></li>
					<li><a href="#">物业报修管理</a></li>
					<li><a href="#">物业报修管理</a></li>
					</ul>
                </li>
				    <li><a style="cursor:pointer;" id="${ctx}/webuser/list/commonAdminList" class="icon-user">用户</a>
					<ul>
					<li><a style="cursor:pointer;" id="${ctx}/webuser/list/commonAdminList">系统管理员管理</a></li>
					<li><a style="cursor:pointer;" id="${ctx}/webuser/propertyadmin">物业管理员管理</a></li>
					<li><a style="cursor:pointer;" id="${ctx}/appuser/user">APP用户管理</a></li>
					
					</ul>
								
					</li>
                <li><a href="#" class="icon-shopping-cart"> 订单</a></li>
                 </ul>
        </div>
        <div class="admin-bread">
              <span>您好，<shiro:principal property="name"/>，欢迎您的光临。</span>
            <ul class="bread">
           
                <li><a id="${ctx}/profile/personInfo" class="icon-home"> 首页</a></li>
                <li>个人信息</li>
            </ul>
        </div>
    </div>
</div>

<div id="show" class="admin">
	
</div>


</body>
</html>