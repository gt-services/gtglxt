<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">


.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control{
	round:#ffffff;
}

#viewkqform span{
	display:inline-block;
	width:5%;
	font-weight:bold;
	text-align:center
}


#viewkqform input{
    display:inline-block;
    margin-top: 5px;
    width:14%;
    border:1px solid #4A8CDB;
}

</style>
<div class="bjui-pageContent tableContent">
    <form class="pageForm" id="viewkqform" data-toggle="validate">
       	<input type="" style="display: none" id="month" value="<s:property value="ViewKqMonth"/>" readonly="readonly">
        <input type="" style="display: none" id="uuid" value="<s:property value="ViewKqUuid"/>" readonly="readonly">
    </form>
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>


<script>
    $(function () {
        var month = $('#month').val();
        var uuid = $('#uuid').val();
        var data = {
            month:month,
            uuid:uuid
        }
        $.ajax({
            type: "POST",
            url: "viewKqbInfo.action",
            data: data,
            dataType: "json",
            success: function(data){
                if(data.statusCode==200){
                    var list = data.data;
                    var htmlStr = '';
                    htmlStr += '<span>姓名：</span><input value="'+ list[0].name +'"readonly="readonly">';
                    htmlStr += '<span>生产组：</span><input value="'+ list[0].scz +'"readonly="readonly">';
                    htmlStr += '<span>年份：</span><input value="'+ list[0].year +'"readonly="readonly">';
                    htmlStr += '<span>月份：</span><input value="'+ list[0].month +'"readonly="readonly">';
                    for(var i = 0;i<list.length;i++){
                        htmlStr += '<br/><span>工种：</span><input value="'+ list[i].jobOrSizeName +'"readonly="readonly"><br/>';
                        if(list[i].day1){
                            htmlStr += '<span>' + 1 +'日'+'：</span><input value="'+ list[i].day1 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 1 +'日'+'：</span><input value="" readonly="readonly">';
                        }
                        if(list[i].day2){
                            htmlStr += '<span>' + 2 +'日'+'：</span><input value="'+ list[i].day2 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 2 +'日'+'：</span><input value="" readonly="readonly">';
                        }
                        if(list[i].day3){
                            htmlStr += '<span>' + 3 +'日'+'：</span><input value="'+ list[i].day3 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 3 +'日'+'：</span><input value="" readonly="readonly">';
                        }
                        if(list[i].day4){
                            htmlStr += '<span>' + 4 +'日'+'：</span><input value="'+ list[i].day4 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 4 +'日'+'：</span><input value="" readonly="readonly">';
                        }
                        if(list[i].day5){
                            htmlStr += '<span>' + 5 +'日'+'：</span><input value="'+ list[i].day5 +'"readonly="readonly"><br/>';
                        }else{
                            htmlStr += '<span>' + 5 +'日'+'：</span><input value="" readonly="readonly"><br/>';
                        }if(list[i].day6){
                            htmlStr += '<span>' + 6 +'日'+'：</span><input value="'+ list[i].day6 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 6 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day7){
                            htmlStr += '<span>' + 7 +'日'+'：</span><input value="'+ list[i].day7 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 7 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day8){
                            htmlStr += '<span>' + 8 +'日'+'：</span><input value="'+ list[i].day8 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 8 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day9){
                            htmlStr += '<span>' + 9 +'日'+'：</span><input value="'+ list[i].day9 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 9 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day10){
                            htmlStr += '<span>' + 10 +'日'+'：</span><input value="'+ list[i].day10 +'"readonly="readonly"><br/>';
                        }else{
                            htmlStr += '<span>' + 10 +'日'+'：</span><input value="" readonly="readonly"><br/>';
                        }if(list[i].day11){
                            htmlStr += '<span>' + 11 +'日'+'：</span><input value="'+ list[i].day11 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 11 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day12){
                            htmlStr += '<span>' + 12 +'日'+'：</span><input value="'+ list[i].day12 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 12 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day13){
                            htmlStr += '<span>' + 13 +'日'+'：</span><input value="'+ list[i].day13 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 13 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day14){
                            htmlStr += '<span>' + 14 +'日'+'：</span><input value="'+ list[i].day14 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 14 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day15){
                            htmlStr += '<span>' + 15 +'日'+'：</span><input value="'+ list[i].day15 +'"readonly="readonly"><br/>';
                        }else{
                            htmlStr += '<span>' + 15 +'日'+'：</span><input value="" readonly="readonly"><br/>';
                        }if(list[i].day16){
                            htmlStr += '<span>' + 16 +'日'+'：</span><input value="'+ list[i].day16 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 16 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day17){
                            htmlStr += '<span>' + 17 +'日'+'：</span><input value="'+ list[i].day17 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 17 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day18){
                            htmlStr += '<span>' + 18 +'日'+'：</span><input value="'+ list[i].day18 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 18 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day19){
                            htmlStr += '<span>' + 19 +'日'+'：</span><input value="'+ list[i].day19 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 19 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day20){
                            htmlStr += '<span>' + 20 +'日'+'：</span><input value="'+ list[i].day20 +'"readonly="readonly"><br/>';
                        }else{
                            htmlStr += '<span>' + 20 +'日'+'：</span><input value="" readonly="readonly"><br/>';
                        }if(list[i].day21){
                            htmlStr += '<span>' + 21 +'日'+'：</span><input value="'+ list[i].day21 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 21 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day22){
                            htmlStr += '<span>' + 22 +'日'+'：</span><input value="'+ list[i].day22 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 22 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day23){
                            htmlStr += '<span>' + 23 +'日'+'：</span><input value="'+ list[i].day23 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 23 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day24){
                            htmlStr += '<span>' + 24 +'日'+'：</span><input value="'+ list[i].day24 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 24 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day25){
                            htmlStr += '<span>' + 25 +'日'+'：</span><input value="'+ list[i].day25 +'"readonly="readonly"><br/>';
                        }else{
                            htmlStr += '<span>' + 25 +'日'+'：</span><input value="" readonly="readonly"><br/>';
                        }if(list[i].day26){
                            htmlStr += '<span>' + 26 +'日'+'：</span><input value="'+ list[i].day26 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 26 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day27){
                            htmlStr += '<span>' + 27 +'日'+'：</span><input value="'+ list[i].day27 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 27 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day28){
                            htmlStr += '<span>' + 28 +'日'+'：</span><input value="'+ list[i].day28 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 28 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day29){
                            htmlStr += '<span>' + 29 +'日'+'：</span><input value="'+ list[i].day29 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 29 +'日'+'：</span><input value="" readonly="readonly">';
                        }if(list[i].day30){
                            htmlStr += '<span>' + 30 +'日'+'：</span><input value="'+ list[i].day30 +'"readonly="readonly"><br/>';
                        }else{
                            htmlStr += '<span>' + 30 +'日'+'：</span><input value="" readonly="readonly"><br/>';
                        }if(list[i].day31){
                            htmlStr += '<span>' + 31 +'日'+'：</span><input value="'+ list[i].day31 +'"readonly="readonly">';
                        }else{
                            htmlStr += '<span>' + 31 +'日'+'：</span><input value="" readonly="readonly">';
                        }

                    }
                    $('#viewkqform').append(htmlStr);
                }else{
                    $(this).alertmsg('error', '操作失败');
                }
            }
        });
    })
</script>
