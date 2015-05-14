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
	
		var propertyIds=""; 
		$(":checkbox").each(function(i) {
		  if (this.checked == true) { 
			  propertyIds =propertyIds+ $(this).val()+","; 
	      }
		  })
		
		  if(propertyIds==""){
			  alert("请选中要删除的记录！");
			  return false;
		  }
		if(confirm('确认删除?'))
		{
	  	show(url+"?propertyIds="+propertyIds);  
	       };
	        return false;
		};
   </script>
  </head>

<body>
<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>物业列表</strong></div>
        <div class="padding border-bottom">
            <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
            <input type="button" class="button button-small border-green" id="${ctx}/commonAdmin/page/addPropertyForm" onclick="show(this.id);" value="添加物业" />
            <input type="button" class="button button-small border-yellow" onclick="deleteSeletedRecord('${ctx}/commonAdmin/operation/deleteProperties');" value="批量删除" />
       </div>
            <table id="table" class="table table-hover">
        	<tr><th width="45">选择</th><th width="45">序号</th><th width="120">物业名称</th>
        	<th width="120">物业地址</th><th width="120">联系人</th><th width="80">联系方式</th>
        	<th width="80">所在城市</th>	<th>添加时间</th><th width="100">操作</th></tr>
               <c:forEach items="${properties.content}" var="property" varStatus="xh">
	    	<tr>
            <td><input type="checkbox" name="id" value="${property.id}" /></td>
            <td>${xh.count}</td>
            <td>${property.name}</td>
            <td>${property.address}</td>
            <td>${property.contacts}</td>
            <td>${property.phone}</td>
              <td>${property.city.name}</td>
            <td>${property.addDate}</td>
         
            <td>
           
            <a class="button border-blue button-little"  style="cursor:pointer;" onclick="show('${ctx}/commonAdmin/page/updatePropertyForm?id=${property.id}');">修改</a>
             <a class="button border-yellow button-little" style="cursor:pointer;"
             onclick="{if(confirm('确认删除?')){return show('${ctx}/commonAdmin/operation/deleteProperty?id=${property.id}');}
             return false;}">删除</a></td>
            </tr>
             	</c:forEach>
                      
        </table>
         <div class="panel-foot text-center">
        <tags:pagination page="${properties}" paginationSize="5"/>
         
        </div>
    </div>
    </form>
</div>
</body>
</html>