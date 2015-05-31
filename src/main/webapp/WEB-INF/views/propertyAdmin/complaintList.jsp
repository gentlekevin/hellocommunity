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
function updateAdmin(o){
  var firstTd=	$(o).parent().siblings().first().children().val();
  show(o.id+"?id="+firstTd);
};

   </script>
  </head>

<body>
<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>社区投诉列表</strong></div>
        <div class="padding border-bottom">
           
       </div>
            <table id="table" class="table table-hover">
        	<tr><th width="45">序号</th><th width="120">投诉标题</th>
        	<th width="120">投诉人</th><th width="120">联系方式</th>
        	<th width="120">所在社区</th><th width="80">发布时间</th><th width="80">处理状态</th>
        	<th width="80">处理人</th><th width="120">处理人联系方式</th><th width="80">处理时间</th>
        	<th width="80">操作</th></tr>
               <c:forEach items="${complaints.content}" var="complaint" varStatus="xh">
	    	<tr>
          
            <td>${xh.count}</td>
            <td>${complaint.title}</td>
            <td>${complaint.user.name}</td>
            <td>${complaint.userPhone}</td>
            <td>${complaint.community.name}</td>
            
            <td>${complaint.complaintDate}</td>
            <td>
            <c:choose>  
                            <c:when test="${complaint.status=='0'}"> 申请 </c:when>
                            <c:when test="${complaint.status=='1'}"> 受理</c:when> 
                            <c:otherwise> 完成</c:otherwise>  
                            </c:choose>  
            
            </td>
            <td>${complaint.handlePerson}</td>
            <td>${complaint.phone}</td>
            <td>${complaint.handleDate}</td>
            <td>
             <a class="button border-blue button-little"  style="cursor:pointer;" 
             onclick="show('${ctx}/propertyAdmin/page/updateComplaintForm?id=${complaint.id}');">修改</a>
            </td>
            </tr>
             	</c:forEach>
                      
        </table>
         <div class="panel-foot text-center">
        <tags:pagination page="${complaints}" paginationSize="5"/>
         
        </div>
    </div>
    </form>
</div>
</body>
</html>