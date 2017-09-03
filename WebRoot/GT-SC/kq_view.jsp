<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">

#form input{
	width:40%;
	margin-top:3px
}

.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control{
	round:#ffffff;
}

#form span{
	display:inline-block;
	width:7%;
	font-weight:bold;
	text-align:center
}

</style>
<div class="bjui-pageContent tableContent">
    <form action="updatekqb.action" class="pageForm" id="form" data-toggle="validate">
       	<input type="hidden" name="" value="<s:property value="kqb.scz"/>" readonly="readonly">
        <input type="hidden" name="type" value="<s:property value="type"/>" readonly="readonly">
       	<span>姓名：</span><input name="kqb.name" value="<s:property value="kqb.name"/>"  readonly="readonly">
        <span>生产组：</span><input name="scz" value="<s:property value="scz"/>"  readonly="readonly"><br/>
        <span>岗位：</span><input name="kqb.gw" value="<s:property value="kqb.gw"/>" readonly="readonly">
      	<input name="kqb.uuid" type="hidden" value="<s:property value="kqb.uuid" />"readonly="readonly">
        <span>年份：</span><input name="kqb.year" value="<s:property value="kqb.year" />"readonly="readonly" style="width:15%">
        <span>月份：</span><input name="kqb.month" value="<s:property value="kqb.month" />"readonly="readonly" style="width:15%"><br/><br/><br/>
        <span>1号：</span><input name="kqb.day1" class="kqb.day1" value="<s:property value="kqb.day1" />"readonly="readonly">
        <span>2号：</span><input name="kqb.day2" class="kqb.day2" value="<s:property value="kqb.day2" />"readonly="readonly"><br/>
        <span>3号：</span><input name="kqb.day3" class="kqb.day3" value="<s:property value="kqb.day3" />"readonly="readonly">
        <span>4号：</span><input name="kqb.day4" class="kqb.day4" value="<s:property value="kqb.day4" />"readonly="readonly"><br/>
        <span>5号：</span><input name="kqb.day5" class="kqb.day5" value="<s:property value="kqb.day5" />"readonly="readonly">
        <span>6号：</span><input name="kqb.day6" class="kqb.day6" value="<s:property value="kqb.day6" />"readonly="readonly"><br/>
        <span>7号：</span><input name="kqb.day7" class="kqb.day7" value="<s:property value="kqb.day7" />"readonly="readonly">
        <span>8号：</span><input name="kqb.day8" class="kqb.day8" value="<s:property value="kqb.day8" />"readonly="readonly"><br/>
        <span>9号：</span><input name="kqb.day9" class="kqb.day9" value="<s:property value="kqb.day9" />"readonly="readonly">
        <span>10号：</span><input name="kqb.day10" class="kqb.day10" value="<s:property value="kqb.day10" />"readonly="readonly"><br/>
        <span>11号：</span><input name="kqb.day11" class="kqb.day11" value="<s:property value="kqb.day11" />"readonly="readonly">
        <span>12号：</span><input name="kqb.day12" class="kqb.day12" value="<s:property value="kqb.day12" />"readonly="readonly"><br/>
        <span>13号：</span><input name="kqb.day13" class="kqb.day13" value="<s:property value="kqb.day13" />"readonly="readonly">
        <span>14号：</span><input name="kqb.day14" class="kqb.day14" value="<s:property value="kqb.day14" />"readonly="readonly"><br/>
        <span>15号：</span><input name="kqb.day15" class="kqb.day15" value="<s:property value="kqb.day15" />"readonly="readonly">
        <span>16号：</span><input name="kqb.day16" class="kqb.day16" value="<s:property value="kqb.day16" />"readonly="readonly"><br/>
        <span>17号：</span><input name="kqb.day17" class="kqb.day17" value="<s:property value="kqb.day17" />"readonly="readonly">
        <span>18号：</span><input name="kqb.day18" class="kqb.day18" value="<s:property value="kqb.day18" />"readonly="readonly"><br/>
        <span>19号：</span><input name="kqb.day19" class="kqb.day19" value="<s:property value="kqb.day19" />"readonly="readonly">
        <span>20号：</span><input name="kqb.day20" class="kqb.day20" value="<s:property value="kqb.day20" />"readonly="readonly"><br/>
        <span>21号：</span><input name="kqb.day21" class="kqb.day21" value="<s:property value="kqb.day21" />"readonly="readonly">
        <span>22号：</span><input name="kqb.day22" class="kqb.day22" value="<s:property value="kqb.day22" />"readonly="readonly"><br/>
        <span>23号：</span><input name="kqb.day23" class="kqb.day23" value="<s:property value="kqb.day23" />"readonly="readonly">
        <span>24号：</span><input name="kqb.day24" class="kqb.day24" value="<s:property value="kqb.day24" />"readonly="readonly"><br/>
        <span>25号：</span><input name="kqb.day25" class="kqb.day25" value="<s:property value="kqb.day25" />"readonly="readonly">
        <span>26号：</span><input name="kqb.day26" class="kqb.day26" value="<s:property value="kqb.day26" />"readonly="readonly"><br/>
        <span>27号：</span><input name="kqb.day27" class="kqb.day27" value="<s:property value="kqb.day27" />"readonly="readonly">
        <span>28号：</span><input name="kqb.day28" class="kqb.day28" value="<s:property value="kqb.day28" />"readonly="readonly"><br/>
        <span>29号：</span><input name="kqb.day29" class="kqb.day29" value="<s:property value="kqb.day29" />"readonly="readonly">
        <span>30号：</span><input name="kqb.day30" class="kqb.day30" value="<s:property value="kqb.day30" />"readonly="readonly"><br/>
        <span>31号：</span><input name="kqb.day31" class="kqb.day31" value="<s:property value="kqb.day31" />"readonly="readonly"><br/>
		<div style="margin-top:10px">
	    	<span>月考勤统计：</span>
	        <textarea cols="60" rows="6" name="kqb.monthcount" style="display:inline-block;vertical-align:middle;" readonly="readonly">
	        <s:property value="kqb.monthcount" />
	        </textarea>
	    </div> 
    </form>
</div>
<s:if test="type==1"></s:if><s:else>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>
</s:else>

<script>
</script>
