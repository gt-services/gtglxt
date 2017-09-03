<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent tableContent">
    <form action="importPxInfo.action" method="post" id="myfrom" enctype="multipart/form-data">
        <table class="table table-condensed table-hover" width="100%">
            <tbody>
                <tr><td><label class='control-label x85'>模板下载：</label><a href="excel/gt_peixun_demo.xls">点击下载表格模板</a></td></tr>
                <tr><td><label class='control-label x85'>上传文件：</label>&nbsp;&nbsp;<input type="file" class="btn btn black" id="excelPath" name="excelPath"/></td></tr>
            </tbody>
        </table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <li><button type="button" class="btn-default" data-icon="save"  onclick="importExcel()">导入</button></li>
    </ul>
</div>

<script type="text/javascript">
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
	                 url:'importPxInfo.action', //url自己写   
	                 secureuri:false, //是否需要安全协议，一般设置为false
	                 type:'post',  
	                 fileElementId:'excelPath',//file标签的id    
	                 dataType: 'JSON',//返回数据的类型
	                 async:false,
	                 success: function(data,status) {
	             	    setTimeout(function(){
	             	    	 $("#queryPX").click();
	                    },500); 
	                	$(this).alertmsg('ok', "导入成功！", {displayMode:'slide', displayPosition:'topcenter', title:'提示信息'});
		             	    
	             	    $(this).dialog('closeCurrent',true);
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
  </script>