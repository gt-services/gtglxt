<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>国通管理系统</title>
<meta name="Keywords"
	content="B-JUI,Bootstrap,DWZ,jquery,ui,前端,框架,开源,OSC,开源框架,knaan" />
<meta name="Description"
	content="B-JUI, Bootstrap for DWZ富客户端框架，基于DWZ富客户端框架修改。主要针对皮肤，编辑器，表单验证等方面进行了大量修改，引入了Bootstrap，Font Awesome，KindEditor，jquery.validationEngine，iCheck等众多开源项目。交流QQ群：232781006。" />
<!-- bootstrap - css -->
<link href="BJUI/themes/css/bootstrap.css" rel="stylesheet">
<!-- core - css -->
<link href="BJUI/themes/css/style.css" rel="stylesheet">
<link href="BJUI/themes/blue/core.css" id="bjui-link-theme"
	rel="stylesheet">
<!-- plug - css -->
<link href="BJUI/plugins/kindeditor_4.1.10/themes/default/default.css"
	rel="stylesheet">
<link href="BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css"
	rel="stylesheet">
<link href="BJUI/plugins/niceValidator/jquery.validator.css"
	rel="stylesheet">
<link href="BJUI/plugins/bootstrapSelect/bootstrap-select.css"
	rel="stylesheet">
<link href="BJUI/themes/css/FA/css/font-awesome.min.css"
	rel="stylesheet"> 
<!--[if lte IE 7]>
    <link href="BJUI/themes/css/ie7.css" rel="stylesheet">
    <![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
    <script src="BJUI/other/html5shiv.min.js"></script>
    <script src="BJUI/other/respond.min.js"></script>
    <![endif]-->
<!-- jquery -->
<script src="BJUI/js/jquery-1.7.2.min.js"></script>
<script src="BJUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
    <script src="BJUI/other/jquery.iframe-transport.js"></script>
    <![endif]-->
<!-- BJUI.all 分模块压缩版 -->
<script src="BJUI/js/bjui-all.js"></script>
<script src="BJUI/plugins/swfupload/swfupload.js"></script>
<!-- kindeditor -->

<!-- colorpicker -->
<script src="BJUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script src="BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="BJUI/plugins/niceValidator/jquery.validator.js"></script>
<script src="BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="BJUI/plugins/bootstrap.min.js"></script>
<script src="BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script src="BJUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
<!-- icheck -->
<script src="BJUI/plugins/icheck/icheck.min.js"></script>
<!-- dragsort -->
<script src="BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<!-- other plugins -->
<script src="BJUI/plugins/other/jquery.autosize.js"></script>
<link href="BJUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
<%-- <script src="BJUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
<script src="BJUI/plugins/download/jquery.fileDownload.js"></script> --%>
<script src="js/ajaxfileupload.js" type="text/javascript"></script>
<!-- init -->
<script type="text/javascript">
    	
    	$("#authority").val();
        $(function() {
            BJUI.init({
                JSPATH       : 'BJUI/',         //[可选]框架路径
                PLUGINPATH   : 'BJUI/plugins/', //[可选]插件路径
                loginInfo    : {url:'login_timeout.html', title:'登录', width:400, height:200}, // 会话超时后弹出登录对话框
                statusCode   : {ok:200, error:300, timeout:301}, //[可选]
                ajaxTimeout  : 50000, //[可选]全局Ajax请求超时时间(毫秒)
                pageInfo     : {total:'total', pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]分页参数
                alertMsg     : {displayPosition:'topcenter', displayMode:'slide', alertTimeout:3000}, //[可选]信息提示的显示位置，显隐方式，及[info/correct]方式时自动关闭延时(毫秒)
                keys         : {statusCode:'statusCode', message:'message'}, //[可选]
                ui           : {
                    windowWidth      : 0,    //框架可视宽度，0=100%宽，> 600为则居中显示
                    showSlidebar     : true, //[可选]左侧导航栏锁定/隐藏
                    clientPaging     : true, //[可选]是否在客户端响应分页及排序参数
                    overwriteHomeTab : false //[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
                },
                debug        : true,    // [可选]调试模式 [true|false，默认false]
                theme        : 'sky' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
            })

            // main - menu
            $('#bjui-accordionmenu')
                    .collapse()
                    .on('hidden.bs.collapse', function(e) {
                        $(this).find('> .panel > .panel-heading').each(function() {
                            var $heading = $(this), $a = $heading.find('> h4 > a')

                            if ($a.hasClass('collapsed')) $heading.removeClass('active')
                        })
                    })
                    .on('shown.bs.collapse', function (e) {
                        $(this).find('> .panel > .panel-heading').each(function() {
                            var $heading = $(this), $a = $heading.find('> h4 > a')

                            if (!$a.hasClass('collapsed')) $heading.addClass('active')
                        })
                    })

            $(document).on('click', 'ul.menu-items > li > a', function(e) {
                var $a = $(this), $li = $a.parent(), options = $a.data('options').toObj()
                var onClose = function() {
                    $li.removeClass('active')
                }
                var onSwitch = function() {
                    $('#bjui-accordionmenu').find('ul.menu-items > li').removeClass('switch')
                    $li.addClass('switch')
                }

                $li.addClass('active')
                if (options) {
                    options.url      = $a.attr('href')
                    options.onClose  = onClose
                    options.onSwitch = onSwitch
                    if (!options.title) options.title = $a.text()

                    if (!options.target)
                        $a.navtab(options)
                    else
                        $a.dialog(options)
                }

                e.preventDefault()
            })

            //时钟
            var today = new Date(), time = today.getTime()
            $('#bjui-date').html(today.formatDate('yyyy/MM/dd'))
            setInterval(function() {
                today = new Date(today.setSeconds(today.getSeconds() + 1))
                $('#bjui-clock').html(today.formatDate('HH:mm:ss'))
            }, 1000)
        })

        //菜单-事件
        function MainMenuClick(event, treeId, treeNode) {
            event.preventDefault()

            if (treeNode.isParent) {
                var zTree = $.fn.zTree.getZTreeObj(treeId)

                zTree.expandNode(treeNode, !treeNode.open, false, true, true)
                return
            }

            if (treeNode.target && treeNode.target == 'dialog')
                $(event.target).dialog({id:treeNode.tabid, url:treeNode.url, title:treeNode.name})
            else
                $(event.target).navtab({id:treeNode.tabid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh, external:treeNode.external})
        }
    </script>
<!-- for doc begin -->

<link href="doc/doc.css" rel="stylesheet">

</head>
<body>

	<header id="bjui-header">
	<div class="bjui-navbar-header" style="margin-left: 20px">
		<h1>国通管理系统</h1>
	</div>
	<nav id="bjui-navbar-collapse">
	<ul class="bjui-navbar-right">
		<li class="datetime"><div>
				<span id="bjui-date"></span> <span id="bjui-clock"></span>
			</div></li>
		<li><a href="#">消息 <span class="badge">4</span></a></li>
		<li><a href="#"> <span>角色:</span> <span><s:property value="#session.authority" /></span> <input id="authority" type="hidden"
				value="<s:property value="#session.admin.authority"/>" />
		</a></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">我的账户 <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li><a href="changpwd.jsp" data-toggle="dialog"
					data-id="changepwd_page" data-mask="true">&nbsp;<span
						class="glyphicon glyphicon-lock"></span> 修改密码&nbsp;
				</a></li>
				<li><a href="myinfo.jsp" data-toggle="dialog"
					data-id="changepwd_page" data-mask="true" data-width="400"
					data-height="260">&nbsp;<span class="glyphicon glyphicon-user"></span>
						我的资料
				</a></li>
				<li class="divider"></li>
				<li><a href="logout.action" class="red">&nbsp;<span
						class="glyphicon glyphicon-off"></span> 注销登陆
				</a></li>
			</ul></li>
		<!-- <li><a href="index_tree.html" title="切换为树状导航(宽版)"
			style="background-color:#ff7b61;">树状导航栏(宽版)</a></li> -->
		<li class="dropdown"><a href="#"
			class="dropdown-toggle theme blue" data-toggle="dropdown"
			title="切换皮肤"><i class="fa fa-tree"></i></a>
			<ul class="dropdown-menu" role="menu" id="bjui-themes">
				<li><a href="javascript:;" class="theme_default"
					data-toggle="theme" data-theme="default">&nbsp;<i
						class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;
				</a></li>
				<li><a href="javascript:;" class="theme_orange"
					data-toggle="theme" data-theme="orange">&nbsp;<i
						class="fa fa-tree"></i> 橘子红了
				</a></li>
				<li><a href="javascript:;" class="theme_purple"
					data-toggle="theme" data-theme="purple">&nbsp;<i
						class="fa fa-tree"></i> 紫罗兰
				</a></li>
				<li class="active"><a href="javascript:;" class="theme_blue"
					data-toggle="theme" data-theme="blue">&nbsp;<i
						class="fa fa-tree"></i> 天空蓝
				</a></li>
				<li><a href="javascript:;" class="theme_green"
					data-toggle="theme" data-theme="green">&nbsp;<i
						class="fa fa-tree"></i> 绿草如茵
				</a></li>
			</ul></li>
	</ul>
	
	</nav>

	</header>
	<div id="bjui-container" class="clearfix">
		<div id="bjui-leftside">
			<div id="bjui-sidebar-s">
				<div class="collapse"></div>
			</div>
			<div id="bjui-sidebar">
				<div class="toggleCollapse">
					<h2>
						<i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i>
					</h2>
					<a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a>
				</div>
				<div class="panel-group panel-main" data-toggle="accordion"
					id="bjui-accordionmenu" data-heightbox="#bjui-sidebar"
					data-offsety="10">
					<s:if test="#session.admin.authority == 1">
					<div class="panel panel-default">
						<div class="panel-heading panelContent active">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#bjui-accordionmenu"
									href="#bjui-collapse0"><i class="fa fa-caret-square-o-down"></i>&nbsp;&nbsp;管理员</a>
							</h4>
						</div>
						<div id="bjui-collapse0"
							class="panel-collapse panelContent collapse">
							<div>
								<ul id="bjui-tree0" class="ztree ztree_main" data-toggle="ztree"
									data-on-click="MainMenuClick" data-expand-all="true">
									<li data-id="40" data-pid="4" data-url="getAdminList.action"
										data-tabid="dialog" data-faicon="plane">权限管理</li>
									<li data-id="41" data-pid="4" data-url="getsecond.action"
										data-tabid="alert" data-faicon="info-circle">生产部项目组管理</li>
								</ul>
							</div>
						</div>
						<div class="panelFooter">
							<div class="panelFooterContent"></div>
						</div>
					</div>
					</s:if>
					<s:if test="#session.admin.authority == 1 || #session.admin.authority == 2">
					<div class="panel panel-default">
						<div class="panel-heading panelContent">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#bjui-accordionmenu"
									href="#bjui-collapse1" class="active"><i
									class="fa fa-caret-square-o-down"></i>&nbsp;&nbsp;人事部门</a>
							</h4>
						</div>
						<div id="bjui-collapse1"
							class="panel-collapse panelContent collapse">
							<div>
								<ul id="bjui-tree1" class="ztree ztree_main" data-toggle="ztree"
									data-on-click="MainMenuClick" data-expand-all="true">
									<li data-id="31" data-pid="3" data-url="getRosterList.action"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      "
									data-tabid="datagrid-convertable" data-faicon="table">人事信息</li>
									
									<li data-id="20" data-pid="2" data-url="yujing.action"
										data-tabid="table" data-faicon="table">代缴社保预警</li>
									
									<!-- <li data-id="21" data-pid="2" data-url="table-fixed.html"
										data-tabid="table-fixed" data-faicon="list-alt">社保信息录入</li> -->
									
									<li data-id="20" data-pid="2" data-url="getKqbListrs.action"
										data-tabid="table" data-faicon="table">考勤信息</li>
									<li data-id="20" data-pid="2" data-url="getTodayAddRosterList.action"
										data-tabid="table" data-faicon="table">本周新增人员信息</li>
									<li data-id="20" data-pid="2" data-url="getTodayDelRosterList.action"
										data-tabid="table" data-faicon="table">本周离职人员信息</li>
									<li data-id="20" data-pid="2" data-url="getOverAgeRosterList.action"
										data-tabid="table" data-faicon="table">超龄预警</li>
									<li data-id="20" data-pid="2" data-url="getLowAgeRosterList.action"
										data-tabid="table" data-faicon="table">未到年龄预警</li>
										<li data-id="20" data-pid="2" data-url="getHtdqRosterList.action"
										data-tabid="table" data-faicon="table">合同到期预警</li>
								</ul>
							</div>
						</div>
						<div class="panelFooter">
							<div class="panelFooterContent"></div>
						</div>
					</div>
					</s:if>
					<s:if test="#session.admin.authority == 1 || #session.admin.authority == 4">
					<div class="panel panel-default">
						<div class="panel-heading panelContent">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#bjui-accordionmenu"
									href="##bjui-collapse2"><i
									class="fa fa-caret-square-o-down"></i>&nbsp;&nbsp;培训部门</a>
							</h4>
						</div>
						<div id="bjui-collapse2"
							class="panel-collapse panelContent collapse">
							<div>
								<ul id="bjui-tree2" class="ztree ztree_main" data-toggle="ztree"
									data-on-click="MainMenuClick" data-expand-all="true">
									<li data-id="10" data-pid="1" data-url="getPxInfoList.action?pageType=1"
										data-tabid="form-button" data-faicon="hand-o-up">培训人员信息录入</li>
									<li data-id="11" data-pid="1" data-url="getPxInfoList.action?pageType=2"
										data-tabid="form-input" data-faicon="terminal">学员缴费概览</li>
									<li data-id="12" data-pid="1" data-url="getPxInfoList.action?pageType=3"
										data-tabid="form-select" data-faicon="caret-square-o-down">即将到期证书</li>
									<li data-id="13" data-pid="1" data-url="getPxInfoList.action?pageType=4"
										data-tabid="table" data-faicon="check-square-o">未领证人员</li>
									<li data-id="13" data-pid="1" data-url="getPxInfoList.action?pageType=5"
										data-tabid="table" data-faicon="check-square-o">考试人员名单</li>
									<li data-id="13" data-pid="1" data-url="getPxInfoList.action?pageType=6"
										data-tabid="table" data-faicon="check-square-o">补考人员名单</li>
								</ul>
							</div>
						</div>
						<div class="panelFooter">
							<div class="panelFooterContent"></div>
						</div>
					</div>
					</s:if>
					<s:if test="#session.admin.authority == 1 || #session.admin.authority == 5">
					<div class="panel panel-default">
						<div class="panel-heading panelContent">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#bjui-accordionmenu"
									href="#bjui-collapse3" class="active"><i
									class="fa fa-caret-square-o-down"></i>&nbsp;&nbsp;生产部门</a>
							</h4>
						</div>
						<div id="bjui-collapse3"
							class="panel-collapse panelContent collapse ">
							<div>
								<ul id="bjui-tree3" class="ztree ztree_main" data-toggle="ztree"
									data-on-click="MainMenuClick" data-expand-all="true">
									<li data-id="20" data-pid="2" data-url="getKqbList.action"
										data-tabid="table" data-faicon="table">人员考勤</li>

									<li data-id="21" data-pid="2" data-url="getSCList.action"
										data-tabid="table-fixed" data-faicon="list-alt">录入人事信息</li>
									<li data-id="21" data-pid="2" data-url="getDeparture.action"
										data-tabid="table-fixed" data-faicon="list-alt">已离职人员</li>

									<li data-id="21" data-pid="2" data-url="getJob.action"
										data-tabid="table-fixed" data-faicon="list-alt">岗位添加</li>
										<li data-id="21" data-pid="2" data-url="getSize.action"
										data-tabid="table-fixed" data-faicon="list-alt">规格添加</li>
								</ul>
							</div>
						</div>
						<div class="panelFooter">
							<div class="panelFooterContent"></div>
						</div>
					</div>
					</s:if>
					<s:if test="#session.admin.authority == 1 || #session.admin.authority == 3">
					<div class="panel panel-default">
						<div class="panel-heading panelContent">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#bjui-accordionmenu"
									href="#bjui-collapse4" class="active"><i
									class="fa fa-caret-square-o-down"></i>&nbsp;&nbsp;财务部门</a>
							</h4>
						</div>
						<div id="bjui-collapse4"
							class="panel-collapse panelContent collapse ">
							<div>
								<ul id="bjui-tree4" class="ztree ztree_main" data-toggle="ztree"
									data-on-click="MainMenuClick" data-expand-all="true">
									<li data-id="20" data-pid="2" data-url="getcw.action"
										data-tabid="table" data-faicon="table">工资发放信息</li>
									<li data-id="21" data-pid="2" data-url="getcwallinfo"
										data-tabid="table-fixed" data-faicon="list-alt">社保信息录入</li>
										<li data-id="21" data-pid="2" data-url="sbdetail.action"
										data-tabid="table-fixed" data-faicon="list-alt">社保信息查询</li>
									<li data-id="20" data-pid="2" data-url="yujing.action"
										data-tabid="table" data-faicon="table">代缴社保预警</li>
									<li data-id="20" data-pid="2" data-url="getKqbListcw.action"
										data-tabid="table" data-faicon="table">考勤信息</li>
								</ul>
							</div>
						</div>
						<div class="panelFooter">
							<div class="panelFooterContent"></div>
						</div>
					</div>
					</s:if>
				</div>
			</div>
		</div>
		<div id="bjui-navtab" class="tabsPage">
			<div class="tabsPageHeader">
				<div class="tabsPageHeaderContent">
					<ul class="navtab-tab nav nav-tabs">
						<li><a href="javascript:;"><span><i
									class="fa fa-home"></i> #maintab#</span></a></li>
					</ul>
				</div>
				<div class="tabsLeft">
					<i class="fa fa-angle-double-left"></i>
				</div>
				<div class="tabsRight">
					<i class="fa fa-angle-double-right"></i>
				</div>
				<div class="tabsMore">
					<i class="fa fa-angle-double-down"></i>
				</div>
			</div>
			<ul class="tabsMoreList">
				<li><a href="javascript:;">#maintab#</a></li>
			</ul>
			<div class="navtab-panel tabsPageContent">
				<div class="navtabPage unitBox">
					<div class="bjui-pageContent" style="background:#FFF;">
						<div class="panel panel-default" style="padding:0">
							<div class="panel-heading">
								<h3 class="panel-title">
									操作日志
								</h3>
							</div>
							<div class="panel-body">
								<div>
									开发中...
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<footer id="bjui-footer">Copyright &copy; 2017 - 2018
		<a href="#" target="_blank">国通企业管理有限公司</a> </footer>

</body>



</html>
