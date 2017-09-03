<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">

#form input{
	width:25%;
	margin-top:3px
}

.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control{
	round:#ffffff;
}

#form span{
	display:inline-block;
	width:20%;
	font-weight:bold;
	text-align:center;
	vertical-align:middle;
}

</style>
<div class="bjui-pageContent tableContent">
    <form action="" class="pageForm" id="form" data-toggle="validate">
       	<input type="hidden" name="" value="<s:property value="kqb.scz"/>" readonly="readonly">
        <input type="hidden" name="type" value="<s:property value="type"/>" readonly="readonly">
      	<input name="uuid" type="hidden" value="<s:property value="kqb.uuid" />"readonly="readonly">
       	<span>姓名：</span><input name="kqb.name" value="<s:property value="kqb.name"/>"  readonly="readonly">
        <span>生产组：</span><input name="scz" value="<s:property value="scz"/>"  readonly="readonly"><br/>
        <span>岗位：</span><input name="kqb.gw" value="<s:property value="kqb.gw"/>" readonly="readonly">
        <span>年份：</span><input name="kqb.year" value="<s:property value="kqb.year" />"readonly="readonly" style="width:15%">
        <span>月份：</span><input name="kqb.month" value="<s:property value="kqb.month" />"readonly="readonly" style="width:15%"><br/><br/><br/>
        <span>健康证报销：</span><input name="kqb.submit_healcard" class="" value="<s:property value="kqb.submit_healcard" />">
        <span>水电费扣款：</span><input name="kqb.cut_waterandele" class="" value="<s:property value="kqb.cut_waterandele" />" ><br/>
        <span>叉车证扣款：</span><input name="kqb.cut_forkcard" class="" value="<s:property value="kqb.cut_forkcard" />">
        <span>叉车证还款：</span><input name="kqb.repay_forkcard" class="" value="<s:property value="kqb.repay_forkcard" />"><br/>
        <span>员工还款：</span><input name="kqb.repay_staff" class="" value="<s:property value="kqb.repay_staff" />">
        <span>员工借款：</span><input name="kqb.borrow_staff" class="" value="<s:property value="kqb.borrow_staff" />"><br/>
        <span>住宿扣款：</span><input name="kqb.cut_stay" class="" value="<s:property value="kqb.cut_stay" />">
        <span>工作服和鞋扣款：</span><input name="kqb.cut_clothesandshoes" class="" value="<s:property value="kqb.cut_clothesandshoes" />"><br/>
        <span>工作服和鞋还款：</span><input name="kqb.repay_clothesandshoes" class="" value="<s:property value="kqb.repay_clothesandshoes"/>" >
        <span>餐补项：</span><input name="kqb.canbu" class="" value="<s:property value="kqb.canbu"/>" ><br/>
	    <div style="margin-top:10px">
	    	<span>其他扣款：</span>
	        <textarea cols="60" rows="6" name="kqb.cut_else" style="display:inline-block;vertical-align:middle;">
	         <s:property value="kqb.cut_else" />
	        </textarea>
	    </div>  
	        
        <br/>

    </form>
</div>

<div class="bjui-pageFooter">
    <ul>
     	<li><button type="button" id="baocun" class="btn-default" data-icon="save">保存</button></li>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>


<script>
$("#baocun").click(function(){
	$.ajax({
        type: "POST",
        url: "cutEdit.action",
        data: $('#form').serialize(),
        dataType: "json",
        success: function(data){
        	if(data.statusCode==200){
  				$(this).dialog('closeCurrent',true);
	          		/* $("#queryRS").click(); */
				}
      }
    });
});
</script>
