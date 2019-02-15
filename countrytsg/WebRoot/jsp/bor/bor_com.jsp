<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>挂失赔偿</title>
<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css">
<link href="../../css/input.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerComboBox.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerGrid.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerDialog.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerDrag.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerToolBar.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerTip.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerMenu.js"></script>
<script type="text/javascript" src="../../js/XHD.js"></script>
<script type="text/javascript">
	$(function() {
		$("#zhuti").ligerGrid({
			columns : [ {
				display : '序号',
				width : 40,
				render : function(item, index) {
					return (index + 1);
				}
			}, {
				display : 'gid',
				name : 'gid',
				type : 'int',
				hide : true
			}, {
				display : '读者id',
				name : 'userid'
			}, {
				display : '读者',
				name : 'uname'
			}, {
				display : '图书编号',
				name : 'bookid',
				width : '12%'
			}, {
				display : '图书名',
				name : 'bname',
				width : '12%'
			}, {
				display : '挂失时间',
				name : 'gtime',
				width : '15%'
			}, {
				display : '应赔金额',
				name : 'yprice',
				width : '8%'
			}, {
				display : '赔偿时间',
				name : 'ptime',
				width : '15%'
			}, {
				display : '实赔金额',
				name : 'sprice',
				width : '8%'
			} ],
			dataAction : 'local',
			pageSize : 20,
			pageSizeOptions : [ 20, 30, 50, 100 ],
			url : "getCompensate",
			width : '100%',
			height : '100%',
			heightDiff : -1,
			record : 'total',
			usePager : true,
			enabledEdit : true,
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
		$.getJSON("getButtons?menuId=30&rnd=" + Math.random, function(data,
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
		f_openWindow("bor/addCom.jsp", "添加挂失", 460, 190);
	}
	function edit() {
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		if (row) {
			url = "bor/addCom.jsp?gid=" + row.gid;
			f_openWindow(url, "修改挂失信息", 460, 190);
		} else {
			$.ligerDialog.warn('请选择行！');
		}
	}
	function f_save(item, dialog) {
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		var issave = dialog.frame.f_save();
		var type = issave.slice(issave.lastIndexOf("=") + 1, issave.length);
		var url = type == "add" ? "addCompen" : "editCompen?gid=" + row.gid;
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
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		if (row) {
			$.ligerDialog.confirm("您确定要移除？", function(yes) {
				if (yes) {
					$.ajax({
						url : "delCompen",
						type : "POST",
						data : {
							gid : row.gid,
							rnd : Math.random()
						},
						success : function(responseText) {
							if (responseText == "true") {
								top.$.ligerDialog.success('操作成功！');
								f_reload();
							} else {
								top.$.ligerDialog
										.error('不可删除！', "", null, 9003);
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
		<div id="toolbar"></div>
		<div id="zhuti" style="margin:-1px"></div>
	</form>
</body>
</html>
