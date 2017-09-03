<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="bjui-pageContent">
    <form id="myinfo_form" action="myinfo.action" data-toggle="validate" method="post">
       
        <div class="form-group">
            <label for="realname" class="control-label x85">真实姓名：</label>
            <input type="text"  name="realname" id="realname" value="<s:property value="#session.admin.realname" />" placeholder="" size="20">
        </div>
        <div class="form-group" style="margin: 20px 0 20px; ">
            <label for="phone" class="control-label x85">联系方式：</label>
            <input type="text"  name="phone" id="phone" value="<s:property value="#session.admin.phone" />" placeholder="" size="20">
        </div>
        <div class="form-group">
            <label for="email" class="control-label x85">邮&nbsp;&nbsp;箱：</label>
            <input type="text"  name="email" id="email" value="<s:property value="#session.admin.email" />" placeholder="" size="20">
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-close">取消</button></li>
        <li><button type="button" class="btn btn-default" id="baocun">保存</button></li>
    </ul>
</div>
<script type="text/javascript">

$("#baocun").click(function(){
	
    var realname = $("#realname").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    
    $.post("myinfo.action",{"realname":realname,"phone":phone,"email":email},function(data){//url为处理方法地址
 
    	$(this).alertmsg('ok', data.msg, {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
    })
}) 
</script>