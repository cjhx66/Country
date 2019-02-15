<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图书资源</title>
<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
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
	function toolbar() {
		$.getJSON("getButtons?menuId=15&rnd=" + Math.random(), function(data,
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
	toolbar();
	$(function() {
		$("#zhuti").ligerGrid({
			columns : [ {
				display : '序号',
				width : 50,
				render : function(item, index) {
					return (index + 1);
				}
			}, {
				display : 'eid',
				name : 'eid',
				type : 'int',
				hide : true
			}, {
				display : '图书名称',
				name : 'bname'
			}, {
				display : '类型',
				name : 'type',
				hide : true
			}, {
				display : '上传时间',
				name : 'time'
			}, {
				display : '操作',
				name : 'down',
				width : 50,
				render : function(rowData) {
					var f = "";
					$.ajax({
						type : 'get',
						dataType : 'text',
						url : 'findEk',//查询单条
						async : false,//同步，true时异步
						data : {
							id : rowData.eid
						},
						success : function(res) {
							f = res;
						}
					});
					return '<a href="../../'+f+'">' + rowData.down + '</a>';
				}
			} ],
			dataAction : 'local',
			pageSize : 20,
			pageSizeOptions : [ 20, 30, 50, 100 ],
			enabledEdit : true,
			url : "getEbook",
			width : '100%',
			height : '100%',
			heightDiff : -1,
			record : 'total',
			usePager : true,
			checkbox : true,
			onRClickToSelect : true,
			onContextmenu : function(parm, e) {
				actionCustomerID = parm.data.eid;
				menu.show({
					top : e.pageY,
					left : e.pageX
				});
				return false;
			}
		});
	});
	var activeDialog = null;
	function f_openWindow(url, title, width, height) {
		var dialogOptions = {
			width : width,
			height : height,
			title : title,
			url : url,
			buttons : [ {
				text : '上传',
				onclick : function(item, dialog) {
					f_save(item, dialog);
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
					f_reload();
				},
				error : function() {
					$.ligerDialog.closeWaitting();
					$.ligerDialog.error('操作失败！');
				}
			});
		}
	}
	function add() {
		f_openWindow('cty/addEbook.jsp', "上传资源", 550, 200);
	}
	function f_reload() {
		var manager = $("#zhuti").ligerGetGridManager();
		manager.loadData(true);
		/* location.reload(); */
	};
	function del() {
		var m = $("#zhuti").ligerGrid();
		var getid = m.getCheckedRows();

		var str = "";
		$(getid).each(function() {
			str += this.eid + ",";
		});
		if (str != "") {
			$.ligerDialog.confirm("确定删除吗？", function(yes) {
				if (yes) {
					$.ajax({
						url : 'delEbook',
						type : 'post',
						dataType : 'json',
						data : [ {
							name : 'eids',
							value : str
						} ],
						success : function(res) {
							if (typeof (res) == "number") {
								if (res == 1) {
									top.$.ligerDialog.success('操作成功！');
									location.reload();
								} else {
									top.$.ligerDialog.error('删除失败！', "", null,
											9003);
								}
							}
						}
					});
				}
			});
		} else {
			top.$.ligerDialog.warn('请选择行！');
		}
	}
</script>
</head>

<body style="padding:0px;margin:0;overflow:hidden;">
	<div>
		<div id="toolbar"></div>
		<div id="zhuti"></div>
	</div>
</body>
</html>
