<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>公告管理</title>
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
		$("#zhuti").ligerGrid(
				{
					columns : [
							{
								display : '序号',
								width : 50,
								render : function(item, index) {
									return (index + 1);
								}
							},
							{
								display : 'pnid',
								name : 'pnid',
								type : 'int',
								hide : true
							},
							{
								display : '公告名',
								name : 'pntitle'
							},
							{
								display : '公告时间',
								name : 'time'
							},
							{
								display : '发布人',
								name : 'uname'
							},
							{
								display : '操作',
								name : 'active',
								render : function(rowData) {
									return '<a href="javascript:show('
											+ rowData.pnid + ')">'
											+ rowData.active + '</a>';
								}
							} ],
					dataAction : 'local',//提交数据的方式：本地(local)或(server),选择本地方式时将在客服端分页、排序
					pageSize : 20,//每页默认的结果数
					pageSizeOptions : [ 20, 30, 50, 100 ],
					url : "getPn",
					width : '100%',
					height : '100%',
					heightDiff : -1,//高度补差
					record : 'total',//数据源记录数字段名
					usePager : true,//是否分页
					enabledEdit : true, //是否允许编辑
					checkbox : true
				});
		toolbar();
	});
	function toolbar() {
		$.getJSON("getButtons?menuId=26&rnd=" + Math.random(), function(data,
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
	function show(rowid) {
		var dialogOptions = {
			width : 670,
			height : 510,
			title : "查看系统公告",
			url : 'sys/showPn.jsp?pnid=' + rowid,
			buttons : [ {
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
		f_openWindow("sys/addPn.jsp", "添加公告", 520, 440);
	}
	function f_save(item, dialog) {
		var issave = dialog.frame.f_save();
		if (issave) {
			dialog.close();
			top.$.ligerDialog.waitting('数据保存中,请稍候...');
			$.ajax({
				url : "../data/",
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
		var pn = $("#zhuti").ligerGrid();
		var getid = pn.getCheckedRows();

		var str = "";
		$(getid).each(function() {
			str +=this.pnid+",";
		});
		if (str != "") {
			$.ligerDialog.confirm("您确定要删除吗？", function(yes) {
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'delPn',
					data : [ {
						name : 'pnids',
						value : str
					} ],
					success : function(result) {
						if (typeof (result) == "number") {
							if (result == 1) {
								f_reload();
								$.ligerDialog.success('删除成功！');
							} else {
								$.ligerDialog.error('删除失败！');
							}
						}
					}
				});
			});
		} else {
			alert("请选择行！");
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
