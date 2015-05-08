<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<body>
	<div class="line-big">
    	<div class="xm3">
        	<div class="panel border-back">
            	<div class="panel-body text-center">
                	<img src="${ctx}/static/images/face.jpg" width="120" class="radius-circle" /><br />
                   <shiro:principal property="name"/>
                </div>
                <div class="panel-foot bg-back border-back">您好，<shiro:principal property="name"/>，上次登录：2012-06-04 01:00</div>
            </div>
   
        </div>
        <div class="xm9">
        
            <div class="panel">
            	<div class="panel-head"><strong>个人信息</strong></div>
                <table class="table">
					<tr>
						<th colspan="2">账号信息</th>
						<th colspan="2">基本信息</th>
					</tr>
					<tr>
					<td align="right">角色：</td>
						<td>${user.roles}</td>
						
						<td width="90" align="right">姓名：</td>
						<td>${user.name}</td>
					</tr>
					<tr>
						<td width="110" align="right">登陆账号：</td>
						<td>${user.loginName}</td>
						<td align="right">性别：</td>
						<td>${user.sex}</td>
					</tr>
					<tr>
						<td align="right">注册时间：</td>
						<td>${user.registerDate}</td>
						<td align="right">手机：</td>
						<td>${user.phone}</td>
					</tr>
					<tr>
						<td align="right">操作：</td>
						<td><a class="button border-blue button-little" href="${ctx}/profile">修改密码</a></td>
						<td align="right">操作：</td>
						<td><a class="button border-blue button-little" href="${ctx}/profile">修改基本信息</a></td>
					</tr>
				</table>
            </div>
        </div>
    </div>

</body>
</html>