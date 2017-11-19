<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getKqbList.action" method="post">
		<input type="hidden" id="adstatus"  value="<s:property value="status" />">
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
                    <option value="<s:property value="11" />" <s:if test="month==11">selected = "selected"</s:if>>11</option>
                    <option value="<s:property value="12" />" <s:if test="month==12">selected = "selected"</s:if>>12</option>
            </select>
             <label>日：</label>
            <select data-toggle="selectpicker" id="day"  name='day'  data-width="40" data-heigth="200" >
            		
                    <option value="<s:property value="1" />" <s:if test="day==1">selected = "selected"</s:if>>1</option>
                    <option value="<s:property value="2" />" <s:if test="day==2">selected = "selected"</s:if>>2</option>
                    <option value="<s:property value="3" />" <s:if test="day==3">selected = "selected"</s:if>>3</option>
                    <option value="<s:property value="4" />" <s:if test="day==4">selected = "selected"</s:if>>4</option>
                    <option value="<s:property value="5" />" <s:if test="day==5">selected = "selected"</s:if>>5</option>
                    <option value="<s:property value="6" />" <s:if test="day==6">selected = "selected"</s:if>>6</option>
                    <option value="<s:property value="7" />" <s:if test="day==7">selected = "selected"</s:if>>7</option>
                    <option value="<s:property value="8" />" <s:if test="day==8">selected = "selected"</s:if>>8</option>
                    <option value="<s:property value="9" />" <s:if test="day==9">selected = "selected"</s:if>>9</option>
                    <option value="<s:property value="10" />" <s:if test="day==10">selected = "selected"</s:if>>10</option>
                    <option value="<s:property value="11" />" <s:if test="day==11">selected = "selected"</s:if>>11</option>
                    <option value="<s:property value="12" />" <s:if test="day==12">selected = "selected"</s:if>>12</option>
                    <option value="<s:property value="13" />" <s:if test="day==13">selected = "selected"</s:if>>13</option>
                    <option value="<s:property value="14" />" <s:if test="day==14">selected = "selected"</s:if>>14</option>
                    <option value="<s:property value="15" />" <s:if test="day==15">selected = "selected"</s:if>>15</option>
                    <option value="<s:property value="16" />" <s:if test="day==16">selected = "selected"</s:if>>16</option>
                    <option value="<s:property value="17" />" <s:if test="day==17">selected = "selected"</s:if>>17</option>
                    <option value="<s:property value="18" />" <s:if test="day==18">selected = "selected"</s:if>>18</option>
                    <option value="<s:property value="19" />" <s:if test="day==19">selected = "selected"</s:if>>19</option>
                    <option value="<s:property value="20" />" <s:if test="day==20">selected = "selected"</s:if>>20</option>
                    <option value="<s:property value="21" />" <s:if test="day==21">selected = "selected"</s:if>>21</option>
                    <option value="<s:property value="22" />" <s:if test="day==22">selected = "selected"</s:if>>22</option>
                    <option value="<s:property value="23" />" <s:if test="day==23">selected = "selected"</s:if>>23</option>
                    <option value="<s:property value="24" />" <s:if test="day==24">selected = "selected"</s:if>>24</option>
                    <option value="<s:property value="25" />" <s:if test="day==25">selected = "selected"</s:if>>25</option>
                    <option value="<s:property value="26" />" <s:if test="day==26">selected = "selected"</s:if>>26</option>
                    <option value="<s:property value="27" />" <s:if test="day==27">selected = "selected"</s:if>>27</option>
                    <option value="<s:property value="28" />" <s:if test="day==28">selected = "selected"</s:if>>28</option>
                    <option value="<s:property value="29" />" <s:if test="day==29">selected = "selected"</s:if>>29</option>
                    <option value="<s:property value="30" />" <s:if test="day==30">selected = "selected"</s:if>>30</option>
                    <option value="<s:property value="31" />" <s:if test="day==31">selected = "selected"</s:if>>31</option>
                   
                   
            </select>
            <button type="submit"  class="btn-default" data-icon="search" id="queryRS">查询</button>
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo">清空查询</a>
            <span style="float:right;margin-right:5px;"><input type="button" class="btn btn-blue" onclick="doKqbExport()" value="新表导出" /></span>
            <span style="float:right;margin-right:5px;"><a href="GT-SC/KQimport.jsp"  class="btn btn-blue" data-toggle="dialog" data-width="300" data-height="300" data-icon="arrow-up">导入</a></span>
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
            <th>岗位</th>
          	<th>当日考情信息</th>            
            <th>考勤添加</th>
            <th>本月考勤信息</th>
            <th>本月扣款信息</th>
            
        </tr>
        </thead>
       <tbody>
       		<s:if test="listss.size()==0"><tr><td colspan="14">暂无信息</td></tr></s:if>
	    	<s:else>
       		<s:iterator value="listss" var="stu1" status="sta">
       		
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="scz" /></td>
            	
            	<td><s:property value="gw" /></td>
            	
            	<td><s:property value="todaykq"/></td>
				
         
            	
            	<td><a href='javascript:void(0)' onclick="kq(2,'<s:property value="uuid"/>','<s:property value="sczid" />','<s:property value="scz" />')" id="todaykq">考勤添加</a></td>
            	<td><a href='javascript:void(0)' onclick="view(2,'<s:property value="uuid"/>','<s:property value="sczid" />','<s:property value="scz" />')"  >本月考勤信息</a>
            	<%-- <td><a href="viewkqb.action?type=2&uuid=<s:property value="uuid"/>&scz=<s:property value="scz" />&sczid=<s:property value="sczid" />" class="btn-green" data-toggle="dialog" data-width="900" data-height="600" data-id="dialog-mask" >本月考勤信息</a> --%>
            	<td><a href="viewkqb.action?type=1&uuid=<s:property value="uuid"/>&scz=<s:property value="scz" />&sczid=<s:property value="sczid" />" class="btn-green" data-toggle="dialog" data-width="900" data-height="600" data-id="dialog-mask" >本月扣款</a>
            	</td>
            	
            </tr>
            </s:iterator >
            </s:else>
        </tbody>
    </table>
</div>
<div class="bjui-pageFooter">
    <div class="pages">
        <span>共 <s:property value="page.totalCount" />条  每页  30 条</span>
    </div>
    <div class="pagination-box" data-toggle="pagination" data-total="<s:property value="page.totalCount" />" data-page-size="30" data-page-current="<s:property value="page.currentPage" />">
    </div>
</div>

<script type="text/javascript">
$(function(){
	var date = new Date();
	var daynow = date.getDate();
	var monthnow = date.getMonth()+1;
	var monthoptions=$("#month option:selected");
	var month = monthoptions.val();
	var dayoptions=$("#day option:selected");
	var day = dayoptions.val();
	var status = $("#adstatus").val();
	console.log($("#adstatus").val());
	if(status==0){
		if(daynow!=day || monthnow!=month){
			$('#todaykq').removeAttr('onclick');
		}	
	}

	
});

function view(type,uuid,sczid,scz){
	var monthoptions=$("#month option:selected");
	var a = monthoptions.val();
	$(this).dialog({id:'mydialog1', url:'viewkqb.action?type=2&month='+a+'&uuid='+uuid+'&sczid='+sczid+'&scz='+scz, title:'本月考勤统计',width:800,height:500});
}

function kq(type,uuid,sczidd){
	var monthoptions=$("#month option:selected");
	var month = monthoptions.val();
	var dayoptions=$("#day option:selected");
	var day = dayoptions.val();
  
    $(this).dialog({id:'mydialog2', url:'editkqb.action?type=2&uuid='+uuid+'&sczidd='+sczidd+'&month='+month+'&day='+day, title:'考勤添加',width:900,height:500});


}

function doExport(){
	var scz=$("#scz").val();
	var year=$("#year").val();
	var month=$("#month").val();
	$(this).alertmsg('confirm', '确定要导出吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'确定', cancelName:'取消', title:'提示信息',okCall:function(){
	  location.href='exportKqb.action?year='+year+'&month='+month+'&scz='+scz;
}});
}

function doExportDetail(){
    var month=$("#month").val();
    var scz=$("#scz").find("option:selected").text();
    $(this).alertmsg('confirm', '确定要导出吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'确定', cancelName:'取消', title:'提示信息',okCall:function(){
        location.href='exportKqbDetail.action?month='+month+'&scz='+scz;
    }});
}

function doKqbExport(){
    var scz=$("#scz").val();
    var year=$("#year").val();
    var month=$("#month").val();
    $(this).alertmsg('confirm', '确定要导出吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'确定', cancelName:'取消', title:'提示信息',okCall:function(){
        location.href='exportKqbExport.action?year='+year+'&month='+month+'&scz='+scz;
    }});
}
</script>
