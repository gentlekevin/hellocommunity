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


	<form  id="updatePassword"  method="post" class="form-x" >
				
                <input type="hidden" name="id" value="${user.id}"/>
                <div class="form-group">
                    <div class="label"><label for="title">旧密码：</label></div>
                    <div class="field">
                    	<input type="password" class="input" id="password" name="password" size="30" placeholder="现用密码" data-validate="required:请填写。" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="keywords">新密码：</label></div>
                    <div class="field">
                    	<input type="password" class="input" id="plainPassword" name="plainPassword" size="30" placeholder="输入要修改的密码" data-validate="required:请填写，建议5-10个数字包含字母。" />
                    </div>
                </div>
                 <div class="form-group">
                    <div class="label"><label for="keywords">确认密码：</label></div>
                    <div class="field">
                    	<input type="password"  class="input" id="confirmPassword" name="confirmPassword" size="30" placeholder="再次输入要修改的密码"    data-validate="required:请填写，建议5-10个数字包含字母,repeat#plainPassword:两次输入不同。" />
                    </div>
                </div>
               
                <div class="form-button">
                <button class="button bg-main"  id="${ctx}/account/updateUserPassowrd" onclick="submitFrom(this.id,'updatePassword')">提交</button>
                <button class="button bg-main"  id="${ctx}/account/personInfo" onclick="show(this.id)">返回</button>
                
                </div>
                
            </form>
</body>
</html>