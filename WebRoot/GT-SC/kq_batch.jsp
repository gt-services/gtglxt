<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<style>
.ck{
	padding:3px 0;
	display:inline-block;
}

.ck .icheckbox_minimal-purple:nth-child(1){
	display: none;
}

.ck .ilabel:nth-child(2){
	display: none;
}

.nameselect:hover{
		background: #d9e7f2;
	}

.nameselect2:hover{
	background: #d9e7f2;
}

.nameselect{
	display:inline-block;
	vertical-align: middle;
	width: 150px;
	padding:20px;
}

.nameselect2{
	display:inline-block;
	vertical-align: middle;
	padding:20px;
}


.nameselect2 input{
	display: inline-block;
	vertical-align: middle;
	width: 20px;
	height: 20px;
}

.nameselect2 span{
	display: inline-block;
	vertical-align: middle;
	width: 50px;
}


.nameselect input{
	display: inline-block;
	vertical-align: middle;
	width: 20px;
	height: 20px;
}

.nameselect span{
	display: inline-block;
	vertical-align: middle;
	width: 50px;
}

	.kqtd-title{
		display: block;
		width: 100%;
		padding:5px 20px;
		font-size: 14px;
		font-family: "PingFang SC";
		font-weight: 600;
	}

	.kqbatch-td{
		width: 100%;
		border:0;
	}
</style>
<div class="bjui-pageContent tableContent">
            
       
        <form class="pageForm" id="formjob" data-toggle="validate">
         	<table class="table table-condensed" style="width: 100%">
	              	<tr>
	                	<td class="kqbatch-td" style="border:0">
	                	<span class="kqtd-title">计时岗位：</span>
	                	<s:iterator value="joblist" status="sta">
	                	<div class="nameselect2">
	                	 <p class="ck">
							 <input type="checkbox" name="jobname" value="<s:property value="name" />" data-toggle="icheck" data-label="<s:property value="name" />"/>
							 <input type="checkbox" class="checkboxip" name="jobid" value="<s:property value="secondId" />" data-toggle="icheck" data-label="<s:property value="name" />"/>
    						</p>
    					 
	                	</div>
    					</s:iterator >
	                	</td>

	                	 </tr>
				<tr>
					<td class="kqbatch-td" style="border:0">
						<span class="kqtd-title">计量岗位：</span>
						<s:iterator value="sizelist" status="sta">
							<div class="nameselect2">
								<p class="ck">
									<input type="checkbox"  name="sizename" value="<s:property value="name" />" data-toggle="icheck" data-label="<s:property value="name" />"/>
									<input type="checkbox" class="checkboxip1" name="sizeid" value="<s:property value="sizeid" />" data-toggle="icheck" data-label="<s:property value="name" />"/>
								</p>

							</div>
						</s:iterator >
					</td>
				</tr>
				<tr>
					<td class="kqbatch-td" style="border:0">
						<span class="kqtd-title">姓名：全选<input type="checkbox" name="allChecked" onclick="DoCheck()" style="margin-left: 20px"/></span>
						<s:iterator value="namelist" status="sta">
							<div class="nameselect">
								<span><s:property value="namelist.get(#sta.index).get(1)" /></span>
								<input type="checkbox" name="selectname" value="<s:property value="namelist.get(#sta.index).get(0)" />"/>
							</div>
						</s:iterator >
					</td>
				</tr>
				<input type="hidden" name="sczByName"  value="<s:property value="sczByName" />">
				<input type="hidden" name="month"  value="<s:property value="month" />">
				<input type="hidden" name="day"  value="<s:property value="day" />">
            </table>
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
        $(this).parent('div').siblings().children('input').iCheck('check');
    });
    $('.checkboxip').on('ifUnchecked', function(event){
        $(this).parent('div').siblings().children('input').iCheck('uncheck');
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
        $(this).parent('div').siblings().children('input').iCheck('check');
    });
    $('.checkboxip1').on('ifUnchecked', function(event){
        $(this).parent('div').parent('.ck').parent('div').children('p').remove('.mdgb');
        $(this).parent('div').siblings().children('input').iCheck('uncheck');
    });


    function DoCheck()
    {
        var ch=document.getElementsByName("selectname");
        if(document.getElementsByName("allChecked")[0].checked==true)
        {
            for(var i=0;i<ch.length;i++)
            {
                ch[i].checked=true;
            }
        }else{
            for(var i=0;i<ch.length;i++)
            {
                ch[i].checked=false;
            }
        }
    }


	$(function(){
		var date = new Date();
		var day = date.getDate();
		var days = 'kqb.day'+day;
		$('.'+days).val('');
	});


    $("#baocun").click(function(){
		/*此处的保存操作加入了两张表*/
        $.ajax({
            type:"POST",
            url:"batchAddKqbExport.action",
            data:$('#formjob').serialize(),
            dataType:"json",
            success:function(data){
                $(this).dialog('closeCurrent',true);
//                $("#queryRS").click();

            }
        })
    });
</script>