<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getcw.action" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
            <label>关键词：</label><input type="text" id="keyword" value="<s:property value="keyword" />" name="keyword" placeholder="请输入查询姓名" class="form-control" size="12" />
           
            <label>生产组：</label>
            <select data-toggle="selectpicker" id="scz" name='scz'  data-width="80">
            		<option value="">请选择</option>
                    <s:iterator value="lists" status="sta">
                    	<option value="<s:property value="secondId" />" <s:if test="scz==secondId">selected = "selected"</s:if>><s:property value="name" /></option>
                    </s:iterator>
            </select>

            
            <label>年：</label>
            <select data-toggle="selectpicker" id="year"  name='year'  data-width="60">
            		
                    <option value="<s:property value="2016" />" <s:if test="year==2016">selected = "selected"</s:if>>2016</option>
                    <option value="<s:property value="2017" />" <s:if test="year==2017">selected = "selected"</s:if>>2017</option>
                    <option value="<s:property value="2018" />" <s:if test="year==2018">selected = "selected"</s:if>>2018</option>
                    <option value="<s:property value="2019" />" <s:if test="year==2019">selected = "selected"</s:if>>2019</option>
                    <option value="<s:property value="2020" />" <s:if test="year==2020">selected = "selected"</s:if>>2020</option>
            </select>
            <label>月：</label>
            <select data-toggle="selectpicker" id="month" name='month'  data-width="40">
            		
            		<option value="<s:property value="1" />" <s:if test="month==1">selected = "selected"</s:if>>1</option>
                    <option value="<s:property value="2" />" <s:if test="month==2">selected = "selected"</s:if>>2</option>
                    <option value="<s:property value="3" />" <s:if test="month==3">selected = "selected"</s:if>>3</option>
                    <option value="<s:property value="4" />" <s:if test="month==4">selected = "selected"</s:if>>4</option>
                    <option value="<s:property value="5" />" <s:if test="month==5">selected = "selected"</s:if>>5</option>
                    <option value="<s:property value="6" />" <s:if test="month==6">selected = "selected"</s:if>>6</option>
                    <option value="<s:property value="7" />" <s:if test="month==7">selected = "selected"</s:if>>7</option>
                    <option value="<s:property value="8" />" <s:if test="month==8">selected = "selected"</s:if>>8</option>
                    <option value="<s:property value="9" />" <s:if test="month==9">selected = "selected"</s:if>>9</option>
                    <option value="<s:property value="10" />" <s:if test="month==10">selected = "selected"</s:if>>10</option>
                    <option value="<s:property value="12" />" <s:if test="month==11">selected = "selected"</s:if>>11</option>
                    <option value="<s:property value="12" />" <s:if test="month==12">selected = "selected"</s:if>>12</option>
            </select>
            <button type="submit"  class="btn-default" data-icon="search" id="queryRS">查询</button>
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo">清空查询</a>
            <%-- <span style="float:right;margin-right:5px;"><a onclick="exportKqb();"  class="btn btn-blue" data-icon="arrow-down">导出</a></span>
            <span style="float:right;margin-right:5px;"><a href="KQimport.jsp" class="btn btn-blue" data-toggle="dialog" data-width="300" data-height="300" data-icon="arrow-up">导入</a></span> --%>
            <span style="float:right;margin-right:5px;"><a href="editcw.action" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="350" data-id="dialog-mask" data-mask="true" data-icon="plus">新增</a></span>
        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr>
            <th width="30">NO.</th>
            <th>姓名</th>
            <th>银行卡号</th>
            <th>金额</th>
            <th>发放情况</th>
            <th>备注</th>
            
            <th width="100">操作</th>
        </tr>
        </thead>
       <tbody>
       		<s:if test="list.size()==0"><tr><td colspan="14">暂无信息</td></tr></s:if>
	    	<s:else>
       		<s:iterator value="list" status="sta">
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="bankcard" /></td>
            	<td><s:property value="salary"/></td>
            	<td>
            	<s:if test="status ==1 ">已发放</s:if>
            	<s:else>未发放</s:else>
            	</td>
            	
            	<td><s:property value="beizhu"/></td>
                       	
            	<td><a href="editcw.action?uuid=<s:property value="uuid"/>" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="350" data-id="dialog-mask" >编辑</a>&nbsp;&nbsp;
            	<a class="btn btn-red" id="delete<s:property value="uuid" />" onclick="deleteCw('<s:property value="uuid" />')">删除</a>
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
function deleteCw(uuid){
	$(this).alertmsg('confirm', '确认删除该条数据？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
		$.ajax({
         type: "POST",
         url: "delcw.action",
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

	function exportKqb(){
		var keyword = $('#keyword').val();
		var scz = $('#scz').val();
		var year = $('#year').val();
		var month = $('#month').val();
		
		window.location.href="exportKqb.action?keyword="+keyword+"&scz="+scz+"&year="+year+"&month="+month;
	}

</script>
