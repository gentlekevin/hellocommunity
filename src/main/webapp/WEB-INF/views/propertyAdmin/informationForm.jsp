<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html lang="zh-cn">
<head>

      <link rel="stylesheet" href="${ctx}/static/css/default.css" />
     <script charset="utf-8" src="${ctx}/static/js/kindeditor-all-min.js"></script>
        <script src="${ctx}/static/js/jquery.js"></script>
	   <script src="${ctx}/static/js/pintuer.js"></script>
   <script type="text/javascript" >
   $(function()  {
	   var path = $("#path").val();
	 
			var editor = KindEditor.create('#content',{
				
				uploadJson : path+"/propertyAdmin/upload/uploadPic",
				 allowImageUpload : true, 
				items:[
				        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'code', 'cut', 'copy', 'paste',
				        'plainpaste',  '|', 'justifyleft', 'justifycenter', 'justifyright',
				        'justifyfull',   'selectall', '|',  'formatblock', 'fontname', 'fontsize', '|', 'bold',
				         'underline',  'lineheight', 'removeformat', '|', 'image', 'link', 'unlink'
				     ],
				     afterBlur: function(){this.sync();}
				
			});
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
 <input id="path" hidden="true" value="${ctx}">
<c:if test="${not empty msg}">
<div class="alert alert-yellow"><span class="close">

      </span><strong>注意：</strong>${msg}</div>

	</c:if>
    
       <form  id="InformationForm"  method="post" class="form-x" enctype="multipart/form-data">
     <div class="panel community-panel" >
   
       	<div class="panel-head"><strong>社区列表</strong></div>
    	 <div class="padding border-bottom">
    	 
    	 <input type="button" class="button button-small checkall" name="checkall" checkfor="communityId" value="全选" />
    	 </div>
    	 
	        <input type="hidden" name="id" value="${information.id}"/>
	        <input type="hidden" name="pic" value="${information.pic}"/>
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
                    <div  class="label"><label for="title">资讯标题：</label></div>
                    <div style="width:50%"  class="field">
                    	<input type="text" class="input" id="title" name="title" size="30" value="${information.title}"
                    	 placeholder="请输入资讯标题" data-validate="required:请输入资讯标题。" />
                    </div>
                </div>
                 <div class="form-group">
                    <div class="label"><label for="title">资讯内容：</label></div>
                    <div class="field">
                   
                  <textarea id="content" name="content"  style="width:56%;height:250px;visibility:hidden;"data-validate="required:请输入资讯内容。">
                  ${information.content}</textarea>
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