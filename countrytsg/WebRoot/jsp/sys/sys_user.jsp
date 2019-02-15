<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script src="../../js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../../css/input.css" rel="stylesheet" type="text/css" />
<script src="../../js/ligerUI/js/plugins/ligerTree.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerMenu.js"
	type="text/javascript"></script>
<script src="../../js/XHD.js" type="text/javascript"></script>
<script type="text/javascript">
	function time() {
		var d = new Date(), str = '';
		str += d.getFullYear() + '-';//获取当前年份 
		str += d.getMonth() + 1 + '-';//获取当前月份（0——11）
		str += d.getDate() + '';
		str += d.getHours() + '';
		str += d.getMinutes() + ':';
		str += d.getSeconds();
		return str;
	}
	$(function() {
		$("#zhuti").ligerGrid({
			columns : [ {
				display : '序号',
				width : 50,
				render : function(item, index) {
					return (index + 1);
				}
			}, {
				display : 'uid',
				name : 'UID',
				type : 'int',
				hide : true
			}, {
				display : '用户名',
				name : 'userid'
			}, {
				display : '姓名',
				name : 'uname'
			}, {
				display : '所属乡村',
				name : 'cname'
			}, {
				display : '角色',
				name : 'rname'
			}, {
				display : '性别',
				name : 'sex',
				width : 60
			}, {
				display : '联系方式',
				name : 'userPhone'
			}, {
				display : '电子邮箱',
				name : 'email',
				width : 160
			}, {
				display : '联系地址',
				name : 'address',
				width : 160
			}, {
				display : '添加时间',
				name : 'uaddTime',
				width : 160
			} ],
			dataAction : 'local',//提交数据的方式：本地(local)或(server),选择本地方式时将在客服端分页、排序
			pageSize : 20,//每页默认的结果数
			pageSizeOptions : [ 20, 30, 50, 100 ],
			url : "getUsers",//getUsers根据cid查询全部
			width : '100%',
			height : '100%',
			heightDiff : -1,//高度补差
			record : 'total',//数据源记录数字段名
			usePager : true,//是否分页
			enabledEdit : true, //是否允许编辑
			checkbox : true,//是否有多选框
			onContextmenu : function(parm, e) {
				actionCustomerID = parm.data.id;
				menu.show({
					top : e.pageY,
					left : e.pageX
				});
				return false;
			}
		});
		toolbar();
	});
	function toolbar() {
		$.getJSON("getButtons?menuId=24&rnd=" + Math.random(), function(data,
				textStatus) {
			var items = [];
			var arr = data.Items;
			for ( var i = 0; i < arr.length; i++) {
				arr[i].icon = "../" + arr[i].icon;
				items.push(arr[i]);
			}
			$("#toolbar").ligerToolBar({
				items : items
			});
			$("#zhuti").ligerGetGridManager().onResize();
		});
	}
	function f_reload() {
		var manager = $("#zhuti").ligerGetGridManager();
		manager.loadData(true);
		location.reload();
	};
	var activeDialog = null;
	function f_openWindow(url, title, width, height) {
		var dialogOptions = {
			width : width,
			height : height,
			title : title,
			url : url,
			buttons : [ {
				text : '保存',
				onclick : function(item, dialog) {
					f_save(item, dialog);
					f_reload();
				}
			}, {
				text : '关闭',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ],
			isResize : true,
			timeParmName : 'a'
		};
		activeDialog = parent.jQuery.ligerDialog.open(dialogOptions);
	}
	function add() {
		f_openWindow("sys/addUser.jsp", "添加用户", 760, 300);
	}
	function edit() {
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		var c = $("#c").text();
		if (row) {
			if (c == 1) {
				url = "sys/addUser.jsp?uid=" + row.UID;
				f_openWindow(url, "修改帐户信息", 760, 300);
			} else if (c != 1 && row.rname == "系统管理员") {
				$.ligerDialog.warn('权限不够！');
			} else {
				url = "sys/addUser.jsp?uid=" + row.UID;
				f_openWindow(url, "修改帐户信息", 760, 300);
			}
		} else {
			$.ligerDialog.warn('请选择行！');
		}
	}
	function f_save(item, dialog) {
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		var issave = dialog.frame.f_save();
		var type = issave.slice(issave.lastIndexOf("=") + 1, issave.length);
		var url = type == "add" ? "addUser" : "editUser?uid=" + row.UID;
		if (issave) {
			dialog.close();
			$.ligerDialog.waitting('数据保存中,请稍候...');
			$.ajax({
				url : url,
				type : "POST",
				data : issave,
				success : function(responseText) {
					$.ligerDialog.closeWaitting();
					$.ligerDialog.success('操作成功！');
					f_reload();
				},
				error : function() {
					$.ligerDialog.closeWaitting();
					$.ligerDialog.error('操作失败！');
				}
			});

		}
	}
	function del() {
		var m = $("#zhuti").ligerGrid();
		var getid = m.getCheckedRows();
		var str = "";
		$(getid).each(function() {
			str += this.UID + ",";
		});
		if (str != "") {
			var s = str.toString().substring(0, 2);
			if (s == "1,") {
				top.$.ligerDialog.error('删除失败！不能删除系统管理员');
			} else {
				$.ligerDialog.confirm("信息删除后不能恢复，\n您确定移除？", function(yes) {
					if (yes) {
						$.ajax({
							url : 'delUser',
							type : 'post',
							dataType : 'json',
							data : [ {
								name : 'uids',
								value : str
							} ],
							success : function(responseText) {
								if (typeof (responseText) == "number") {
									if (responseText == 1) {
										top.$.ligerDialog.success('操作成功！');
										location.reload();
									} else {
										top.$.ligerDialog.error('删除失败！', "",
												null, 9003);
									}
								}
							},
						});
					}
				});
			}
		} else {
			$.ligerDialog.warn('请选择行！');
		}
	}
</script>
<%
	int rid;
	rid = ((User) request.getSession().getAttribute("user")).getRid();
%>
</head>

<body>
	<form id="mainform" onsubmit="return false">
		<div>
			<div id="toolbar"></div>
			<div id="c" style="display: none;"><%=rid%></div>
			<div id="zhuti" style="margin:-1px;"></div>
		</div>
	</form>
</body>
</html>
