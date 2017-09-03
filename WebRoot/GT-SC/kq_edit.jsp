<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<style>
.ck{
	padding:3px 0;
	display:inline-block;
}
</style>
<div class="bjui-pageContent tableContent">
    <form action="updatekqb.action" class="pageForm" id="form" data-toggle="validate">            
        <table class="table table-condensed table-hover">
          <tr>
              	<td><label  class='control-label x85' >姓名:</label>
              		<input type='text' id="name" data-rule="姓名:required" size='20'  value='<s:property value="kqb.name" />'  >
              		<input type='hidden' data-rule="姓名:required"  size='20'  value='<s:property value="kqb.gw" />'  >
              	</td>
	                
	       </tr>
	       </table>
</form>
            
       
        <form class="pageForm" id="formjob" data-toggle="validate">
         	<table class="table table-condensed table-hover">
         		<tr>
	                <th>当日考勤<th>
	            </tr>
	              	<tr>
	              		
	                	<td  style="vertical-align:top">
	                	<span>计时：</span>
	                	<s:iterator value="joblist" status="sta">
	                	<div>
	                	 <p class="ck">
        					<input type="checkbox" class="checkboxip" name="jobid" value="<s:property value="secondId" />" data-toggle="icheck" data-label="<s:property value="name" />"/>
    						</p>
    					 
	                	</div>
    					</s:iterator >
	                	</td>
	                	
	                	<td style="vertical-align:top">
	                	<span>计量：</span>
	                	<s:iterator value="sizelist" status="sta">
	                	<div>
	                	 <p class="ck">
        					<input type="checkbox" class="checkboxip1" name="sizeid" value="<s:property value="sizeid" />" data-toggle="icheck" data-label="<s:property value="name" />"/>
    						</p>
    					 
	                	</div>
    					</s:iterator >
	                	</td>

	                	 </tr>
            </table>
            <input type="hidden" name="uuid" id="uuid" value="<s:property value="kqb.uuid" />">
            <input type="hidden" name="sczid" value="<s:property value="sczid" />">
            <input type="hidden" name="month"  value="<s:property value="month" />">
        	<input type="hidden" name="day"  value="<s:property value="day" />">
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
	$('.checkboxip').on('ifChecked', function(event){
		var str = [
					'<p style="display:inline-block" class="mdgb">',
					'时间：<input type="text" size="5" name="hours" value=""/>&nbsp;&nbsp;单位：(小时)',
					'</p>'   
			           ];
			var _html = str.join("");
			$(this).parent('div').parent('.ck').parent('div').append(_html);
		});
	$('.checkboxip').on('ifUnchecked', function(event){
		$(this).parent('div').parent('.ck').parent('div').children('p').remove('.mdgb');
		});
	
	$('.checkboxip1').on('ifChecked', function(event){
		var str = [
					'<p style="display:inline-block" class="mdgb">',
					'数量：<input type="text" size="5" name="number" value=""/>&nbsp;&nbsp;',
					'</p>'   
			           ];
			var _html = str.join("");
			$(this).parent('div').parent('.ck').parent('div').append(_html);
		});
	$('.checkboxip1').on('ifUnchecked', function(event){
		$(this).parent('div').parent('.ck').parent('div').children('p').remove('.mdgb');
		});


	$(function(){
		var date = new Date();
		var day = date.getDate();
		var days = 'kqb.day'+day;
		$('.'+days).val('');
		console.log(days);
	}); 
	$("#baocun").click(function(){
	    var name = $("#name").val();
	    if(name==""){
	    	return false;
	    };
	   // debugger;
	    console.log($('#formjob').serialize());
	    /*此处的保存操作加入了两张表*/
		$.ajax({
            type: "POST",
            url: "addTodayKqb.action",
            data: $('#formjob').serialize(),
            dataType: "json",
            success: function(data){
	            if(data.statusCode==200){
	         		$.ajax({
	          			type:"POST",
	          			url:"updatekqb.action",
	          			data:$('#formjob').serialize(),
	          			dataType:"json",
	          			success:function(data){
	          				if(data.statusCode==200){
		          				$(this).dialog('closeCurrent',true);
		     	          		$("#queryRS").click(); 
	          				}
	          			}
	          		});
	            }else{
	          		$(this).alertmsg('error', '操作失败');
	            }
          }
        });
	});
</script>