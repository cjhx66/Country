<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>上传资源</title>
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
<script src="../../js/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/common.js" type="text/javascript"></script>
<script src="../../js/jquery-validation/jquery.validate.js"
	type="text/javascript"></script>
<script src="../../js/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script src="../../js/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="../../js/XHD.js" type="text/javascript"></script>
<script src="../../js/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(form1));
		$("form").ligerForm();
	});
	function f_save() {
		if ($(form1).valid()) {
			$("#form1").ajaxSubmit({
				type : 'post',
				url : 'addEbook',
				success : function(data) {
					if (data == "true") {
						parent.$.ligerDialog.close();
					} else {
						alert("上传失败！");
					}
				}
			});
		}
	}
</script>
</head>

<body>
	<form enctype="multipart/form-data" id="form1" onsubmit="return false"
		method="post">
		<table align="center" border="0">
			<tr>
				<td><label style="width:140px;">选择资源:</label></td>
				<td><input type="file" name="wen"
					style="height:30px;width:180px;" /></td>
			</tr>
			<tr>
				<td><label style="width:140px;">图书名称:</label></td>
				<td><input type="text" id="bname" name="bname" ltype="text"
					ligerui="{width:180}"
					validate="{required:true,maxlength:25,messages:{required:'请输入图书名'}}"
					style="width:180px;" /></td>

			</tr>
		</table>

	</form>
</body>
</html>
