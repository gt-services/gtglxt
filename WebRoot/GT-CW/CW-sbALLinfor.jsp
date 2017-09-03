<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="sbdetail.action" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
            <label>关键词：</label><input type="text" id="keyword" value="<s:property value="keyword" />" name="keyword" placeholder="请输入查询姓名" class="form-control" size="12" />
            <button type="submit"  class="btn-default" data-icon="search" id="queryRS">查询</button>
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo" id="clearQuery">清空查询</a>
            <span style="float:right;margin-right:5px;"><a href="exportRoster.action" class="btn btn-blue" data-confirm-msg="确定要导出吗？" data-icon="arrow-down">导出</a></span>
            <span style="float:right;margin-right:5px;"><a href="/GT-RS/RSimport.jsp" class="btn btn-blue" data-toggle="dialog" data-width="300" data-height="300" data-icon="arrow-up">导入</a></span>
         
        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr>
            <th width="30">NO.</th>
            
            <th>姓名</th>
           	<th>生产组</th>
            <th>身份证</th>
       
       		
            <th>社保详情</th>
          <!--   <th>修改时间</th> -->
        </tr>
        </thead>
       <tbody>
       		<s:if test="listss.size()==0"><tr><td colspan="14">暂无信息</td></tr></s:if>
	    	<s:else>
       		<s:iterator value="listss" status="sta">
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="scz" /></td>
            	<td><s:property value="identityId" /></td>
            	<td><s:property value="sbinfo" /></td>
            	<%-- <td><s:property value="creatDate" /></td> --%>
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

