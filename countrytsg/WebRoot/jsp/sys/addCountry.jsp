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
<script src="../../js/jquery.form.js" type="text/javascript"></script>
<script src="../../js/XHD.js" type="text/javascript"></script>
<script type="text/javascript">
	var type = "add";
	$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(form1));
		$("form").ligerForm();
		if (getparastr("cid")) {
			loadForm(getparastr("cid"));
		}
	});
	function loadForm(cid) {
		type = "edit";
		$.ajax({
			url : "findCountry",
			type : "GET",
			data : {
				cid : cid,
				rnd : Math.random()
			},
			contentType : "application/json;charset=utf-8",
			dataType : "json",
			success : function(result) {
				var obj = eval(result);
				$("#c_name").val(obj.cname);
				$("#c_phone").val(obj.cphone);
				$("#c_address").val(obj.caddress);
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
	text-align: center;
}
</style>
</head>
<body style="margin:5px 5px 5px 5px">
	<form id="form1" onsubmit="return false">
		<div>
			<table class="bodytable0" align="center" border="0" cellpadding="3"
				cellspacing="1" style="background: #fff;">

				<tr>
					<td height="23" width="70px" class="style1"><div align="left"
							style="width: 61px">乡村名称：</div></td>
					<td height="23" class="style1">
						<div style="float:left; height: 20px;">
							<input type="text" id="c_name" name="c_name" ltype="text"
								ligerui="{width:180}"
								validate="{required:true,maxlength:25,remote:'isExist',messages:{required:'请输入乡村帐户名',remote:'乡村名已经存在!'}}" />
						</div>

					</td>
				</tr>
				<tr>
					<td style="vertical-align:center">

						<div align="left" style="width: 62px">联系方式：</div>
					</td>
					<td><input type="text" id="c_phone" name="c_phone"
						ligerui="{width:180}" ltype="text" /></td>

				</tr>
				<tr>
					<td style="vertical-align:center">

						<div align="left" style="width: 62px">所在地址：</div>
					</td>
					<td><input type="text" id="c_address" name="c_address"
						ligerui="{width:180}" ltype="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
