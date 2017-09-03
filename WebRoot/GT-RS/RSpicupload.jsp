<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent tableContent">
    <s:form id ="fileform" action ="fileUpload.action" method ="POST" enctype ="multipart/form-data" target="nm_iframe">  
  		<input type="hidden" name="uuid" value="<s:property value="uuid" />">
                  <s:fielderror />  
  
                  <s:file name ="myFile" label ="Image File1"/>  
  
                  <s:file name ="myFile" label ="Image File2"/>   
  
                  <s:textfield name ="caption" label ="Caption"  type="hidden"/>  
  
				  <s:submit value="上传图片" id="picsubmit" />  
  
    </s:form> 
    <iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>   
<script type="text/javascript">
$("#picsubmit").click(function(){	
        setTimeout("$(this).dialog('closeCurrent',true);",3000);
	
}); 
</script>
