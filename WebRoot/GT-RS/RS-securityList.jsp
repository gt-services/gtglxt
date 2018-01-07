<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getSecuirtyList.action" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
            <label>关键词：</label><input type="text" id="keyword" value="<s:property value="keyword" />" name="keyword" placeholder="请输入查询姓名" class="form-control" size="12" />
            <button type="submit"  class="btn-default" data-icon="search" id="queryRS">查询</button>
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo">清空查询</a>
            <span style="float:right;margin-right:5px;"><a href="exportRoster.action" class="btn btn-blue" data-confirm-msg="确定要导出吗？" data-icon="arrow-down">导出</a></span>
            <span style="float:right;margin-right:5px;"><a href="RSimport.jsp" class="btn btn-blue" data-toggle="dialog" data-width="300" data-height="300" data-icon="arrow-up">导入</a></span>
            <%--  <span style="float:right;margin-right:5px;"><a href="RSadd.jsp" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" data-mask="true" data-icon="plus">新增</a></span> --%>
        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr>
            <th width="30">NO.</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>身份证</th>
            <th>个人编号</th>
            <th>家庭地址</th>
            <th>联系电话</th>
            <th>合同签订日期</th>
            <th>合同截止日期</th>
            <th>介绍人</th>
            <th>介绍人联系方式</th>
            <th>缴费项目</th>
            <th>公积金客户号</th>
            <th>停保时间</th>
            <th>户籍</th>
            <th >备注</th>
            <th >操作</th>
        </tr>
        </thead>
       <tbody>
       		<s:if test="securityList.size()==0"><tr><td colspan="14">暂无信息</td></tr></s:if>
	    	<s:else>
       		<s:iterator value="securityList" status="sta">
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:property value="htNumber" /></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="sex" /></td>
            	<td><s:property value="identityId" /></td>
            	<td><s:property value="sbNumber" /></td>
            	<td><s:date name="htStart" format="yyyy-MM-dd"/></td>
            	<td><s:date name="htEnd" format="yyyy-MM-dd"/></td>
            	<td><s:date name="lcTime" format="yyyy-MM-dd"/></td>
            	<td><s:date name="sbStart" format="yyyy-MM-dd"/></td>
            	<td><s:date name="sbEnd" format="yyyy-MM-dd"/></td>
            	<td><s:property value="gsqk" /></td>
            	<td><a href="editroster.action?type=1&uuid=<s:property value="uuid"/>" class="btn-blue" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" >详细</a></td>
            	<td><a href="editroster.action?uuid=<s:property value="uuid"/>" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" >编辑</a>&nbsp;&nbsp;
            	<a class="btn btn-red" id="delete<s:property value="uuid" />" onclick="deleteRS('<s:property value="uuid" />')">删除</a></td>
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
	function deleteRS(uuid){
		$(this).alertmsg('confirm', '确认删除该条数据？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
			$.ajax({
             type: "POST",
             url: "delroster.action",
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
