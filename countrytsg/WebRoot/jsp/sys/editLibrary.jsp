<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="../../js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerRadio.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<script src="../../js/jquery-validation/jquery.validate.js"
	type="text/javascript"></script>
<script src="../../js/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script src="../../js/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/common.js" type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script src="../../js/XHD.js" type="text/javascript"></script>
<script src="../../js/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(form1));
		$("form").ligerForm();
		loadForm();
	});

	function loadForm() {
		$.ajax({
			type : "GET",
			url : 'getLibName',
			cache : false,
			contentType : "application/json; charset=utf-8",
			dataType : 'json',
			success : function(result) {
				var obj = eval(result);
				$("#lname").val(obj.lname);
				$("#area").val(obj.area);
				$("#lphone").val(obj.lphone);
				$("#laddress").val(obj.laddress);
				$("#intro").val(obj.intro);
			}
		});
	}

	function f_save() {
		if ($(form1).valid()) {
			var issave = $("form :input").fieldSerialize();
			/* issave += '&lnid=' + getparastr("lnid") */;
			$.ajax({
				url : "editLibName",
				type : "POST",
				data : issave,
				success : function(responseText) {
					if (responseText == "true") {
						parent.$.ligerDialog.close();
					} else {
						$.ligerDialog.error('操作失败！');
					}
				}
			});
		}
	}
</script>
<style type="text/css">
textarea {
	border: 1px solid #AECAF0;
}

textarea:hover {
	border: 1px solid #9370db;
}

textarea:focus {
	border-color: #ffa500;
}

td {
	height: 30px;
}
</style>
</head>

<body>
	<form id="form1" onsubmit="return false">
		<table style="float: left;">
			<tr>
				<td>图书馆名称：</td>
				<td colspan="3"><input type="text" id="lname" name="lname"
					ltype="text" style="width:448px;" /></td>
			</tr>
			<tr>
				<td>图书馆总面积：</td>
				<td><input id="area" type="text" name="area" ltype="text"
					style="width:175px;" /></td>
				<td>联系方式：</td>
				<td><input type="text" id="lphone" name="lphone" ltype="text"
					style="width:175px;" /></td>
			</tr>
			<tr>
				<td>联系地址：</td>
				<td colspan="3"><input type="text" id="laddress"
					name="laddress" ltype="text" style="width:448px;" /></td>
			</tr>
			<tr>
				<td>图书馆简介:</td>
				<td colspan="3"><textarea rows="14" cols="70" id="intro"
						name="intro" ltype="text"></textarea></td>
			</tr>
		</table>
	</form>

</body>
</html>
