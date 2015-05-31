<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>

 <script src="${ctx}/static/js/jquery.js"></script>
    <script src="${ctx}/static/js/pintuer.js"></script>
    
<script type="text/javascript" >
$(document).ready(function(){ 
	}); 

   </script>
  </head>

<body>
<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>物业管理员列表</strong></div>
        
            <table id="table" class="table table-hover">
        	<tr><th width="45">序号</th><th width="120">登录账号</th>
        	<th width="120">角色</th><th width="120">姓名</th><th width="80">性别</th>
        	<th width="120">社区</th><th width="*">添加时间</th></tr>
               <c:forEach items="${users.content}" var="user" varStatus="xh">
	    	<tr>
            <td>${xh.count}</td>
            <td>${user.phone}</td>
            <td>App用户</td>
            <td>${user.name}</td>
            <td>  <c:choose>  
                   <c:when test="${user.sex=='1'}"> 男 </c:when> 
                   <c:when test="${user.sex=='0'}"> 女 </c:when> 
                   <c:otherwise>  </c:otherwise>  
                  </c:choose>   
            </td>
          
            <td>${user.community.name}</td>
            <td>${user.registerDate}</td>
            
            </tr>
             	</c:forEach>
                      
        </table>
         <div class="panel-foot text-center">
        <tags:pagination page="${users}" paginationSize="5"/>
         
        </div>
    </div>
    </form>
</div>
</body>
</html>