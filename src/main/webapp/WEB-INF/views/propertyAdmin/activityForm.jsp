<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html lang="zh-cn">
<head>
 <script src="${ctx}/static/js/jquery.js"></script>
    <script src="${ctx}/static/js/pintuer.js"></script>
    <script src="${ctx}/static/js/ajaxfileupload.js"></script>
    
    
   
   
    <script type="text/javascript" >
    function getGuidGenerator () {   
        var S4 = function() {   
           return (((1+Math.random())*0x10000)|0).toString(16).substring(1);   
        };   
        return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());   
    };  
   
   function fileUpLoad(url){
	   var fileName = $("#pic").val();//文件名  
	    fileName = fileName.split("\\");  
	    fileName = fileName[fileName.length-1];  
	    var guid = getGuidGenerator();//唯一标识guid  
	    var data = {guid:guid};  
	    jQuery.ajaxSettings.traditional = true;  
    		
    	$.ajaxFileUpload({
    		            cache: true,
    					type: "POST",
    					dataType : 'json',
    					fileElementId:'pic',
    					url:url,
    					data:data,
    					async: false,   
    					error : function(data,status,e) { 
    						
    			            alert('Operate Failed!');  
    			        },
    				    success: function(data) {
    				    	
    				    	alert('文件上传成功!');  
    		                var next = $("#fileUpLoads").html();  
    		                $("#fileUpLoads").html("<div id='"+guid+"' > <input type='text' name='fileNames' value='"
    		                		+guid+"."+fileName+"'"/>"
    		                		+"文件:"+fileName+
    		                		"<a href='#' onclick='filedelete("+"\""+guid+"\""+","+"\""+fileName+"\""+")'>删除</a>"+"<br/></div>");  
    		                $("#fileUpLoads").append(next);  
    				    	
    				    }
    	
    				});

    };
    
    filedelete = function(guid,fileName){  
        jQuery.ajaxSettings.traditional = true;  
        var data = {guid:guid,fileName:fileName};  
        $.ajax({  
            url : '/PROJECT/function.do?method=filedelete',  
            type : 'POST',  
            dataType : 'json',  
            data:data,  
            async : false,  
            error : function() {  
                alert('Operate Failed!');  
            },  
            success : function(json) {  
                if (json.resultFlag==false){  
                    alert(json.resultMessage);  
                }else{  
                    alert('删除成功!');  
                    $("#"+guid).remove();  
                }  
            }  
        });  
    };  
   
    </script>
    </head>
<body>

<c:if test="${not empty msg}">
<div class="alert alert-yellow"><span class="close">

      </span><strong>注意：</strong>${msg}</div>

	</c:if>
    <form  id="ActivityForm"  method="post" class="form-x" >
    
     <div class="panel admin-panel">
     
    	<div class="panel-head"><strong>社区列表</strong></div>
    	 <div class="padding border-bottom">
    	 <input type="button" class="button button-small checkall" name="checkall" checkfor="communityId" value="全选" />
    	 </div>
    	 
	        <input type="hidden" name="id" value="${activity.id}"/>
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
                    <div class="label"><label for="title">活动标题：</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="title" name="title" size="30" value="${activity.title}"
                    	 placeholder="请输入活动标题" data-validate="required:请输入活动标题。" />
                    </div>
                </div>
                 <div class="form-group">
                    <div class="label"><label for="title">活动内容：</label></div>
                    <div class="field">
                    	<textarea class="input" rows="5" cols="50" id="content" name="content" 
                    	placeholder="请输入活动内容" > ${activity.content}</textarea>
                    </div>
                </div>
                  <div class="form-group">
                    <div class="label"><label for="title">活动图片：</label></div>
                    <div class="field">
                    	<a class="button input-file" href="javascript:void(0);">+ 浏览文件
                    	<input size="100" type="file" name="pic" id="pic"  
                    	onchange="fileUpLoad('${ctx}/propertyAdmin/page/uploadPic')" /></a>
                    	<div id="fileUpLoads"></div>
                    </div>
                </div>
                 <div class="form-button">
                <button class="button bg-main"  
                   <c:choose>  
                   <c:when test="${formType=='add'}">   id="${ctx}/propertyAdmin/operation/addActivity" </c:when> 
                   <c:otherwise> id="${ctx}/propertyAdmin/operation/updateActivity" </c:otherwise>  
                  </c:choose>   
                onclick="submitFromTest(this.id,'ActivityForm')">提交</button>
                <button class="button bg-main" id="${ctx}/propertyAdmin/list/activityList" onclick="show(this.id)">返回</button>
                
                </div>
                
            </form>
</body>
</html>