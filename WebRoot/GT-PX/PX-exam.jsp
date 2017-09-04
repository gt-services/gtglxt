<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="getPxInfoList.action?pageType=5" method="post">

        <input type="hidden" name="pageSize" value="20">
        <input type="hidden" name="currentPage" id="pageCurrent" value="<s:property value="page.currentPage" />">

        <div class="bjui-searchBar">
            <label>关键词：</label><input type="text" id="keyword" value="<s:property value="keyword" />" name="keyword" placeholder="请输入查询姓名" class="form-control" size="12" />
            <label>培训科目：</label><input type="text" id="trainType" value="<s:property value="trainType" />" name="trainType" placeholder="请输入培训科目" class="form-control" size="12" />
             <label>年：</label>
            <select data-toggle="selectpicker" id="year"  name='year'  data-width="60">
            		
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
            
            <button type="submit"  class="btn-default" data-icon="search" id="queryPX">查询</button>
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true);" data-icon="undo" id="clearQuery">清空查询</a>
            <span style="float:right;margin-right:5px;"><input type="button" class="btn btn-green" onclick="batchpassExam()" value="考试通过" /></span>
            <span style="float:right;margin-right:5px;"><input type="button" class="btn btn-red" onclick="batchaddReExam()" value="加入补考" /></span>
            <span style="float:right;margin-right:5px;"><input type="button" class="btn btn-blue" onclick="doExport()" value="导出" /></span>
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
            <th>性别</th>
            <!-- <th>身份证</th> -->
            <th>文化程度</th>
            <th>单位/个人</th>
            <th>联系电话</th>
            <!-- <th>单位联系人</th>
            <th>单位电话</th> -->
            <th>培训点</th>
            <th>考试日期</th>
            <!-- <th>考试情况</th> -->
            <th>标准金额</th>
            <th>优惠金额</th>
            <th>详细</th>
            <th>操作</th>
            <th>考试情况</th>
        </tr>
        </thead>
       <tbody>
       		<s:if test="list.size()==0"><tr><td colspan="17" rowspan="3"><center><font style="font-size: large;">暂无信息</font></center></td></tr></s:if>
	    	<s:else>
       		<s:iterator value="list" status="sta">
            <tr>
            	<td><s:property value="#sta.index+1" /></td>
            	<td><s:property value="signupDate" /></td>
            	<td><s:property value="name" /></td>
            	<td><s:property value="sex" /></td>
            	<%-- <td><s:property value="idCard" /></td> --%>
            	<td><s:property value="levelEdu" /></td>
            	<td><s:property value="unitOrPerson"/></td>
            	<td><s:property value="telephone"/></td>
            	<%-- <td><s:property value="contactOfUnit"/></td>
            	<td><s:property value="unitPhone"/></td> --%>
            	<td><s:property value="trainPlace"/></td>
            	<td><s:date name="testDate" format="yyyy-MM-dd"/></td>
            	<%-- <td><s:property value="test"/></td> --%>
            	<td><s:property value="standardAmount"/></td>
            	<td><s:property value="discountAmount"/></td>
            	<td><a href="editPxInfo.action?type=1&uuid=<s:property value="uuid"/>" class="btn-blue" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" >详细</a></td>
                <td><a href="editPxInfo.action?uuid=<s:property value="uuid"/>" class="btn btn-green" data-toggle="dialog" data-width="900" data-height="500" data-id="dialog-mask" >编辑</a>&nbsp;
                </td>
                <td>
                    <a class="btn btn-red" id="delete<s:property value="uuid" />" onclick="addReExam('<s:property value="uuid" />')">加入补考</a>
                    <a class="btn btn-green" id="delete<s:property value="uuid" />" onclick="passExam('<s:property value="uuid" />')">考试通过</a>
                    <input type="checkbox" style="width: 15px;height: 15px" name="delUuid" value="<s:property value="uuid" />"/>
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
	function deletePX(uuid){
		$(this).alertmsg('confirm', '确认删除该条数据？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
			$.ajax({
             type: "POST",
             url: "delPxInfo.action",
             data: {uuid:uuid},
             dataType: "json",
             success: function(data){
                 $("#queryPX").click();
           }
         });
	   }});
	}
	
		function doExport(){
			var a =$("#trainType").val();
			var b =$("#year").val();
			var c =$("#month").val();
		$(this).alertmsg('confirm', '确定要导出吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'确定', cancelName:'取消', title:'提示信息',okCall:function(){
			  location.href='exportPxInfo.action?exptrainType='+a+'&year='+b+'&month='+c;
	   }});
	}


    function batchdeletePX(){
        var uuidarrs = takeuuid();
        //debugger;

        if(uuidarrs.length > 0){
            $(this).alertmsg('confirm', '确认删除这些数据？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
                $.ajax({
                    type: "POST",
                    url: "changeStatus.action",
                    data: {uuidarr:uuidarrs},
                    dataType: "json",
                    success: function(data){
                        if(data.statusCode==200){
                            $("#clearQuery").click();
                        }else{
                            $(this).alertmsg('error', '删除失败');
                        }
                    }
                });
            }});
        }else{
            $(this).alertmsg('confirm', '请至少选择一条数据', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息'});
        }
    }




    function addReExam(uuid){
        var uuidarr = [];
        uuidarr.push(uuid);
        $(this).alertmsg('confirm', '确认将此人加入补考？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
            $.ajax({
                type: "POST",
                url: "changeStatus.action",
                data: {uuidarr:uuidarr,status:'2'},
                dataType: "json",
                success: function(data){
                    $("#queryPX").click();
                }
            });
        }});
    }
    function batchaddReExam(){
        var uuidarrs = takeuuid();
        //debugger;

        if(uuidarrs.length > 0){
            $(this).alertmsg('confirm', '确认将这些人加入补考？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
                $.ajax({
                    type: "POST",
                    url: "changeStatus.action",
                    data: {uuidarr:uuidarrs,status:'2'},
                    dataType: "json",
                    success: function(data){
                        if(data.statusCode==200){
                            $("#clearQuery").click();
                        }else{
                            $(this).alertmsg('error', '删除失败');
                        }
                    }
                });
            }});
        }else{
            $(this).alertmsg('confirm', '请至少选择一条数据', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息'});
        }
    }


    function passExam(uuid){
        var uuidarr = [];
        uuidarr.push(uuid);
        $(this).alertmsg('confirm', '确认此人考试通过？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
            $.ajax({
                type: "POST",
                url: "changeStatus.action",
                data: {uuidarr:uuidarr,status:'1'},
                dataType: "json",
                success: function(data){
                    $("#queryPX").click();
                }
            });
        }});
    }
    function batchpassExam(){
        var uuidarrs = takeuuid();
        //debugger;

        if(uuidarrs.length > 0){
            $(this).alertmsg('confirm', '确认这些人考试通过？', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息',okCall:function(){
                $.ajax({
                    type: "POST",
                    url: "changeStatus.action",
                    data: {uuidarr:uuidarrs,status:'1'},
                    dataType: "json",
                    success: function(data){
                        if(data.statusCode==200){
                            $("#clearQuery").click();
                        }else{
                            $(this).alertmsg('error', '删除失败');
                        }
                    }
                });
            }});
        }else{
            $(this).alertmsg('confirm', '请至少选择一条数据', {displayMode:'slide', displayPosition:'topcenter', okName:'Yes', cancelName:'no', title:'提示信息'});
        }
    }



    function takeuuid(){ //jquery获取复选框值
        var uuidArr =[];
        $('input[name="delUuid"]:checked').each(function(){
            uuidArr.push($(this).val());
        });
        return uuidArr;
    }


</script>
