<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getAdminList.action" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
            <label>关键词：</label><input type="text" id="keyword" value="<s:property value="keyword" />" name="keyword" placeholder="请输入查询姓名" class="form-control" size="12" />
            <button type="submit"  class="btn-default" data-icon="search" id="queryRS">查询</button>
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo">清空查询</a>
            <span style="float:right;margin-right:5px;"><a href="editAdmin.action?uuid=" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="300" data-id="dialog-mask" data-mask="true" data-icon="plus">新增</a></span>
        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr>
            <th width="30">NO.</th>
            <th>用户名</th>
            <th>姓名</th>
            <th>所属部门</th>
            <th>电话号码</th>
            <th>邮箱</th>
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
            	<td><s:property value="username" /></td>
            	<td><s:property value="realname" /></td>
            	<td><s:property value="authority" /></td>
            	<td><s:property value="phone" /></td>
            	<td><s:property value="email" /></td>
            	<td><s:property value="beizhu" /></td>
            	<td><a href="editAdmin.action?uuid=<s:property value="uuid"/>" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="300" data-id="dialog-mask" >编辑</a>&nbsp;&nbsp;
            	<a class="btn btn-red" id="delete<s:property value="uuid" />" onclick="deleteAdmin('<s:property value="uuid" />')">删除</a>
            	<s:if test="status==0">
            		<a class="btn btn-red"  onclick="addinKQ('<s:property value="uuid" />')">不可延时考勤</a>
            		</s:if>
            		<s:else>
            		<a class="btn btn-green kqardadd" onclick="kqardadd('<s:property value="uuid" />')">可延时考勤</a>
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
	function deleteAdmin(uuid){
		$(this).alertmsg('confirm', '确认删除该条数据？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
			$.ajax({
             type: "POST",
             url: "delAdmin.action",
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
	
	function kqardadd(uuid){
		$.ajax({
            type: "POST",
            url: "editAdminqx.action",
            data: {status:'0',uuid:uuid},
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
	
	function addinKQ(uuid){
		$.ajax({
            type: "POST",
            url: "editAdminqx.action",
            data: {status:'1',uuid:uuid},
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
