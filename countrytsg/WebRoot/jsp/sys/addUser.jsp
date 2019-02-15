<%@page import="entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
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
	var type = "add";
	$(function() {
		$("#T_country").ligerComboBox({
			url : "getCountryJson",
			valueField : 'id',
			textField : 'text',
			selectBoxWidth : 180,
			autocomplete : true,
			width : 180,
			initValue : 1,
			selectBoxHeight : 100
		});

		$("#T_role").ligerComboBox({
			url : "getRoleJson",
			valueField : 'id',
			textField : 'text',
			selectBoxWidth : 180,
			autocomplete : true,
			width : 180,
			selectBoxHeight : 100
		});
	});
	$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(form1));
		$("form").ligerForm();
		$("#T_userid").ligerGetTextBoxManager().setDisabled();
		if (getparastr("uid")) {
			type = "edit";
			/* $("#T_userid").ligerGetTextBoxManager().setDisabled(); */
			$
					.ajax({
						url : "findUser",
						type : "GET",
						data : {
							uid : getparastr("uid"),
							rnd : Math.random()
						},
						contentType : "application/json;charset=utf-8",
						dataType : "json",
						success : function(result) {
							var obj = eval(result);
							$("#T_name").val(obj.uname);
							$("#T_userid").val(obj.userid);
							$("#T_Phone").val(obj.phone);
							$("#T_Email").val(obj.email);
							$("#T_Address").val(obj.address);
							$("#T_country").val(obj.cname);
							$("#T_country_val").val(obj.cid);
							$("#T_role").val(obj.rname);
							$("#T_role_val").val(obj.rid);
							if (obj.sex == "男") {
								$('input[name=T_sex][value="男"]').attr(
										"checked", true);
							} else {
								$('input[name=T_sex][value="女"]').attr(
										"checked", true);
							}
						}
					});
		} else {
			$.ajax({
				type : "GET",
				url : "getCs",
				contentType : "application/json;charset=utf-8",
				success : function(result) {
					$("#T_userid").val(result);
				}
			});
		}
	});
	function f_save() {
		if ($(form1).valid()) {
			var sendText = "&type=" + type;
			return $("form :input").fieldSerialize() + sendText;
		}
	}
</script>
<style type="text/css">
.style1 {
	text-align: left;
}
</style>
</head>
<body style="margin:5px 5px 5px 5px">
	<form id="form1" onsubmit="return false">
		<div>
			<table class="bodytable0" align="center" border="0" cellpadding="3"
				cellspacing="1" style="background: #fff; width:400px;">

				<tr>
					<td height="23" width="50px" class="style1"><div align="left">姓名：</div></td>
					<td height="23" class="style1">
						<div style="float:left; height: 20px;">
							<input type="text" id="T_name" name="T_name" ltype="text"
								ligerui="{width:180}"
								validate="{required:true,maxlength:25,messages:{required:'请输入姓名'}}" />

						</div>

					</td>
					<td>
						<div align="left" style="width: 50px">用户名：</div>
					</td>
					<td><input type="text" id="T_userid" name="T_userid"
						ligerui="{width:180}" ltype="text"
						validate="{required:true,messages:{required:'请输入用户名'}}" /></td>

				</tr>
				<tr>
					<td style="vertical-align:top">

						<div align="left" style="width: 50px">电话：</div>
					</td>
					<td><input type="text" id="T_Phone" name="T_Phone"
						ligerui="{width:180}" ltype="text"
						validate="{required:true,maxlength:25,messages:{required:'请输入电话'}}" /></td>
					<td style="vertical-align:top">

						<div align="left" style="width: 50px;left:5px;">邮箱：</div>
					</td>
					<td><input type="text" id="T_Email" name="T_Email"
						ligerui="{width:180}" ltype="text" /></td>

				</tr>
				<tr>
					<td style="vertical-align:top">

						<div align="left" style="width: 50px">性别：</div>
					</td>
					<td><input type="radio" class="T_sex" name="T_sex"
						style="margin-left: 40" value="男" checked="checked" />男 <input
						type="radio" id="T_sex" name="T_sex" style="margin-left: 20"
						value="女" />女</td>

					<td style="vertical-align:top">

						<div align="left" style="width: 50px">角色：</div>
					</td>
					<td><input type="text" id="T_role" name="T_role"
						ligerui="{width:180}"
						validate="{required:true,messages:{required:'请选择角色'}}" /></td>

				</tr>

				<tr>
					<td style="vertical-align:top">
						<div align="left" style="width: 50px">乡村：</div>
					</td>
					<td><input type="text" id="T_country" name="T_country"
						ligerui="{width:180}" /></td>
					<td style="vertical-align:top">
						<div align="left" style="width: 50px">地址：</div>
					</td>
					<td><input type="text" id="T_Address" name="T_Address"
						ligerui="{width:180}" ltype="text" /></td>

				</tr>
			</table>
		</div>
	</form>
</body>
</html>
