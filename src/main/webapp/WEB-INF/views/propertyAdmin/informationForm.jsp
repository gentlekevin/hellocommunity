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
    <form  id="InformationForm"  method="post" class="form-x" enctype="multipart/form-data">
    
     <div class="panel admin-panel">
     
    	<div class="panel-head"><strong>社区列表</strong></div>
    	 <div class="padding border-bottom">
    	 <input type="button" class="button button-small checkall" name="checkall" checkfor="communityId" value="全选" />
    	 </div>
    	 
	        <input type="hidden" name="id" value="${information.id}"/>
	        <input type="hidden" name="oldCommunityIds" value="${oldCommunityIds}"/> 
               <table id="table" class="table table-hover">
        	<tr><th width="20">选择</th><th width="20">序号</th><th width="200">社区名称</th>
        	</tr>
               <c:forEach items="${communities}" var="community" varStatus="xh">
	    	<tr>
            <td><input type="checkbox"  name="communityId" 
                  <c:forEach items="${checkCommunities}" var="checkCommunity">
                
                  <c:if test="${checkCommunity.community.id==community.id}">checked="checked" </c:if>
                  </c:forEach> 
                  value="${community.id}" />  
              </td>
            
            <td>${xh.count}</td>
            <td>${community.name}</td>
            </tr>
             	</c:forEach>
                    
          </table> 
          </div> 
                    <div class="form-group">
                    <div class="label"><label for="title">资讯标题：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="title" name="title" size="30" value="${information.title}"
                    	 placeholder="请输入资讯标题" data-validate="required:请输入资讯标题。" />
                    </div>
                </div>
                 <div class="form-group">
                    <div class="label"><label for="title">资讯内容：</label></div>
                    <div class="field">
                    	<textarea class="input" rows="5" cols="50" id="content" name="content" size="30" value="${information.content}"
                    	placeholder="请输入资讯内容" ></textarea>
                    </div>
                </div>
                  <div class="form-group">
                    <div class="label"><label for="title">资讯图片：</label></div>
                    <div class="field">
                    	<a class="button input-file" href="javascript:void(0);">+ 浏览文件<input size="100" type="file" name="logo" data-validate="regexp#.+.(jpg|jpeg|png|gif)$:只能上传jpg|gif|png格式文件" /></a>
                    </div>
                </div>
                 <div class="form-button">
                <button class="button bg-main"  
                   <c:choose>  
                   <c:when test="${formType=='add'}">   id="${ctx}/propertyAdmin/operation/addInformation" </c:when> 
                   <c:otherwise> id="${ctx}/propertyAdmin/operation/updateInformation" </c:otherwise>  
                  </c:choose>   
                onclick="submitFrom(this.id,'InformationForm')">提交</button>
                <button class="button bg-main" id="${ctx}/propertyAdmin/list/informationList" onclick="show(this.id)">返回</button>
                
                </div>
                
            </form>
</body>
</html>