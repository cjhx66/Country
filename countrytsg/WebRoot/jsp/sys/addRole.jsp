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
	var type="add";
	$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(form1));
		$("form").ligerForm();
		if (getparastr("rid")) {
			loadForm(getparastr("rid"));
		}

	});

	function loadForm(rid) {
		type = "edit";
		$.ajax({
			url : "findRole",
			type : "GET",
			data : {
				rid : rid,
				rnd : Math.random()
			},
			contentType : "application/json;charset=utf-8",
			dataType : "json",
			success : function(result) {
				var obj = eval(result);
				$("#r_name").val(obj.rname);
				$("#r_desc").val(obj.rdesc);
			}
		});
	}
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
body{
	margin:5px 5px 5px 5px;
}
</style>
</head>
<body>
	<form id="form1" onsubmit="return false">
		<div>
			<table class="bodytable0" align="center" border="0" cellpadding="3"
				cellspacing="1" style="background: #fff;">

				<tr>
					<td height="23" width="70px" class="style1"><div align="left"
							style="width: 61px">角色名称：</div></td>
					<td height="23" class="style1">
						<div style="float:left; height: 20px;">
							<input type="text" id="r_name" name="r_name" ltype="text"
								ligerui="{width:180}"
								validate="{required:true,maxlength:25,remote:'isExist',messages:{required:'请输入角色名',remote:'角色名已经存在!'}}" />


						</div>

					</td>
				</tr>
				<tr>
					<td style="vertical-align:top">

						<div align="left" style="width: 62px">角色说明：</div>
					</td>
					<td><input type="text" id="r_desc" name="r_desc"
						ligerui="{width:180}" ltype="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
