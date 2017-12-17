<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageContent tableContent">
	<input type="hidden" id="type" value="<s:property value="type" />">
    <form action="addroster.action" class="pageForm" id="form" data-toggle="validate">
        <input type="hidden" name="roster.uuid" value="<s:property value="roster.uuid" />">
        <input type="hidden" name="roster.createDate" value="<s:property value="roster.createDate" />">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                <tr><td><label  class='control-label x85' >合同编号:</label><input type='text' id="htNumber" name='roster.htNumber'  size='20'  value='<s:property value="roster.htNumber" />'  readonly="readonly"></td>
                    <td><label  class='control-label x85'>姓名:</label><input type='text' id="name" data-rule="姓名:required"  name='roster.name'  size='20'   value='<s:property value="roster.name" />'  ></td></tr>
                <tr><td><label  class='control-label x85'>性别:</label><select data-toggle="selectpicker"  name='roster.sex'  data-width="50"  value='<s:property value="roster.sex" />'  >
                    <option value="男" <s:if test="roster.sex.equals(\"男\")">selected = "selected"</s:if>>男</option>
                    <option value="女" <s:if test="roster.sex.equals(\"女\")">selected = "selected"</s:if>>女</option>
                </select></td>
                    <td><label class='control-label x85'>身份证:</label><input type='text'  name='roster.identityId'  size='20'   value='<s:property value="roster.identityId" />'  ></td></tr>
                 <tr>
                    	<td><label class='control-label x85'>银行卡号:</label><input type='text'  name='roster.bankCard'  size='20'   value='<s:property value="roster.bankCard" />'  ></td>
                    	<td><label class='control-label x85'>人事来源:</label><input type='text'  name='roster.source'  size='20'   value='<s:property value="roster.source" />'  ></td>
                </tr>
                <tr><td><label class='control-label x85'>保险编号:</label><input type='text'  name='roster.sbNumber'  size='20'  value='<s:property value="roster.sbNumber" />'  ></td>
                    <td><label class='control-label x85'>家庭住址:</label><input type='text' name='roster.address'  size='20'   value='<s:property value="roster.address" />'  ></td></tr>
                <tr><td><label class='control-label x85'>合同开始日期:</label><input type='text' data-toggle="datepicker" name='roster.htStart'  size='20'  value='<s:date name="roster.htStart" format="yyyy-MM-dd"/>'  ></td>
                    <td><label class='control-label x85'>合同结束日期:</label><input type='text' data-toggle="datepicker" name='roster.htEnd'  size='20'   value='<s:date name="roster.htEnd" format="yyyy-MM-dd"/>'   readonly="readonly"></td></tr>
                <tr><td><label class='control-label x85'>工种:</label><input type='text'  name='roster.jobType'  size='20'  value='<s:property value="roster.jobType" />'  ></td>
                    <td><label class='control-label x85'>电话:</label><input type='text'  name='roster.phone'  size='20'   value='<s:property value="roster.phone" />'  ></td></tr>
                <tr><td><label class='control-label x85'>社保开始日期:</label><input type='text' data-toggle="datepicker" name='roster.sbStart'  size='20'  value='<s:date name="roster.sbStart" format="yyyy-MM-dd"/>'   readonly="readonly"></td>
                    <td><label class='control-label x85'>社保结束日期:</label><input type='text' data-toggle="datepicker" name='roster.sbEnd'  size='20'   value='<s:date name="roster.sbEnd" format="yyyy-MM-dd"/>'   readonly="readonly"></td></tr>
                <tr><td><label class='control-label x85'>保险类型:</label><input type='text'  name='roster.bxType'  size='20'  value='<s:property value="roster.bxType" />' readonly="readonly" ></td>
                    <td><label class='control-label x85'>离厂日期:</label><input type='text' data-toggle="datepicker" name='roster.lcTime' id ="lctime" size='20'   value='<s:date name="roster.lcTime" format="yyyy-MM-dd HH:mm:ss"/>'   ></td></tr>
                <tr><td colspan="1"><label  class='control-label x85'>工伤处理情况:</label><select data-toggle="selectpicker"  name='roster.gsqk'  data-width="80"  value='<s:property value="roster.gsqk" />'  >
                    <option value="未受工伤" <s:if test="roster.gsqk.equals(\"未受工伤\")">selected = "selected"</s:if>>未受工伤</option>
                    <option value="工伤中" <s:if test="roster.gsqk.equals(\"工伤中\")">selected = "selected"</s:if>>工伤中</option>
                    <option value="已处理" <s:if test="roster.gsqk.equals(\"已处理\")">selected = "selected"</s:if>>已处理</option>
                </select></td>
                <td colspan="1"><label  class='control-label x85'>生产组:</label>
                <select data-toggle="selectpicker"  name='roster.scz'  data-width="80">
                    <s:iterator value="lists" status="sta">
                    	<option value="<s:property value="name" />" <s:if test="roster.scz == name">selected = "selected"</s:if>><s:property value="name" /></option>
                    </s:iterator>
                </select></td>
                    <tr><td><label class='control-label x85'>工伤开始日期:</label><input type='text' data-toggle="datepicker"  name='roster.gsStart'  size='20'  value='<s:date name="roster.gsStart" format="yyyy-MM-dd"/>'  ></td>
                    <td><label class='control-label x85'>工伤结束日期:</label><input type='text' data-toggle="datepicker" name='roster.gsEnd'  size='20'   value='<s:date name="roster.gsEnd" format="yyyy-MM-dd"/>'  readonly="readonly" ></td></tr>
                <tr><td colspan=2><label class='control-label x85'>备注:</label><textarea name='roster.beizhu'  cols='65' rows='5'><s:property value="roster.beizhu" /></textarea></td></tr>
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
/* $(function () {
    $("#lctime").datepicker({pattern:"yyyy-MM-dd HH:mm:ss"});
}) */
	$("#baocun").click(function(){		
	    var newpass = $("#name").val();
	    if(newpass==""){
	    	return false;
	    };
		$.ajax({
            type: "POST",
            url: "addroster.action",
            data: $('#form').serialize(),
            dataType: "json",
            success: function(data){
	            if(data.statusCode==200){
	            	 $(this).dialog('closeCurrent',true);

	          		$("#clearQuery").click();
	            }else{
	          		$(this).alertmsg('error', '操作失败');
	            }
          }
        });
	});
</script>