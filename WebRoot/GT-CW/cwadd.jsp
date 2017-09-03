<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageContent tableContent">
    <form action="addcw.action" class="pageForm" id="form" data-toggle="validate">
    	<input type="hidden" name="cw.createDate" value="<s:property value="cw.createDate" />">
        <input type="hidden" name="cw.uuid" value="<s:property value="cw.uuid" />">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
	                <tr><td><label  class='control-label x85' >姓名:</label>
	                		<input type='text' id="name" data-rule="姓名:required" name='cw.name'  size='20'  value='<s:property value="cw.name" />'  >
	                	</td>
	                    <td><label  class='control-label x85'>银行卡号:</label>
	                    	<input type='text' id="bankcard" name='cw.bankcard'  size='20' value='<s:property value="cw.bankcard" />'>
	                    </td>
	                </tr>
	                <tr>
	                	<td>
	                		<label class='control-label x85'>年月：</label>
				            <select data-toggle="selectpicker"  name='cw.year'  data-width="80">
				            		<option value="">请选择</option>
				                    <option value="<s:property value="2016" />" <s:if test="cw.year==2016">selected = "selected"</s:if>>2016</option>
				                    <option value="<s:property value="2017" />" <s:if test="cw.year==2017">selected = "selected"</s:if>>2017</option>
				                    <option value="<s:property value="2018" />" <s:if test="cw.year==2018">selected = "selected"</s:if>>2018</option>
				                    <option value="<s:property value="2019" />" <s:if test="cw.year==2019">selected = "selected"</s:if>>2019</option>
				                    <option value="<s:property value="2020" />" <s:if test="cw.year==2020">selected = "selected"</s:if>>2020</option>
				            </select>
				            <select data-toggle="selectpicker"  name='cw.month'  data-width="80">
				            		<option value="">请选择</option>
				            		<option value="<s:property value="1" />" <s:if test="cw.month==1">selected = "selected"</s:if>>1</option>
				                    <option value="<s:property value="2" />" <s:if test="cw.month==2">selected = "selected"</s:if>>2</option>
				                    <option value="<s:property value="3" />" <s:if test="cw.month==3">selected = "selected"</s:if>>3</option>
				                    <option value="<s:property value="4" />" <s:if test="cw.month==4">selected = "selected"</s:if>>4</option>
				                    <option value="<s:property value="5" />" <s:if test="cw.month==5">selected = "selected"</s:if>>5</option>
				                    <option value="<s:property value="6" />" <s:if test="cw.month==6">selected = "selected"</s:if>>6</option>
				                    <option value="<s:property value="7" />" <s:if test="cw.month==7">selected = "selected"</s:if>>7</option>
				                    <option value="<s:property value="8" />" <s:if test="cw.month==8">selected = "selected"</s:if>>8</option>
				                    <option value="<s:property value="9" />" <s:if test="cw.month==9">selected = "selected"</s:if>>9</option>
				                    <option value="<s:property value="10" />" <s:if test="cw.month==10">selected = "selected"</s:if>>10</option>
				                    <option value="<s:property value="12" />" <s:if test="cw.month==11">selected = "selected"</s:if>>11</option>
				                    <option value="<s:property value="12" />" <s:if test="cw.month==12">selected = "selected"</s:if>>12</option>
				            </select>
	                	</td>
	                	<td>
				            <label class='control-label x85'>生产组：</label>
				            <select data-toggle="selectpicker"  name='cw.scz'  data-width="80">
				            		<option value="">请选择</option>
				                    <s:iterator value="lists" status="sta">
				                    	<option value="<s:property value="secondId" />" <s:if test="scz==secondId">selected = "selected"</s:if>><s:property value="name" /></option>
				                    </s:iterator>
				            </select>
	                	</td>
	                </tr>
	                <tr><td><label  class='control-label x85' >金额:</label>
	                		<input type='text' id="salary " name='cw.salary'  size='20'  value='<s:property value="cw.salary" />'  >
	                	</td>
	                    <td>
	                    <label class='control-label x85'>发放情况：</label>
				            <select data-toggle="selectpicker"  name='cw.status'  data-width="80">
				            		<option value="">请选择</option>
				                    <option value="<s:property value="0" />" <s:if test="cw.status==0">selected = "selected"</s:if>>未发放</option>
				                    <option value="<s:property value="1" />" <s:if test="cw.status==1">selected = "selected"</s:if>>已发放</option>
				            </select>
				        </td>
	                </tr>
	                <tr><td colspan=2><label class='control-label x85'>备注:</label><textarea name='cw.beizhu'  cols='65' rows='5'><s:property value="cw.beizhu" /></textarea></td></tr>
                </tbody>
            </table>
        </div>
    </form>
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <li><button type="button" id="baocun" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>

<script type="text/javascript">
	$("#baocun").click(function(){
	    var name = $("#name").val();
	    if(name==""){
	    	return false;
	    };
		$.ajax({
            type: "POST",
            url: "addcw.action",
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