<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html lang="zh-cn">
<head>
 <script src="${ctx}/static/js/jquery.js"></script>
    <script src="${ctx}/static/js/pintuer.js"></script>
    </head>
<body>

<c:if test="${not empty msg}">
<div class="alert alert-yellow"><span class="close"></span><strong>注意：</strong>${msg}</div>

	</c:if>


	<form  id="adminForm"  method="post" class="form-x" >
				
                <input type="hidden" name="id" value="${user.id}"/>
                <div class="form-group">
                    <div class="label"><label for="title">登录账号：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="name" name="name" value="${user.name}" 
                    	size="30" placeholder="输入姓名" data-validate="required:请填写。" />
                    </div>
                </div>
           
                 <div class="form-group">
                    <div class="label"><label for="keywords">密码：</label></div>
                    <div class="field">
                    	<input type="text"  class="input" id="phone" name="phone" value="${user.phone }" 
                    	size="30" placeholder="输入手机号"  
                    	data-validate="required:请输入手机号,mobile:请输入正确的手机号" />
                    </div>
                </div>
               
                <div class="form-button">
                <button class="button bg-main"  id="${ctx}/account/updatePersonInfo"
                 onclick="submitFrom(this.id,'updatePersonInfo')">提交</button>
                
                 </div>
                
            </form>
</body>
</html>