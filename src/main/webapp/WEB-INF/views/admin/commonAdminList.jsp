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
function deleteSeletedRecord(url){
	if(confirm('确认删除?'))
	{
		var userIds=""; 
		$(":checkbox").each(function(i) {
		  if (this.checked == true) { 
			  userIds =userIds+ $(this).val()+","; 
	      }
		  })
	  	show(url+"?userIds="+userIds);  
	       };
	        return false;
		};
   </script>
  </head>

<body>
<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>普通管理员列表</strong></div>
        <div class="padding border-bottom">
            <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
            <input type="button" class="button button-small border-green" id="${ctx}/admin/page/addCommonAdminForm" onclick="show(this.id);" value="添加管理员" />
            <input type="button" class="button button-small border-yellow" onclick="deleteSeletedRecord('${ctx}/admin/operation/deleteCommonAdmins');" value="批量删除" />
       </div>
            <table id="table" class="table table-hover">
        	<tr><th width="45">选择</th><th width="45">序号</th><th width="120">登录账号</th><th width="120">角色</th><th width="120">姓名</th><th width="80">性别</th><th width="120">手机</th><th width="*">添加时间</th><th width="100">操作</th></tr>
               <c:forEach items="${users.content}" var="user" varStatus="xh">
	    	<tr>
            <td><input type="checkbox" name="id" value="${user.id}" /></td>
            <td>${xh.count}</td>
            <td>${user.loginName}</td>
            <td>普通管理员</td>
            <td>${user.name}</td>
            <td>  <c:choose>  
                   <c:when test="${user.sex=='1'}"> 男 </c:when> 
                   <c:otherwise> 女 </c:otherwise>  
                  </c:choose>   
            </td>
            <td>${user.phone}</td>
            <td>${user.registerDate}</td>
            <td>
           
            <a class="button border-blue button-little"  style="cursor:pointer;" onclick="show('${ctx}/admin/page/updateCommonAdminForm?id=${user.id}');">修改</a>
             <a class="button border-yellow button-little" style="cursor:pointer;"
             onclick="{if(confirm('确认删除?')){return show('${ctx}/admin/operation/deleteCommonAdmin?id=${user.id}');}
             return false;}">删除</a></td>
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