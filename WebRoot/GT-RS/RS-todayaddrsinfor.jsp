<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getTodayAddRosterList.action" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
            <label>关键词：</label><input type="text" id="keyword" value="<s:property value="keyword" />" name="keyword" placeholder="请输入查询姓名" class="form-control" size="12" />
           	<label>生产组：</label>
            <select data-toggle="selectpicker" id="scz" name='scz'  data-width="80">
            		<option value="">请选择</option>
                    <s:iterator value="lists" status="sta">
                    	<option value="<s:property value="name" />" <s:if test="scz==secondId">selected = "selected"</s:if>><s:property value="name" /></option>
                    </s:iterator>
            </select>
            <button type="submit"  class="btn-default" data-icon="search" id="queryRS">查询</button>
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo" id="clearQuery">清空查询</a>
            <span style="float:right;margin-right:5px;"><input type="button" class="btn btn-blue" onclick="doExport()" value="导出" /></span>
        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr>
            <th width="30">NO.</th>
            <th>合同编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>身份证</th>
            <th>银行卡号</th>
            <th>社保编号</th>
            <th>合同开始日期</th>
            <th>合同结束日期</th>
            <th>离厂日期</th>
            <th>社保开始日期</th>
            <th>社保结束日期</th>
            <th>工伤概况</th>
            <th>招聘来源</th>
            <th>所在生产组</th>
            <th >详细</th>
            <th>处理情况</th>
            
        </tr>
        </thead>
       <tbody>
       		<s:if test="list.size()==0"><tr><td colspan="17">暂无信息</td></tr></s:if>
	    	<s:else>
       		<s:iterator value="list" status="sta">
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:property value="htNumber" /></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="sex" /></td>
            	<td><s:property value="identityId" /></td>
            	<td><s:property value="bankCard" /></td>
            	<td><s:property value="sbNumber" /></td>
            	<td><s:date name="htStart" format="yyyy-MM-dd"/></td>
            	<td><s:date name="htEnd" format="yyyy-MM-dd"/></td>
            	<td><s:date name="lcTime" format="yyyy-MM-dd"/></td>
            	<td><s:date name="sbStart" format="yyyy-MM-dd"/></td>
            	<td><s:date name="sbEnd" format="yyyy-MM-dd"/></td>
            	<td><s:property value="gsqk" /></td>
            	<td><s:property value="source" /></td>
            	<td><s:property value="scz" /></td>
            	<td><a href="editroster.action?type=1&uuid=<s:property value="uuid"/>" class="btn-blue" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" >详细</a></td>
            	<td>
                    <s:if test="deal==0">
                        <a class="btn btn-red"  onclick="dealnot('<s:property value="uuid" />')">未处理</a>
                    </s:if>
                    <s:else>
                        <a class="btn btn-green kqardadd" onclick="deal('<s:property value="uuid" />')">已处理</a>
                    </s:else>
                </td>
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
function doExport(){
	var a =$("#scz").val();
	
	$(this).alertmsg('confirm', '确定要导出吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'确定', cancelName:'取消', title:'提示信息',okCall:function(){
	  location.href='exportTodayaddRoster.action?sczn='+a;
}});
}

function deal(uuid){
    $.ajax({
        type: "POST",
        url: "editDeal.action",
        data: {deal:'0',uuid:uuid},
        dataType: "json",
        success: function(data){
            if(data.statusCode==200){
                $("#queryRS").click();
            }else{
                $(this).alertmsg('error', '删除失败');
            }
        }
    });
}

function dealnot(uuid){
    $.ajax({
        type: "POST",
        url: "editDeal.action",
        data: {deal:'1',uuid:uuid},
        dataType: "json",
        success: function(data){
            if(data.statusCode==200){
                $("#queryRS").click();
            }else{
                $(this).alertmsg('error', '删除失败');
            }
        }
    });
}
</script>
