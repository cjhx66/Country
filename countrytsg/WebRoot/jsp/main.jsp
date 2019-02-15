<%@page import="entity.User"%>
<%@page import="entity.Country"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>乡村图书馆管理系统云平台</title>

<link href="../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../css/Toolbar.css" rel="stylesheet" type="text/css" />
<link href="../css/core.css" rel="stylesheet" type="text/css" />
<link href="../js/ligerUI/skins/ext/css/ligerui-fix.css"
	rel="stylesheet" type="text/css" />
<script src="../js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerTab.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerAccordion.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerMenu.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerTree.js"
	type="text/javascript"></script>
<script src="../js/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript"></script>
<script src="../js/Toolbar.js" type="text/javascript"></script>
<script type="text/javascript">
	var tab = null;
	var accordion = null;
	var accordion2 = null;
	var tree = null;
	var manager = null;
	function current() {
		var d = new Date(), str = '';
		str += d.getFullYear() + '年'; //获取当前年份 
		str += d.getMonth() + 1 + '月'; //获取当前月份（0——11） 
		str += d.getDate() + '日';
		str += d.getHours() + '时';
		str += d.getMinutes() + '分';
		str += d.getSeconds() + '秒';
		return str;
	}
	setInterval(function() {
		$("#timer").html(current());
	}, 1000);
	function time() {
		var d = new Date(), str = '';
		str += d.getFullYear() + '-'; //获取当前年份 
		str += d.getMonth() + 1 + '-'; //获取当前月份（0——11） 
		str += d.getDate() + ' ';
		str += d.getHours() + ':';
		str += d.getMinutes() + ':';
		str += d.getSeconds();
		return str;
	}
	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url
		});
	}
	$(function() {
		// $("#pageloading").height($(window).height());
		//布局
		$("#layout1").ligerLayout({
			leftWidth : 190,
			rightWidth : 190,
			bottomHeight : 25,
			allowBottomResize : false,
			allowLeftResize : false,
			allowRightResize : false,
			height : '95%',
			onHeightChanged : f_heightChanged,
			isRightCollapse : true
		});
		var height = $(".l-layout-center").height();
		//Tab
		tab = $("#framecenter").ligerTab({
			height : height,
			dblClickToClose : true,
			showSwitch : true, //显示切换窗口按钮
			showSwitchInTab : true
		//切换窗口按钮显示在最后一项 
		});
		accordion = $("#accordion1").ligerAccordion({
			height : height - 25
		});
		accordion2 = $("#accordion2").ligerAccordion({
			height : height - 25
		});
		f_according();
		toolbar();
	});
	function f_heightChanged(options) {
		if (tab)
			tab.addHeight(options.diff);
		if (accordion && options.middleHeight - 25 > 0)
			accordion.setHeight(options.middleHeight - 25);
		if (accordion2 && options.middleHeight - 25 > 0)
			accordion2.setHeight(options.middleHeight - 25);
	}
	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url
		});
	}
	function changepwd() {
		var dialog = $.ligerDialog.open({
			url : "ChangePwd.jsp",
			width : 350,
			height : 200,
			title : "修改密码",
			buttons : [ {
				text : '保存',
				onclick : function(item, dialog) {
					dialog.frame.f_save();
				}
			}, {
				text : '关闭',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ],
			isResize : true,
			timeParmName : 'a'
		});
	}
	function f_according(appid) {
		if (!appid)
			appid = 1;
		var mainmenu = $("#accordion1");
		mainmenu.empty();
		$
				.getJSON(
						"getMenus?appid=" + appid + "&rnd=" + Math.random(),
						function(menus) {
							$(menus)
									.each(
											function(i, menu) {
												//if(menu==null)return;
												var item = $('<div title="' + menu.Menu_name + '"><ul class="menulist"></ul></div>');
												$(menu.children)
														.each(
																function(j,
																		submenu) {
																	var subitem = $('<li><img/><span></span><div class="menuitem-l"></div><div class="menuitem-r"></div></li>');
																	subitem
																			.attr({
																				url : submenu.Menu_url,
																				tabid : "tabid"
																						+ submenu.Menu_id,
																				menuno : submenu.Menu_id
																			});
																	$("img",
																			subitem)
																			.attr(
																					"src",
																					submenu.Menu_icon
																							|| submenu.icon);
																	$("span",
																			subitem)
																			.html(
																					submenu.Menu_name
																							|| submenu.text);

																	$(
																			"ul:first",
																			item)
																			.append(
																					subitem);
																});
												mainmenu.append(item);
											});
							accordion.render();
							accordion
									.setHeight($(".l-layout-center").height() - 25);
						});

		$("#pageloading").fadeOut(800);

		//tabid计数器，保证tabid不会重复
		var tabidcounter = 0;
		//菜单初始化
		$("ul.menulist li").live('click', function() {
			var jitem = $(this);
			var tabid = jitem.attr("tabid");
			var url = jitem.attr("url");
			if (!url)
				return;
			if (!tabid) {
				tabidcounter++;
				tabid = "tabid" + tabidcounter;
				jitem.attr("tabid", tabid);

				//给url附加menuno
				if (url.indexOf('?') > -1)
					url += "&";
				else
					url += "?";
				url += "MenuNo=" + jitem.attr("menuno");
				jitem.attr("url", url);
			}
			//$("#mainframe").attr("src", url);                

			f_addTab(tabid, $("span:first", jitem).html(), url);
			if ($(this).hasClass("selected")) {
				return;
			} else {
				$(".selected").removeClass("selected");
				$(this).addClass("selected");
			}

		}).live('mouseover', function() {
			var jitem = $(this);
			jitem.addClass("over");
		}).live('mouseout', function() {
			var jitem = $(this);
			jitem.removeClass("over");
		});

	}
	function logout() {
		$.ligerDialog.confirm('您确认要退出系统？', function(yes) {
			if (yes) {
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'logout',
					data : [ {
						name : 'Action',
						value : 'logout'
					}, {
						name : 'time',
						value : time()
					} ],
					success : function(result) {
						javascript: location.replace("../index.jsp");
					}
				});
			}
		});
	}
	//加载系统工具栏     
	function toolbar() {
		$.getJSON("getApps?rnd=" + Math.random(), function(data, textStatus) {
			$("#toolbar").ligerToolBar({
				background : false,
				items : data.Items
			});
		});

		var toolbar = new Toolbar({
			renderTo : 'filters',
			items : [],
			filters : [ {
				id : 'filter-home',
				title : '桌面',
				bodyStyle : 'filter-home',
				handler : function() {
					f_addTab('home');
				}
			}, {
				id : 'filter-sett',
				title : '修改密码',
				bodyStyle : 'filter-sett',
				handler : function() {
					changepwd();
				}
			}, {
				id : 'filter-out',
				title : '退出系统',
				bodyStyle : 'filter-out',
				handler : function() {
					logout();
				}
			} ]
		});
		toolbar.render();
	}
</script>
<link href="../css/main_css.css" rel="stylesheet" type="text/css" />
<%
	String uname = null;
	String cname=null;
	int utype = 0;
	User ul = (User) request.getSession().getAttribute("user");
	if (null == ul)
		response.sendRedirect("../index.jsp");
	else {
		utype = ((User) request.getSession().getAttribute("user"))
				.getRid();
		cname = request.getSession().getAttribute("country")
							.toString()+"图书馆管理系统";
		if (utype == 1) {
			uname = "系统管理员";
		} else if (utype == 2) {
			/* uname = ((User) request.getSession().getAttribute("user"))
					.getUname() + "图书管理员"; */
			uname="图书管理员";
		} else {
			uname = ((User) request.getSession().getAttribute("user"))
					.getUname() + "读者";
		}
	}
%>
</head>

<body>
	<form id="form1">
		<div style="background: #2967a4; height: 92px; overflow: hidden;">
			<div style="height: 65px;">
				<div id="log"><%=cname%></div>
				<div
					style="float: right; width: 220px; height: 47px; margin-right: 65px;">
					<div style="width: 100%; height: 25px; text-align: right;">
						<table style="width: 100%;">
							<tr>
								<td>
									<div id="jnkc"
										style="font-size: 12px; color: black; text-align: right;"></div>
								</td>
							</tr>
						</table>
					</div>

					<div>
						<div id="welCome"
							style="font-size: 12px; padding-right: 5px; width: 135px; margin-right: 60px;  text-align: right;">
							欢迎你：<%=uname%>

						</div>
						<div id="timer"></div>
					</div>
				</div>
			</div>
			<div id="portrait">
				<img src="../images/user_48.png" onerror="noheadimg()"
					id="userheader" width="48px" />
			</div>
			<div
				style="margin: 0; padding: 0; background: url(../images/headbg.gif); height: 28px; overflow: hidden; border-bottom: 1px solid #8db2e3; width: 100%;">
				<div id="toolbar"
					style="height: 27px; width: 780px; float: left; margin-top: 1px;"></div>
				<div id="filters"
					style="height: 27px; width: 200px; float: right; margin-right: 70px;"></div>
			</div>
		</div>
		<div id="layout1" style="width: 100%;">

			<div position="left" title="功能菜单" id="accordion1"></div>
			<div position="center" id="framecenter"<%--title="首页"--%>>
				<div tabid="home" title="桌面" style="height: 300px;">
					<div id="homes"></div>
					<img src="../images/main2.png" style="width:100%;height:100%;" />
				</div>
			</div>
		</div>
		<div id="footer">技术支持：忻州师范学院计算机应用技术研究所</div>
	</form>
</body>
</html>