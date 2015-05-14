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
<div class="alert alert-yellow"><span class="close">

      </span><strong>注意：</strong>${msg}</div>

	</c:if>


	<form  id="CommunityForm"  method="post" class="form-x" >
				
                     <input type="hidden" name="id" value="${community.id}"/> 
                 <div class="form-group">
                    <div class="label"><label for="title">物业：</label></div>
                    <div class="field">
                    <select  class="select"  name="propertyId" >
                    	<option value="" selected>请选择物业</option>
                    	<c:forEach items="${properties}" var="p">
                    	<option value="${p.id}" <c:if test="${community.property.id==p.id}">selected="selected"  </c:if> >
                    	${p.name}</option>
                    	</c:forEach>
                    	</select>
                    	
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="title">社区名称：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="name" name="name" size="30" value="${community.name}"
                    	 placeholder="请输入社区名称" data-validate="required:请输入社区名称。" />
                    </div>
                </div>
                 <div class="form-group">
                    <div class="label"><label for="title">社区地址：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="address" name="address" size="30" value="${community.address}"
                    	placeholder="请输入社区地址" />
                    </div>
                </div>
                  <div class="form-group">
                    <div class="label"><label for="title">联系人：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="contacts" name="contacts" size="30" value="${community.contacts}"
                    	 placeholder="请输入社区联系人" />
                    </div>
                </div>
                   <div class="form-group">
                    <div class="label"><label for="title">联系方式：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="phone" name="phone" size="30" value="${community.phone}"
                    	placeholder="请输入社区联系方式" />
                    </div>
                </div>
               
               
                <div class="form-button">
                <button class="button bg-main"  
                   <c:choose>  
                   <c:when test="${formType=='add'}">   id="${ctx}/commonAdmin/operation/addCommunity" </c:when> 
                   <c:otherwise> id="${ctx}/commonAdmin/operation/updateCommunity" </c:otherwise>  
                  </c:choose>   
                onclick="submitFrom(this.id,'CommunityForm')">提交</button>
                <button class="button bg-main" id="${ctx}/commonAdmin/list/communityList" onclick="show(this.id)">返回</button>
                
                </div>
                
            </form>
</body>
</html>