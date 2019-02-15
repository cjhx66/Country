<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>借阅规则</title>
<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../../css/input.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerGrid.js"
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
	$(function() {
		$("#zhuti").ligerGrid({
			columns : [ {
				display : '序号',
				width : 50,
				render : function(item, index) {
					return (index + 1);
				}
			}, {
				display : 'ruid',
				name : 'ruid',
				type : 'int',
				hide : true
			}, {
				display : '借阅名称',
				name : 'runame'
			}, {
				display : '借阅天数',
				name : 'ruday'
			}, {
				display : '借阅次数',
				name : 'runum'
			}, {
				display : '续借次数',
				name : 'renew'
			}, {
				display : '创建者',
				name : 'uname'
			}, {
				display : '创建时间',
				name : 'rutime',
				width : 200
			} ],
			dataAction : 'local',//提交数据的方式：本地(local)或(server),选择本地方式时将在客服端分页、排序
			pageSize : 20,//每页默认的结果数
			pageSizeOptions : [ 20, 30, 50, 100 ],
			url : "getRule",
			width : '100%',
			height : '100%',
			heightDiff : -1,//高度补差
			record : 'total',//数据源记录数字段名
			usePager : true,//是否分页
			enabledEdit : true, //是否允许编辑
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
		$.getJSON("getButtons?menuId=29&rnd=" + Math.random(), function(data,
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
		f_openWindow("sys/addRule.jsp", "添加借阅规则", 650, 200);
	}
	function edit() {
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		if (row) {
			url = "sys/addRule.jsp?ruid=" + row.ruid;
			f_openWindow(url, "修改规则", 550, 200);
		} else {
			$.ligerDialog.warn('请选择行！');
		}
	}
	function f_save(item, dialog) {
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		var issave = dialog.frame.f_save();
		var type = issave.slice(issave.lastIndexOf("=") + 1, issave.length);
		var url = type == "add" ? "addRule" : "editRule?ruid=" + row.ruid;
		if (issave) {
			dialog.close();
			/* $.ligerDialog.waitting('数据保存中,请稍候...'); */
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
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		if (row) {
			$.ligerDialog.confirm("信息删除后不能恢复，\n您确定要移除？", function(yes) {
				if (yes) {
					$.ajax({
						url : "delRule",
						type : "POST",
						data : {
							ruid : row.ruid,
							rnd : Math.random()
						},
						success : function(responseText) {
							if (responseText == "true") {
								top.$.ligerDialog.success('操作成功！');
								f_reload();
							} else {
								top.$.ligerDialog
										.error('删除失败！', "", null, 9003);
							}
						},
						error : function() {
							top.$.ligerDialog.closeWaitting();
							top.$.ligerDialog.error('删除失败！系统问题，请联系管理员！', "",
									null, 9003);
						}
					});
				}
			});
		} else {
			$.ligerDialog.warn("请选择行!");
		}
	}
</script>
</head>

<body>
	<form id="mainform" onsubmit="return false">
		<div>
			<div id="toolbar"></div>
			<div id="zhuti" style="margin:-1px;"></div>
		</div>
	</form>
</body>
</html>
