<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent tableContent">
  <s:iterator value="piclist" var="user" id="n">  
        <img src ='<s:property value ="n" />' width="250" height="400"> 
    </s:iterator>  
</div>


