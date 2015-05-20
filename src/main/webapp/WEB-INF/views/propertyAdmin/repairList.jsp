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
    	<div class="panel-head"><strong>社区报修列表</strong></div>
        <div class="padding border-bottom">
           
       </div>
            <table id="table" class="table table-hover">
        	<tr><th width="45">序号</th><th width="120">报修标题</th>
        	<th width="120">详细内容</th><th width="120">报修人</th><th width="120">联系方式</th>
        	<th width="120">所在社区</th><th width="80">发布时间</th><th width="80">处理状态</th>
        	<th width="80">处理人</th><th width="80">处理时间</th>
        	<th width="100">操作</th></tr>
               <c:forEach items="${repairs.content}" var="repair" varStatus="xh">
	    	<tr>
          
            <td>${xh.count}</td>
            <td>${repair.title}</td>
            <td>${repair.content}</td>            
            <td>${repair.user.name}</td>
            <td>${repair.userPhone}</td>
            <td>${repair.community.name}</td>
             
            <td>${repair.repairDate}</td>
            <td>${repair.status}</td>
            <td>${repair.handlePerson}</td>
            <td>${repair.handleDate}</td>
            <td>
             <a class="button border-blue button-little"  style="cursor:pointer;" 
             onclick="show('${ctx}/propertyAdmin/page/updateRepairForm?id=${repair.id}');">修改</a>
            </td>
            </tr>
             	</c:forEach>
                      
        </table>
         <div class="panel-foot text-center">
        <tags:pagination page="${repairs}" paginationSize="5"/>
         
        </div>
    </div>
    </form>
</div>
</body>
</html>