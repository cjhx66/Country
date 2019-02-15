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
<script src="../../js/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDateEditor.js"
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
<script src="../../js/XHD.js" type="text/javascript"></script>
<script src="../../js/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(form1));
		$("form").ligerForm();
	});
	function f_save() {
		var title = $("#title").val();
		var content = $("#content").val();
		$.ajax({
			url : 'addPn',
			type : 'post',
			data : {
				content : content,
				title : title
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
textarea {
	border: 1px solid #AECAF0;
}
textarea:hover {
	border: 1px solid #9370db;
}

textarea:focus {
	border-color: #ffa500;
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
							style="width: 61px">公告名称：</div></td>
					<td height="23" class="style1">
						<div style="float:left; height: 20px;">
							<input type="text" id="title" name="title" ltype="text"
								style="width:300px;"
								validate="{required:true,maxlength:35,messages:{required:'请输入公告名'}}" />
						</div>

					</td>
				</tr>
				<tr>
					<td style="vertical-align:top">

						<div align="left" style="width: 62px">公告内容：</div>
					</td>
					<td><textarea rows="20" cols="46" id="content" name="content"
							ltype="text"
							validate="{required:true,messages:{required:'请输入内容'}}"></textarea></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
