<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageContent tableContent">
    <form action="addSize.action" class="pageForm" id="form" data-toggle="validate">
        <input type="hidden" name="size.uuid" value="<s:property value="size.uuid" />">
        <input type="hidden" name="size.sizeid" value="<s:property value="size.sizeid" />">
        <input type="hidden" name="size.createDate" value="<s:property value="size.createDate" />">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                <tr><td colspan="1"><label  class='control-label x85' >规格名称:</label><input type='text' id="name" data-rule="岗位名称:required" name='size.name'  size='20'  value='<s:property value="size.name" />'  ></td>
                 <s:if test="#session.admin.authority.equals(\"1\")">
                 <td colspan="1"><label  class='control-label x85'>生产组:</label>
                <select data-toggle="selectpicker"  name='size.scz'  data-width="80">
                    <s:iterator value="lists" status="sta">
                    <s:if test="secondId==scz">
                    
                    	<option value="<s:property value="secondId"/>" selected="selected"><s:property value="name" /></option>
                    	</s:if>
                    	<s:else>
                    	<option value="<s:property value="secondId"/>"><s:property value="name" /></option>
                    	</s:else>
                    </s:iterator>
                </select></td>
                </s:if>
                 </tr>
                <tr><td colspan=2><label class='control-label x85'>备注:</label><textarea name='size.beizhu'  cols='65' rows='5'><s:property value="size.beizhu" /></textarea></td></tr>
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
            url: "addSize.action",
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