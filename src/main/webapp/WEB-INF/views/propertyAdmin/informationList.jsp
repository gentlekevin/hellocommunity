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
	
		var informationIds=""; 
		$(":checkbox").each(function(i) {
		  if (this.checked == true) { 
			  informationIds =informationIds+ $(this).val()+","; 
	      }
		  })
		
		  if(informationIds==""){
			  alert("请选中要删除的记录！");
			  return false;
		  }
		if(confirm('确认删除?'))
		{
	  	show(url+"?informationIds="+informationIds);  
	       };
	        return false;
		};
   </script>
  </head>

<body>
<div class="admin">
	<form method="post">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>社区资讯列表</strong></div>
        <div class="padding border-bottom">
            <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" />
               
            <c:if test="${user.property.name!=null}"><!-- 只能物业管理员能进行增加资讯信息 -->
            <input type="button" class="button button-small border-green" id="${ctx}/propertyAdmin/page/addInformationForm" onclick="show(this.id);" value="添加社区资讯" />
            </c:if>
            
            <input type="button" class="button button-small border-yellow" onclick="deleteSeletedRecord('${ctx}/propertyAdmin/operation/deleteInformations');" value="批量删除" />
       </div>
            <table id="table" class="table table-hover">
        	<tr><th width="45">选择</th><th width="45">序号</th><th width="120">资讯标题</th>
        	<th width="120">所在物业</th><th width="80">发布时间</th>
        	<th width="100">操作</th></tr>
               <c:forEach items="${informations.content}" var="information" varStatus="xh">
	    	<tr>
            <td><input type="checkbox" name="id" value="${information.id}" /></td>
            <td>${xh.count}</td>
            <td>${information.title}</td>
                    
            <td>${information.property.name}</td>
            <td>${information.publishDate}</td>
             <td>
           <c:if test="${user.property.name!=null}"><!-- 只能物业管理员能进行增加资讯信息 -->
            <a class="button border-blue button-little"  style="cursor:pointer;" onclick="show('${ctx}/propertyAdmin/page/updateInformationForm?id=${information.id}');">修改</a>
            </c:if>
             <a class="button border-yellow button-little" style="cursor:pointer;"
             onclick="{if(confirm('确认删除?')){return show('${ctx}/propertyAdmin/operation/deleteInformation?id=${information.id}');}
             return false;}">删除</a></td>
            </tr>
             	</c:forEach>
                      
        </table>
         <div class="panel-foot text-center">
        <tags:pagination page="${informations}" paginationSize="5"/>
         
        </div>
    </div>
    </form>
</div>
</body>
</html>