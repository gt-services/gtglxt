<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+"	:"+request.getServerPort()+path+"/";
%>
<div class="bjui-pageContent">
    <form id="j_pwschange_form" action="changpwd.action" data-toggle="validate" method="post">
       
        <div class="form-group">
            <label for="j_pwschange_oldpassword" class="control-label x85">旧密码：</label>
            <input type="text" data-rule="旧密码:required" name="password" id="j_pwschange_oldpassword" value="" placeholder="旧密码" size="20">
        </div>
        <div class="form-group" style="margin: 20px 0 20px; ">
            <label for="j_pwschange_newpassword" class="control-label x85">新密码：</label>
            <input type="password" data-rule="新密码:required" name="pass" id="j_pwschange_newpassword" value="" placeholder="新密码" size="20">
        </div>
        <div class="form-group">
            <label for="j_pwschange_secpassword" class="control-label x85">确认密码：</label>
            <input type="password" data-rule="required;match(pass)" name="" id="j_pwschange_secpassword" value="" placeholder="确认新密码" size="20">
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
	
    var password = $("#j_pwschange_oldpassword").val();
    var newpass = $("#j_pwschange_newpassword").val();
    var secpass = $("#j_pwschange_secpassword").val();
    if(password=="" || newpass=="" || secpass==""){
    	return false;
    }
    
    $.post("changpwd.action",{"password":password,"pass":newpass},function(data){//url为处理方法地址
	 	if(data.status == 200){
	        $(this).alertmsg('ok', data.msg, {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
	    }else{
	    	$(this).alertmsg('error', data.msg, {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
	    }
    })
}) 
</script>