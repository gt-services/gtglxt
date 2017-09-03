<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>test</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  	<span><s:property value="#session.admin.authority"/> </span>
    <input value="<s:property value="username"/>" />
    <input value="<s:property value="password"/>" /> 
    <a href="exportKqb.action">导出Excel</a>
     <a href="getroster.action">花名册</a>
    
    <form action="importExcel.action" method="post" id="myfrom" enctype="multipart/form-data">
    	<input type="file" id="excelPath" name="excelPath"/>&nbsp;&nbsp;  
   		<input type="submit"  value="导入Excel" onclick="importExcel()"/> 
    </form>
  </body>
  
  <script type="text/javascript">
  	function importExcel(){  
	    //检验导入的文件是否为Excel文件  
	    var excelPath = document.getElementById("excelPath").value;  
	    if(excelPath == null || excelPath == ''){  
	        alert("请选择要上传的Excel文件");  
	        return;  
	    }else{  
	        var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();   
	        if(fileExtend == '.xls'){  
	        }else{  
	            alert("文件格式需为'.xls'格式");  
	            return;  
	        }  
	    } 
    } 
   // document.getElementById('myform').submit();
  </script>
</html>
