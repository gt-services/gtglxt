<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageContent tableContent">
    <form action="addsecond.action" class="pageForm" id="form" data-toggle="validate">
        <input type="hidden" name="second.uuid" value="<s:property value="second.uuid" />">
        <input type="hidden" name="second.secondId" value="<s:property value="second.secondId" />">
        <input type="hidden" name="second.createDate" value="<s:property value="second.createDate" />">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                <tr><td><label  class='control-label x85' >项目组名称:</label><input type='text' id="name" data-rule="项目组名称:required" name='second.name'  size='20'  value='<s:property value="second.name" />'  ></td>
                 </tr>
                <tr><td colspan=2><label class='control-label x85'>备注:</label><textarea name='second.beizhu'  cols='65' rows='5'><s:property value="second.beizhu" /></textarea></td></tr>
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
	
	$("#baocun").click(function(){
	    var name = $("#name").val();
	    if(name==""){
	    	return false;
	    };
		$.ajax({
            type: "POST",
            url: "addsecond.action",
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