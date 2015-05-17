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
	
		var communityIds=""; 
		$(":checkbox").each(function(i) {
		  if (this.checked == true) { 
			  communityIds =communityIds+ $(this).val()+","; 
	      }
		  })
		
		  if(communityIds==""){
			  alert("请选中要删除的记录！");
			  return false;
		  }
		if(confirm('确认删除?'))
		{
	  	show(url+"?communityIds="+communityIds);  
	       };
	        return false;
		};
   </script>
  </head>

<body>
<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>社区列表</strong></div>
        <div class="padding border-bottom">
            <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
            <input type="button" class="button button-small border-green" id="${ctx}/propertyAdmin/page/addCommunityForm" onclick="show(this.id);" value="添加社区" />
            <input type="button" class="button button-small border-yellow" onclick="deleteSeletedRecord('${ctx}/propertyAdmin/operation/deleteCommunities');" value="批量删除" />
       </div>
            <table id="table" class="table table-hover">
        	<tr><th width="45">选择</th><th width="45">序号</th><th width="200">社区名称</th>
        	<th width="120">社区地址</th><th width="120">联系人</th><th width="120">联系方式</th>
        	<th width="200">所在物业</th>	<th>添加时间</th><th width="100">操作</th></tr>
               <c:forEach items="${communities.content}" var="community" varStatus="xh">
	    	<tr>
            <td><input type="checkbox" name="id" value="${community.id}" /></td>
            <td>${xh.count}</td>
            <td>${community.name}</td>
            <td>${community.address}</td>
            <td>${community.contacts}</td>
            <td>${community.phone}</td>
            <td>${community.property.name}</td>
            <td>${community.addDate}</td>
         
            <td>
           
            <a class="button border-blue button-little"  style="cursor:pointer;" onclick="show('${ctx}/propertyAdmin/page/updateCommunityForm?id=${community.id}');">修改</a>
             <a class="button border-yellow button-little" style="cursor:pointer;"
             onclick="{if(confirm('确认删除?')){return show('${ctx}/propertyAdmin/operation/deleteCommunity?id=${community.id}');}
             return false;}">删除</a></td>
            </tr>
             	</c:forEach>
                      
        </table>
         <div class="panel-foot text-center">
        <tags:pagination page="${communities}" paginationSize="5"/>
         
        </div>
    </div>
    </form>
</div>
</body>
</html>