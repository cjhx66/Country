<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>个人信息</title>
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
	function loadForm() {
		toolbar();
		$.ajax({
			type : "GET",
			cache : false,
			url : 'findPerson',
			dataType : 'json',
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				if (res != null) {
					var obj = eval(res);
					$("#cname").val(obj.cname);
					$("#rname").val(obj.rname);
					$("#userid").val(obj.userid);
					$("#uname").val(obj.uname);
					$("#sex").val(obj.sex);
					$("#phone").val(obj.phone);
					$("#address").val(obj.address);
					$("#email").val(obj.email);
				}
			}
		});
	}
	function toolbar() {
		$.getJSON("getButtons?menuId=17&rnd=" + Math.random(), function(data,
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
		});
	}
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
					location.reload();
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
	function edit() {
		f_openWindow('person/editPerson.jsp', "修改个人信息", 450, 240);
	}
	function f_save(item, dialog) {
		var issave = dialog.frame.f_save();
		if (issave) {
			dialog.close();
			top.$.ligerDialog.waitting('数据保存中,请稍候...');
			$.ajax({
				url : "../dada/",
				type : "POST",
				data : issave,
				success : function(responseText) {
					top.$.ligerDialog.closeWaitting();
					top.$.ligerDialog.success('操作成功，请手动刷新！');
					location.reload();
				},
				error : function() {
					top.$.ligerDialog.closeWaitting();
					top.$.ligerDialog.error('操作失败！');
				}
			});

		}
	}
</script>
<style type="text/css">
tr {
	height: 40px;
}

td {
	font-size: 18px;
}

input {
	border: 0;
	font-size: 18px;
	height: 30px;
}
</style>
</head>

<body onload="loadForm()">
	<div id="toolbar"></div>
	<form id="form1">
		<table style="margin:20px;float: left;">
			<tr>
				<td>乡村：</td>
				<td><input id="cname" readOnly="true" /></td>
			</tr>
			<tr>
				<td>角色：</td>
				<td><input id="rname" readOnly="true" /></td>
			</tr>
			<tr>
				<td>编号：</td>
				<td><input id="userid" readOnly="true" /></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><input id="uname" readOnly="true" /></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td><input id="sex" readOnly="true" /></td>
			</tr>

			<tr>
				<td>电话：</td>
				<td><input id="phone" readOnly="true" /></td>
			</tr>

			<tr>
				<td>邮箱：</td>
				<td><input id="email" readOnly="true" /></td>
			</tr>

			<tr>
				<td>地址：</td>
				<td><input id="address" readOnly="true" style="width:260px;" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
