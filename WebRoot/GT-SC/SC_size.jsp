<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getSize.action" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
        <span style="float:right;margin-right:5px;"><a href="addSizeopen.action" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="250" data-id="dialog-mask" data-mask="true" data-icon="plus">新增</a></span>
    </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr>
            <th width="30">NO.</th>
            <th>规格名称</th>
            <th>备注</th>
            <th >操作</th>
        </tr>
        </thead>
       <tbody>
       		<s:if test="list.size()==0"><tr><td colspan="14">暂无信息</td></tr></s:if>
	    	<s:else>
       		<s:iterator value="list" status="sta">
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="beizhu" /></td>
            	<td><a href="editSize.action?uuid=<s:property value="uuid"/>&scz=<s:property value="scz"/>" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="250" data-id="dialog-mask" >编辑</a>&nbsp;&nbsp;
            	<a class="btn btn-red" id="delete<s:property value="uuid" />" onclick="deleteSize('<s:property value="uuid" />')">删除</a></td>
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
	function deleteSize(uuid){
		$(this).alertmsg('confirm', '确认删除该条数据？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
			$.ajax({
             type: "POST",
             url: "delSize.action",
             data: {uuid:uuid},
             dataType: "json",
             success: function(data){
             if(data.statusCode==200){
           		$("#queryRS").click();
             }else{
           		$(this).alertmsg('error', '删除失败');
             }
           }
         });
	   }});
	}

</script>
