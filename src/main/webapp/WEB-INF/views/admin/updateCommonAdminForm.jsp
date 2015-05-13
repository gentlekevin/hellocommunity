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
	<form  id="updateCommonAdmin"  method="post" class="form-x" >
				
                <input type="hidden" name="id" value="${user.id}"/>
               
                 <div class="form-group">
                    <div class="label"><label for="title">登录账号：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="loginName" name="loginName" value="${user.loginName}" 
                    	size="30" readonly="readonly" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="title">姓名：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="name" name="name" value="${user.name}" 
                    	size="30" placeholder="输入姓名"  />
                    </div>
                </div>
                <div class="form-group">
                	<div class="label"><label>性别：</label></div>
                	<div class="field">
                	      
                        <div class="button-group button-group-small radio">
                            <label  <c:choose>  
                            <c:when test="${user.sex=='1'}">  class='button active'" </c:when> 
                            <c:otherwise> class='button ' </c:otherwise>  
                            </c:choose> >  
                             <input name="sex"  value="1"  <c:if test="${user.sex=='1'}"> checked="checked" </c:if> type="radio"> 
                                                                                男</label>
                            <label  <c:choose>  
                            <c:when test="${user.sex=='0'}">  class='button active'" </c:when> 
                            <c:otherwise> class='button' </c:otherwise>  
                            </c:choose> >
                            <input name="sex" value="0" <c:if test="${user.sex=='0'}"> checked="checked" </c:if> type="radio"> 
                                                                                  女</label>
                        </div>
                    </div>
                </div>
                 <div class="form-group">
                    <div class="label"><label for="keywords">手机：</label></div>
                    <div class="field">
                    	<input type="text"  class="input" id="phone" name="phone" value="${user.phone }" 
                    	size="30" placeholder="输入手机号"  
                    	data-validate="mobile:请输入正确的手机号" />
                    </div>
                </div>
               
                <div class="form-button">
                <button class="button bg-main"  id="${ctx}/webuser/operation/updateAdmin" onclick="submitFrom(this.id,'updateCommonAdmin')">提交</button>
                
                  <button class="button bg-main" id="${ctx}/webuser/list/commonAdminList" onclick="show(this.id)">返回</button>
                 </div>
                
            </form>
</body>
</html>