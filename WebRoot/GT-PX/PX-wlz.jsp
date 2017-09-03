<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getPxInfoList.action?pageType=4" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
            <label>关键词：</label><input type="text" id="keyword" value="<s:property value="keyword" />" name="keyword" placeholder="请输入查询姓名" class="form-control" size="12" />
            <button type="submit"  class="btn-default" data-icon="search" id="queryPX">查询</button>
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo">清空查询</a>
            <span style="float:right;margin-right:5px;"><input type="button" class="btn btn-blue" onclick="doExport()" value="导出" /></span>
            <span style="float:right;margin-right:5px;"><a href="/GT-PX/PXimport.jsp" class="btn btn-blue" data-toggle="dialog" data-width="300" data-height="300" data-icon="arrow-up">导入</a></span>
            <span style="float:right;margin-right:5px;"><a href="/GT-PX/PXadd.jsp" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" data-mask="true" data-icon="plus">新增</a></span>
        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
     <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr>    
            <th width="30">NO.</th>
            <th>培训日期</th>
            <th>姓名</th>
            <th>身份证</th>
            <th>联系电话</th>
            <th>单位联系人</th>
            <th>单位电话</th>
            <th>类别</th>
            <th>详细</th>
            <th>操作</th>
        </tr>
        </thead>
       <tbody>
       		<s:if test="list.size()==0"><tr><td colspan="17" rowspan="3"><center><font style="font-size: large;">暂无信息</font></center></td></tr></s:if>
	    	<s:else>
       		<s:iterator value="list" status="sta">
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:date name="signupDate" format="yyyy-MM-dd"/></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="idCard" /></td>
            	<td><s:property value="telephone"/></td>
            	<td><s:property value="contactOfUnit"/></td>
            	<td><s:property value="unitPhone"/></td>
            	<td><s:property value="trainType"/></td>
            	<td><a href="editPxInfo.action?type=1&uuid=<s:property value="uuid"/>" class="btn-blue" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" >详细</a></td>
            	<td><a href="editPxInfo.action?uuid=<s:property value="uuid"/>" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" >编辑</a>&nbsp;&nbsp;
            	<a class="btn btn-red" onclick="deletePX('<s:property value="uuid" />')">删除</a></td>
            </tr>
            </s:iterator >
            </s:else>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
    <div class="pages">
        <span>共 <s:property value="page.totalCount" />条  每页  20 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<s:property value="page.totalCount" />" data-page-size="20" data-page-current="<s:property value="page.currentPage" />">
    </div>
</div>
<script type="text/javascript">
	function deletePX(uuid){
		$(this).alertmsg('confirm', '确认删除该条数据？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
			$.ajax({
             type: "POST",
             url: "delPxInfo.action",
             data: {uuid:uuid},
             dataType: "json",
             success: function(data){
             if(data.statusCode==200){
           		$("#queryPX").click();
             }else{
           		$(this).alertmsg('error', '删除失败');
             }
           }
         });
	   }});
	}
	
		function doExport(){
		$(this).alertmsg('confirm', '确定要导出吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'确定', cancelName:'取消', title:'提示信息',okCall:function(){
			  location.href="exportPxInfo.action?exportType=2";
	   }});
	}

</script>
