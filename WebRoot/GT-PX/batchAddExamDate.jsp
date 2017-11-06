<%--
  Created by IntelliJ IDEA.
  User: zdzmac
  Date: 2017/11/6
  Time: 上午12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
         contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<style type="text/css">
    .baed-label{
        margin-top: 30px;
    }
</style>

<div class="bjui-pageContent tableContent">
    <form action="importPxInfo.action" method="post" id="myfrom" enctype="multipart/form-data">
        <table class="table table-condensed table-hover" width="100%">
            <tbody>
            <label class='control-label x85' class="baed-label">考试日期:</label><input type='text' data-toggle="datepicker" id='examDate'  size='20' data-pattern="yyyy-MM-dd HH:mm:ss"   value="" />
            </tbody>
        </table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
        <li><button type="button" class="btn-default" data-icon="save"  onclick="batchAddExamDate()">确定</button></li>
    </ul>
</div>

<script type="text/javascript">
    function batchAddExamDate(){
        var uuidarr =takeuuid();
        var examdate = $('#examDate').val();
        var data = {
            examdate:examdate,
            uuidarr:uuidarr
        }
        $.ajax({
            type: "POST",
            url: "batchAddExamDate.action",
            data: data,
            dataType: "json",
            success: function(data){
                if(data.statusCode==200){
                    $(this).dialog('closeCurrent',true);

                    $("#queryPX").click();
                }else{
                    $(this).alertmsg('error', '操作失败');
                }
            }
        });
    }

    function takeuuid(){ //jquery获取复选框值
        var uuidArr =[];
        $('input[name="delUuid"]:checked').each(function(){
            uuidArr.push($(this).val());
        });
        return uuidArr;
    }

</script>
