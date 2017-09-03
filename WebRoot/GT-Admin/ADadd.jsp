<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageContent tableContent">
    <form action="addAdmin.action" class="pageForm" id="form" data-toggle="validate">
        <input type="hidden" name="admin.uuid" value="<s:property value="admin.uuid" />">
        <input type="hidden" name="admin.createDate" value="<s:property value="admin.createDate" />">
        <input type="hidden" name="admin.password" value="<s:property value="admin.password" />">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                <tr><td><label  class='control-label x85' >用户名:</label><input type='text' id="username" data-rule="用户名:required" name='admin.username'  size='20'  value='<s:property value="admin.username" />'  ></td>
                    <td><label  class='control-label x85'>密码:</label>
                    <input type='text' id="pass" name='pass'  size='20'>
                    </td></tr>
                 <tr><td><label  class='control-label x85' >真实姓名:</label><input type='text' id="realname" name='admin.realname'  size='20'  value='<s:property value="admin.realname" />'  ></td>
                    <td><label  class='control-label x85'>联系方式:</label><input type='text' id="phone"  name='admin.phone'  size='20'   value='<s:property value="admin.phone" />'  ></td></tr>
                
                <tr><td><label  class='control-label x85'>管理员权限:</label>
                <select id="one" data-toggle="selectpicker" onchange="chang(this.options[this.options.selectedIndex].value)" name='admin.authority'  data-width="80"  value='<s:property value="admin.authority" />'  >
                    <option value="1" <s:if test="admin.authority.equals(\"1\")">selected = "selected"</s:if>>超级管理员</option>
                    <option value="2" <s:if test="admin.authority.equals(\"2\")">selected = "selected"</s:if>>人事管理员</option>
                    <option value="4" <s:if test="admin.authority.equals(\"4\")">selected = "selected"</s:if>>培训管理员</option>
                    <option value="5" <s:if test="admin.authority.equals(\"5\")">selected = "selected"</s:if>>生产管理员</option>
                    <option value="3" <s:if test="admin.authority.equals(\"3\")">selected = "selected"</s:if>>财务管理员</option>
                </select>
                <div id="second" style="display:none;" >
                <select data-toggle="selectpicker" class="scadd"  name='admin.second'  data-width="80"  value='' onchange="scadd()">
                	<option value="">请选择</option>
                    <s:iterator value="lists" status="sta">
                    	<option value="<s:property value="secondId" />" <s:if test="admin.second.equals(secondId)">selected = "selected"</s:if>><s:property value="name" /></option>
                    </s:iterator>
                </select>
                <input type="hidden" value="" name="admin.sczname" class="secondname"/ >
                </div>
                </td>
                    <td><label class='control-label x85'>邮箱:</label><input type='text'  name='admin.email'  size='20'   value='<s:property value="admin.email" />' ></td></tr>
                <tr><td colspan=2><label class='control-label x85'>备注:</label><textarea name='admin.beizhu'  cols='65' rows='5'><s:property value="admin.beizhu" /></textarea></td></tr>
                </tbody>
            </table>
        </div>
    </form>
</div>
<s:if test="type==1"></s:if><s:else>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <li><button type="button" id="baocun" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>
</s:else>
<script type="text/javascript">
	$(document).ready(function(){
	 	if($("#one").val() == 5){
	 		$("#second").css("display","inline-block");
	 	}
	});
	function chang(a){
		if(a == 5){
			$("#second").css("display","inline-block");
		}else{
			$("#second").css("display","none");
		}
	};
	function scadd(){
		 var a =$(".scadd").find("option:selected").text();
		 $(".secondname").val(a);
	};
	$("#baocun").click(function(){
		var password = $("#htNumber").val();
	    var newpass = $("#name").val();
	    if(password=="" || newpass==""){
	    	return false;
	    };
		$.ajax({
            type: "POST",
            url: "addAdmin.action",
            data: $('#form').serialize(),
            dataType: "json",
            success: function(data){
	            if(data.statusCode==200){
	            	 $(this).dialog('closeCurrent',true);

	          		$("#queryRS").click();
	            }else{
	          		$(this).alertmsg('error', '操作失败');
	            }
          }
        });
	});
</script>