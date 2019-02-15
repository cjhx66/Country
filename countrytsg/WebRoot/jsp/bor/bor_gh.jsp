<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>还书记录</title>
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
<script src="../../js/ligerUI/js/plugins/ligerTip.js"
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
				display : 'jid',
				name : 'jid',
				type : 'int',
				hide : true
			}, {
				display : '图书名',
				name : 'bname'
			}, {
				display : '读者id',
				name : 'userid'
			}, {
				display : '读者',
				name : 'uname'
			}, {
				display : '借书时间',
				name : 'jieTime'
			}, {
				display : '应还时间',
				name : 'yingTime'
			}, {
				display : '还书时间',
				name : 'huanTime'
			} ],
			dataAction : 'local',//提交数据的方式：本地(local)或(server),选择本地方式时将在客服端分页、排序
			pageSize : 20,//每页默认的结果数
			pageSizeOptions : [ 20, 30, 50, 100 ],
			url : "findGh",
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
	});
</script>
</head>

<body>
	<div id="zhuti"></div>
</body>
</html>
