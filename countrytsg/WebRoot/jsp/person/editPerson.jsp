<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>修改个人信息</title>
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
			url : 'findPerson',
			cache : false,
			contentType : "application/json; charset=utf-8",
			dataType : 'json',
			success : function(result) {
				var obj = eval(result);
				$("#T_name").val(obj.uname);
				$("#T_Phone").val(obj.phone);
				$("#T_Address").val(obj.address);
				$("#T_Email").val(obj.email);
				if (obj.sex == "男") {
					$('input[name=T_sex][value="男"]').attr("checked", true);
				} else {
					$('input[name=T_sex][value="女"]').attr("checked", true);
				}
			}
		});
	}

	function f_save() {
		if ($(form1).valid()) {
			var issave = $("form :input").fieldSerialize();
			$.ajax({
				url : "editPerson",
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
</head>

<body>
	<form id="form1" onsubmit="return false">
		<div>
			<table align="center" border="0">

				<tr>
					<td>
						<div>姓名：</div>
					</td>
					<td><input type="text" id="T_name" name="T_name" ltype="text"
						ligerui="{width:160}"
						validate="{required:true,maxlength:25,messages:{required:'请输入姓名'}}" />
					</td>
				</tr>
				<tr>
					<td>

						<div>性别：</div>
					</td>
					<td><input type="radio" class="T_sex" name="T_sex"
						style="margin-left: 40" value="男" checked="checked" />男 <input
						type="radio" id="T_sex" name="T_sex" style="margin-left: 20"
						value="女" />女</td>
				</tr>
				<tr>
					<td>

						<div>电话：</div>
					</td>
					<td><input type="text" id="T_Phone" name="T_Phone"
						ligerui="{width:160}" ltype="text"
						validate="{required:true,maxlength:25,messages:{required:'请输入电话'}}" /></td>
				</tr>
				<tr>
					<td>

						<div>邮箱：</div>
					</td>
					<td><input type="text" id="T_Email" name="T_Email"
						ligerui="{width:160}" ltype="text" /></td>
				</tr>
				<tr>
					<td>
						<div>地址：</div>
					</td>
					<td><input type="text" id="T_Address" name="T_Address"
						ltype="text" ligerui="{width:160}" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
