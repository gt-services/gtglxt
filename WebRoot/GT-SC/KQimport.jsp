<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageHeader">
    <form action="importKqb.action" method="post" id="myfrom" enctype="multipart/form-data">
        <table class="table table-condensed table-hover" width="100%">
            <tbody>
                <tr><td><label class='control-label x85'>模板下载：</label><a href="excel/gt_kaoqing_demo.xls">点击下载表格模板</a></td></tr>
                <tr><td><label class='control-label x85'>上传文件：</label>&nbsp;&nbsp;<input type="file" class="btn btn black" id="excelPath" name="excelPath"/></td></tr>
            </tbody>
        </table>
    </form>
</div>


<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr id="theadTr">
        </tr>
        </thead>
        <tbody id="tbodyTr">
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
    <ul id="submitExcel">
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <li><button type="button" class="btn-default" data-icon="save"  onclick="importExcel()">导入</button></li>
    </ul>
</div>

<script type="text/javascript">

    exportdata = {};

    $(function () {
        var trobj = '<th>NO.</th><th>姓名</th><th>银行卡</th> <th>生产组</th> <th>岗位</th> <th>年份</th> <th>月份</th>';
        for(i=1;i<=31;i++){
            trobj =  trobj + '<th>' + i + '</th>'
        }
        trobj = trobj + '<th>健康证报销</th><th>水电费扣款</th> <th>叉车证扣款</th> <th>叉车证还款</th> <th>员工还款</th> <th>员工借款</th>'
        +'th>住宿扣款</th><th>工作服和鞋扣款</th> <th>工作服和鞋还款</th> <th>餐补</th> <th>其他扣款</th> <th>备注</th> <th>数据更新时间</th>';
        $('#theadTr').append(trobj);
    })



    function allPrpos(obj,index) {
        // 用来保存所有的属性名称和值
        var props = '<td>' + index  + '</td>';
        for(var p in obj){
            if(p!='jid' && p!='uuid' && p!='sid'){
                props = props + '<td>' + obj[p]  + '</td>';
            }
        }
        props = '<tr>' + props + '</tr>';
        return props;
    }


  	function importExcel(){  
	    //检验导入的文件是否为Excel文件  
	    var excelPath = document.getElementById("excelPath").value;  
	    if(excelPath == null || excelPath == ''){  
	    	$(this).alertmsg('error', "请选择要上传的Excel文件", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
	        return false;  
	    }else{  
	        var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();   
	        if(fileExtend == '.xls'){
	        	 $.ajaxFileUpload({    
	                 url:'importKqb.action', //url自己写   
	                 secureuri:false, //是否需要安全协议，一般设置为false
	                 type:'post',  
	                 fileElementId:'excelPath',//file标签的id    
	                 dataType: 'JSON',//返回数据的类型
	                 async:false,
	                 success: function(data,status) {
	                     var obj = data;
                         obj = JSON.parse(obj).data;
                         exportdata = obj;
	                     var trobj = "";
	                     for(i=0;i<=obj.length;i++){
	                         trobj = trobj + allPrpos(obj[i],i+1);
                         }
//
                         $('#tbodyTr').append(trobj);
                         $('#submitExcel').append('<li><button type="button" class="btn btn-green" data-icon="save" onclick="submitExcel()">上传</button></li>');
	                	 $(this).alertmsg('ok', "导入成功！", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'});
		             	    
//	             	    $(this).dialog('closeCurrent',true);
//	             	    setTimeout(function(){
//	             	    	 $("#queryRS").click();
//	                    },500);
	                 } ,
	                 error:function(data,status){
	                	 $(this).alertmsg('error', "导入失败！", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
	                 }
	             	});
	        }else{  
	        	$(this).alertmsg('error', "文件格式需为'.xls'格式", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
	            return false;  
	        }  
	    } 
    }


    function submitExcel(){
            //检验导入的文件是否为Excel文件
            var excelPath = document.getElementById("excelPath").value;
            if(excelPath == null || excelPath == ''){
                $(this).alertmsg('error', "请选择要上传的Excel文件", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
                return false;
            }else{
                var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
                if(fileExtend == '.xls'){
                    $.ajaxFileUpload({
                        url:'importKqbNew.action', //url自己写
                        secureuri:false, //是否需要安全协议，一般设置为false
                        type:'post',
                        fileElementId:'excelPath',//file标签的id
                        dataType: 'JSON',//返回数据的类型
                        async:false,
                        success: function(data,status) {
                            $(this).alertmsg('ok', "上传成功！", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'});

                            $(this).dialog('closeCurrent',true);
                            setTimeout(function(){
                                $("#queryRS").click();
                            },500);
                        } ,
                        error:function(data,status){
                            $(this).alertmsg('error', "上传失败！", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
                        }
                    });
                }else{
                    $(this).alertmsg('error', "文件格式需为'.xls'格式", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'})
                    return false;
                }
            }

    }

  </script>