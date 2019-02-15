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
	var type = "add";
	$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(form1));
		$("form").ligerForm();
	});

	function f_save() {
		var tname = $("#tname").val();
		$.ajax({
			url : 'addBT',
			type : 'post',
			data : {
				tname : tname
			},
			success : function(res) {
				if (res == "true") {
					parent.$.ligerDialog.close();
					alert("发送成功");
				} else {
					$.ligerDialog.error('发送失败！请重新发送');
				}
			},
			error : function() {
			}
		});
	}
</script>
<style type="text/css">
.style1 {
	text-align: left;
}

body {
	margin: 5px 5px 5px 5px;
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
							style="width: 61px">图书类型：</div></td>
					<td height="23" class="style1">
						<div style="float:left; height: 20px;">
							<input type="text" id="tname" name="tname" ltype="text"
								ligerui="{width:140}"
								validate="{required:true,maxlength:25,messages:{required:'请输入图书类型',remote:'此类型已经存在!'}}" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
