<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<form  id="ComplaintForm"  method="post" class="form-x" >
	             <input type="hidden" name="id" value="${complaint.id}"/> 
	                       
                <div class="form-group">
                    <div class="label"><label for="title">报修标题：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="title" name="title" size="30" value="${complaint.title}" readonly="readonly"/>
                    </div>
                </div>
                 <div class="form-group">
                    <div class="label"><label for="title">报修内容：</label></div>
                    <div class="field">
                    	 <textarea id="content" name="content" class="input" rows="5" cols="50"  readonly="readonly">${complaint.content}</textarea>
                    </div>
                </div>
                  <div class="form-group">
                    <div class="label"><label for="title">修改状态：</label></div>
                    <div class="field">
                    	      <select  class="select"  name="status" data-validate="required:请选择状态。">
                    	<option value="" selected>请选择状态</option>
                    	<option value="0" <c:if test="${complaint.status==0}">selected="selected"  </c:if>>申请</option>
                    	<option value="1" <c:if test="${complaint.status==1}">selected="selected"  </c:if>>受理</option>
                    	<option value="2" <c:if test="${complaint.status==2}">selected="selected"  </c:if>>完成</option>
                    	            	
                    	</select >
                    </div>
                </div>
                   <div class="form-group">
                    <div class="label"><label for="title">指定维修人：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="handlePerson" name="handlePerson" size="30" value="${complaint.handlePerson}"
                    	placeholder="请输入维修人" data-validate="required:请指定维护文。"/>
                    </div>
                </div>
                 <div class="form-group">
                    <div class="label"><label for="title">维修电话：</label></div>
                    <div class="field">
                    	<input type="text" class="input"  id="phone" name="phone" size="30" value="${complaint.phone}"
                    	placeholder="请输入维修电话" data-validate="required:请输入手机号,mobile:请输入正确的手机号" />
                    </div>
                </div>
         
                    <div class="form-group">
                	<div class="label"><label>是否结束：</label></div>
                	<div class="field">
                	      
                        <div class="button-group button-group-small radio">
                            <label  <c:choose>  
                            <c:when test="${complaint.flag=='0'}">  class='button active'" </c:when> 
                            <c:otherwise> class='button ' </c:otherwise>  
                            </c:choose> >  
                             <input name="flag"  value="0"  <c:if test="${complaint.flag=='0'}"> checked="checked" 
                             </c:if> type="radio"> 未完成</label>
                            <label  <c:choose>  
                            <c:when test="${complaint.flag=='1'}">  class='button active'" </c:when> 
                            <c:otherwise> class='button' </c:otherwise>  
                            </c:choose> >
                            <input name="flag" value="1" <c:if test="${complaint.flag=='1'}"> checked="checked" </c:if> type="radio"> 
                                                                                  完成</label>
                        </div>
                    </div>
                </div>
                <div class="form-button">
                <button class="button bg-main" id="${ctx}/propertyAdmin/operation/complaintUpdate"  onclick="submitFrom(this.id,'ComplaintForm')">提交</button>
                <button class="button bg-main" id="${ctx}/propertyAdmin/list/complaintList" onclick="show(this.id)">返回</button>
                
                </div>
                
            </form>
</body>
</html>