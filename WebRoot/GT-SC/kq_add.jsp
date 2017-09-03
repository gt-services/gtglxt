<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getKqbList.action" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
            <label>关键词：</label><input type="text" id="keyword" value="<s:property value="keyword" />" name="keyword" placeholder="请输入查询姓名" class="form-control" size="12" />
            <s:if test="#session.admin.authority.equals(\"1\")">
            <label>生产组：</label>
            <select data-toggle="selectpicker" id="scz" name='scz'  data-width="80">
            		<option value="">请选择</option>
                    <s:iterator value="lists" status="sta">
                    	<option value="<s:property value="secondId" />" <s:if test="scz==secondId">selected = "selected"</s:if>><s:property value="name" /></option>
                    </s:iterator>
            </select>
            </s:if>
            <label>班次：</label>
            <select data-toggle="selectpicker" id="banci" name='banci'  data-width="60">
            		<option value="">请选择</option>
                    <option value="<s:property value="1" />" <s:if test="banci==1">selected = "selected"</s:if>>早班</option>
                    <option value="<s:property value="2" />" <s:if test="banci==2">selected = "selected"</s:if>>中班</option>
                    <option value="<s:property value="3" />" <s:if test="banci==3">selected = "selected"</s:if>>晚班</option>
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
            <span style="float:right;margin-right:5px;"><a onclick="exportKqb();"  class="btn btn-blue" data-icon="arrow-down">导出</a></span>
            <span style="float:right;margin-right:5px;"><a href="KQimport.jsp" class="btn btn-blue" data-toggle="dialog" data-width="300" data-height="300" data-icon="arrow-up">导入</a></span>
            <span style="float:right;margin-right:5px;"><a href="editkqb.action?type=1" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="200" data-id="dialog-mask" data-mask="true" data-icon="plus">新增</a></span>
        </div>
    </form>

</div>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed" data-width="100%" data-layout-h="0" data-nowrap="true">
        <thead>
        <tr>
            <th width="30">NO.</th>
            <th>姓名</th>
            <th>岗位</th>
            <!-- <th>班次</th> -->
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
            <th>5</th>
            <th>6</th>
            <th>7</th>
            <th>8</th>
            <th>9</th>
            <th>10</th>
            <th>11</th>
            <th>12</th>
            <th>13</th>
            <th>14</th>
            <th>15</th>
            <th>16</th>
            <th>17</th>
            <th>18</th>
            <th>19</th>
            <th>20</th>
            <th>21</th>
            <th>22</th>
            <th>23</th>
            <th>24</th>
            <th>25</th>
            <th>26</th>
            <th>27</th>
            <th>28</th>
            <th>29</th>
            <th>30</th>
            <th>31</th>
            <th>备注</th>
            <th>考勤</th>
            <th width="50">操作</th>
            
        </tr>
        </thead>
       <tbody>
       		<s:if test="list.size()==0"><tr><td colspan="14">暂无信息</td></tr></s:if>
	    	<s:else>
       		<s:iterator value="list" status="sta">
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="gw" /></td>
            
            	<td><s:property value="day1"/></td>
            	<td><s:property value="day2"/></td>
            	<td><s:property value="day3"/></td>
            	<td><s:property value="day4"/></td>
            	<td><s:property value="day5"/></td>
            	<td><s:property value="day6"/></td>
            	<td><s:property value="day7"/></td>
            	<td><s:property value="day8"/></td>
            	<td><s:property value="day9"/></td>
            	<td><s:property value="day10"/></td>
            	<td><s:property value="day11"/></td>
            	<td><s:property value="day12"/></td>
            	<td><s:property value="day13"/></td>
            	<td><s:property value="day14"/></td>
            	<td><s:property value="day15"/></td>
            	<td><s:property value="day16"/></td>
            	<td><s:property value="day17"/></td>
            	<td><s:property value="day18"/></td>
            	<td><s:property value="day19"/></td>
            	<td><s:property value="day20"/></td>
            	<td><s:property value="day21"/></td>
            	<td><s:property value="day22"/></td>
            	<td><s:property value="day23"/></td>
            	<td><s:property value="day24"/></td>
            	<td><s:property value="day25"/></td>
            	<td><s:property value="day26"/></td>
            	<td><s:property value="day27"/></td>
            	<td><s:property value="day28"/></td>
            	<td><s:property value="day29"/></td>
            	<td><s:property value="day30"/></td>
            	<td><s:property value="day31"/></td>
				<td><s:property value="remark"/></td>
            	
            	<td><a href="editkqb.action?type=3&uuid=<s:property value="uuid"/>" class="btn btn-blue" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" >考勤</a></td>
            	<td><a href="editkqb.action?type=2&uuid=<s:property value="uuid"/>" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="200" data-id="dialog-mask" >编辑</a>
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
	function exportKqb(){
		var keyword = $('#keyword').val();
		var scz = $('#scz').val();
		var banci = $('#banci').val();
		var year = $('#year').val();
		var month = $('#month').val();
		
		window.location.href="exportKqb.action?keyword="+keyword+"&scz="+scz+"&banci="+banci+"&year="+year+"&month="+month;
		/* $(this).alertmsg('confirm', '确定要导出吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
			$.ajax({
				url: "exportKqb.action",
				cache : false,
			 	async : false, 
            	type: "POST",
             
             data: {keyword:keyword,scz:scz,banci:banci,year:year,month:month},
             dataType: "json",
             success: function(data){
             if(data.statusCode==200){
           		
             }else{
           		$(this).alertmsg('error', '导出失败');
             }
           } 
         }); 
	   }});*/
	} 

</script>
