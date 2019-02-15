<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>本馆信息</title>
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
			url : 'getLibName',
			dataType : 'json',
			contentType : "application/json;charset=utf-8",
			success : function(result) {
				if (result != null) {
					var obj = eval(result);
					$("#lname").val(obj.lname);
					$("#cname").val(obj.cname);
					$("#uname").val(obj.uname);
					$("#collectBk").val(obj.colbooks + "本");
					$("#userNum").val(obj.userNum + "人");
					$("#area").val(obj.area);
					$("#lphone").val(obj.lphone);
					$("#laddress").val(obj.laddress);
					$("#intro").val(obj.intro);
				} else {
					add();
					/* top.$.ligerDialog.error('您还没有填写信息,请填写信息！'); */
				}
			}
		});
	}
	function toolbar() {
		$.getJSON("getButtons?menuId=28&rnd=" + Math.random(), function(data,
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
		f_openWindow('sys/editLibrary.jsp', "修改本馆信息", 650, 440);
	}
	function add() {
		f_openWindow('sys/addLibrary.jsp', "填写本馆信息", 650, 440);
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
.l {
	border-left-style: dotted;
	border-color: #ABC7EC;
	margin: 20px;
	padding-left: 20px;
	float: left;
}

.e {
	text-align: center;
	font-size: 18px;
	height: 30px;
}

input {
	font-size: 16px;
	color: black;
	border: none;
}

textarea {
	color: black;
	border: none;
	font-size: 16px;
}

table td {
	font-size: 16px;
	color: black;
	height: 30px;
	text-align: left;
}
</style>
</head>

<body onload="loadForm()">
	<div id="toolbar"></div>
	<form id="form1">
		<table style="margin:20px;float: left;">
			<tr>
				<td>图书馆名称：</td>
				<td><input id="lname" readOnly="true" /></td>
			</tr>
			<tr>
				<td>乡村名：</td>
				<td><input id="cname" readOnly="true" /></td>
			</tr>
			<tr>
				<td>图书管理员：</td>
				<td><input id="uname" readOnly="true" /></td>
			</tr>
			<tr>
				<td>藏书量：</td>
				<td><input id="collectBk" readOnly="true" /></td>
			</tr>
			<tr>
				<td>读者人数：</td>
				<td><input id="userNum" readOnly="true" /></td>
			</tr>
			<tr>
				<td>图书馆总面积：</td>
				<td><input id="area" readOnly="true" /></td>
			</tr>
			<tr>
				<td>联系方式：</td>
				<td><input id="lphone" readOnly="true" /></td>
			</tr>

			<tr>
				<td>联系地址：</td>
				<td><input id="laddress" readOnly="true" style="width:260px;" /></td>
			</tr>
		</table>
		<div class="l">
			<div class="e">图书馆简介</div>
			<textarea rows="18" cols="70" id="intro" readOnly="true"></textarea>
		</div>
	</form>
</body>
</html>
