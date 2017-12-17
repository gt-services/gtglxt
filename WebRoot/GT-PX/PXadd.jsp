<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageContent tableContent">
	<input type="hidden" id="type" value="<s:property value="type" />">
    <form action="addPxInfo.action" class="pageForm" id="form" data-toggle="validate">
        <input type="hidden" name="pxInfo.uuid" value="<s:property value="pxInfo.uuid" />">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                <tr><td><label  class='control-label x85' for="name">姓名:</label><input type='text' id="name" data-rule="姓名:required" name='pxInfo.name'  size='20'  value='<s:property value="pxInfo.name" />'  ></td>
                	<td><label  class='control-label x85' for="idCard">身份证:</label><input type='text' id="idCard" name='pxInfo.idCard'  size='20'  value='<s:property value="pxInfo.idCard" />'  ></td></tr>
                <tr><td><label  class='control-label x85' for="sex">性别:</label><select data-toggle="selectpicker" id="sex"  name='pxInfo.sex'  data-width="50">
                    <option value="男" <s:if test="pxInfo.sex.equals(\"男\")">selected = "selected"</s:if>>男</option>
                    <option value="女" <s:if test="pxInfo.sex.equals(\"女\")">selected = "selected"</s:if>>女</option>
                </select></td>
                    <td><label class='control-label x85'>文化程度:</label><input type='text'  name='pxInfo.levelEdu'  size='20'   value='<s:property value="pxInfo.levelEdu" />'  ></td></tr>
                <tr><td><label class='control-label x85'>单位/个人:</label><input type='text'  name='pxInfo.unitOrPerson'  size='20'  value='<s:property value="pxInfo.unitOrPerson" />'  ></td>
                    <td><label class='control-label x85'>联系电话:</label><input type='text' name='pxInfo.telephone'  size='20'   value='<s:property value="pxInfo.telephone" />'  ></td></tr>
                <tr><td><label class='control-label x85'>单位联系人:</label><input type='text' name='pxInfo.contactOfUnit'  size='20'  value='<s:property value="pxInfo.contactOfUnit" />'  ></td>
                    <td><label class='control-label x85'>单位电话:</label><input type='text' name='pxInfo.unitPhone'  size='20'   value='<s:property value="pxInfo.unitPhone" />'  ></td></tr>
                <tr><td><label class='control-label x85'>培训点:</label><input type='text'  name='pxInfo.trainPlace'  size='20'  value='<s:property value="pxInfo.trainPlace" />'  ></td>
                    <td><label class='control-label x85'>培训科目:</label><input type='text'  name='pxInfo.trainType'  size='20'  value='<s:property value="pxInfo.trainType" />'  ></td></tr>
                <tr><td>
                    <label class='control-label x85'>考试情况:</label>
                    <select data-toggle="selectpicker" name='pxInfo.test'  data-width="100">
                        <option value="">请选择</option>
                        <option value="待考" <s:if test="pxInfo.test.equals(\"待考\")">selected = "selected"</s:if>>待考</option>
                        <option value="合格" <s:if test="pxInfo.test.equals(\"合格\")">selected = "selected"</s:if>>合格</option>
                        <option value="不合格" <s:if test="pxInfo.test.equals(\"不合格\")">selected = "selected"</s:if>>不合格</option>
                    </select>
                    </td>
               		<td><label class='control-label x85'>标准金额:</label><input type='text'  name='pxInfo.standardAmount'  size='20'   value='<s:property value="pxInfo.standardAmount" />'  ></td>
           		<tr><td><label class='control-label x85'>优惠金额:</label><input type='text'  name='pxInfo.discountAmount'  size='20'   value='<s:property value="pxInfo.discountAmount" />'  ></td>
                    <td><label class='control-label x85'>培训日期:</label><input type='text' data-toggle="datepicker" name='pxInfo.signupDate'  size='20'    value='<s:property value="pxInfo.signupDate" />'  ></td></tr>
                <tr>
                    <td><label class='control-label x85'>缴费情况:</label>
                        <select data-toggle="selectpicker" id="payCost"  name='pxInfo.payCost'  data-width="100">
                        <option value="已缴费" <s:if test="pxInfo.payCost.equals(\"已缴费\")">selected = "selected"</s:if>>已缴费</option>
                        <option value="未缴费" <s:if test="pxInfo.payCost.equals(\"未缴费\")">selected = "selected"</s:if>>未缴费</option>
                        <option value="转账" <s:if test="pxInfo.payCost.equals(\"未缴费\")">selected = "selected"</s:if>>转账</option>
                        </select>
                    </td>
                    <td><label class='control-label x85'>收据号:</label><input type='text'  name='pxInfo.payNumber'  size='20'    value='<s:property value="pxInfo.payNumber" />'   ></td></tr>
                <tr>
                	<td><label class='control-label x85'>领证情况:</label>
                		<select data-toggle="selectpicker" id="licenseStatus"  name='pxInfo.licenseStatus'   data-width="100" >
	                    <option value="已领证" <s:if test="pxInfo.licenseStatus.equals(\"已领证\")">selected = "selected"</s:if>>已领证</option>
	                    <option value="未领证" <s:if test="pxInfo.licenseStatus.equals(\"未领证\")">selected = "selected"</s:if>>未领证</option>
                		</select>
                	</td>
                 </tr>
                <tr>
                    <td><label class='control-label x85'>考试日期:</label><input type='text' data-toggle="datepicker" name='pxInfo.testDate'  size='20'    value='<s:date name="pxInfo.testDate" format="yyyy-MM-dd"/>'  ></td>
                    <td><label class='control-label x85'>复审日期:</label><input type='text' data-toggle="datepicker" name='pxInfo.dueToDate'  size='20'    value='<s:date name="pxInfo.dueToDate" format="yyyy-MM-dd"/>'  ></td>
                </tr>
                <tr>
                    <td><label class='control-label x85'>补考一费用:</label><input type='text'  name='pxInfo.FirstRetestFee'  size='20'  value='<s:property value="pxInfo.FirstRetestFee" />'  ></td>
                    <td><label class='control-label x85'>补考一收据号:</label><input type='text'  name='pxInfo.FirstRetesPayNumber'  size='20'  value='<s:property value="pxInfo.FirstRetesPayNumber" />'  ></td>
                </tr>
                <tr>
                    <td><label class='control-label x85'>补考二费用:</label><input type='text'  name='pxInfo.SecondRetestFee'  size='20'  value='<s:property value="pxInfo.SecondRetestFee" />'  ></td>
                    <td><label class='control-label x85'>补考二收据号:</label><input type='text'  name='pxInfo.SecondRetesPayNumber'  size='20'  value='<s:property value="pxInfo.SecondRetesPayNumber" />'  ></td>
                </tr>
                <tr>
                    <td><label class='control-label x85'>补考三费用:</label><input type='text'  name='pxInfo.ThirdRetestFee'  size='20'  value='<s:property value="pxInfo.ThirdRetestFee" />'  ></td>
                    <td><label class='control-label x85'>补考三收据号:</label><input type='text'  name='pxInfo.ThirdRetesPayNumber'  size='20'  value='<s:property value="pxInfo.ThirdRetesPayNumber" />'  ></td>
                </tr>
                </tbody>

     </table>
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <s:if test="type==1"></s:if><s:else>
        <li><button type="button" id="baocun" class="btn-default" data-icon="save">保存</button></li>
        </s:else>
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
            url: "addPxInfo.action",
            data: $('#form').serialize(),
            dataType: "json",
            success: function(data){
	            if(data.statusCode==200){
	            	 $(this).dialog('closeCurrent',true);

	          		$("#queryPX").click();
	            }else{
	          		$(this).alertmsg('error', '操作失败');
	            }
          }
        });
	});
</script>