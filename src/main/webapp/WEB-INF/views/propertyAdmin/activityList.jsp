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
	
		var activityIds=""; 
		$(":checkbox").each(function(i) {
		  if (this.checked == true) { 
			  activityIds =activityIds+ $(this).val()+","; 
	      }
		  })
		
		  if(activityIds==""){
			  alert("请选中要删除的记录！");
			  return false;
		  }
		if(confirm('确认删除?'))
		{
	  	show(url+"?activityIds="+activityIds);  
	       };
	        return false;
		};
   </script>
  </head>

<body>
<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>社区活动列表</strong></div>
        <div class="padding border-bottom">
            <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
               
            <c:if test="${user.property.name!=null}"><!-- 只能物业管理员能进行增加活动信息 -->
            <input type="button" class="button button-small border-green" id="${ctx}/propertyAdmin/page/addActivityForm" onclick="show(this.id);" value="添加社区活动" />
            </c:if>
            
            <input type="button" class="button button-small border-yellow" onclick="deleteSeletedRecord('${ctx}/propertyAdmin/operation/deleteActivities');" value="批量删除" />
       </div>
            <table id="table" class="table table-hover">
        	<tr><th width="45">选择</th><th width="45">序号</th><th width="120">活动标题</th>
        	<th width="120">所在物业</th><th width="80">发布时间</th>
        	<th width="100">操作</th></tr>
               <c:forEach items="${activities.content}" var="activity" varStatus="xh">
	    	<tr>
            <td><input type="checkbox" name="id" value="${activity.id}" /></td>
            <td>${xh.count}</td>
            <td>${activity.title}</td>
                     
            <td>${activity.property.name}</td>
            <td>${activity.publishDate}</td>
             <td>
           
            <a class="button border-blue button-little"  style="cursor:pointer;" onclick="show('${ctx}/propertyAdmin/page/updateActivityForm?id=${activity.id}');">修改</a>
             <a class="button border-yellow button-little" style="cursor:pointer;"
             onclick="{if(confirm('确认删除?')){return show('${ctx}/propertyAdmin/operation/deleteActivity?id=${activity.id}');}
             return false;}">删除</a></td>
            </tr>
             	</c:forEach>
                      
        </table>
         <div class="panel-foot text-center">
        <tags:pagination page="${activities}" paginationSize="5"/>
         
        </div>
    </div>
    </form>
</div>
</body>
</html>