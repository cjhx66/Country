<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加挂失</title>
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
<script src="../../js/ligerUI/js/plugins/ligerTip.js"
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
	function gb() {
		var a = $("#userid").val();
		$.ajax({
			url : "findUserid",
			type : "POST",
			data : {
				userid : a
			},
			success : function(res) {
				if (res == "false") {
					alert("读者编号错误！");
					$("#userid").val("");
				} else {
					a = $("#userid").val();
				}
			}
		});
	}
	function gh() {
		var shuru = $("#bname").val();
		$.ajax({
			url : "findBn",
			type : "POST",
			data : {
				shuru : shuru
			},
			success : function(res) {
				if (res == "false") {
					top.$.ligerDialog.warn('没有此图书！');
					$("#bname").val("");
				} else {
					shuru = $("#bname").val();
				}
			}
		});
	}
	$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(form1));
		$("form").ligerForm();
		if (getparastr("gid")) {
			loadForm(getparastr("gid"));
		}
	});

	function loadForm(gid) {
		type = "edit";
		$.ajax({
			url : "findCompen",
			type : "GET",
			data : {
				gid : gid,
				rnd : Math.random()
			},
			contentType : "application/json;charset=utf-8",
			dataType : "json",
			success : function(res) {
				var obj = eval(res);
				$("#userid").val(obj.userid);
				$("#bname").val(obj.bname);
				$("#sprice").val(obj.sprice);
			}
		});
	}

	function f_save() {
		if ($(form1).valid()) {
			var sendText = "&type=" + type;
			var userid = "userid=" + $("#userid").val();
			var bname = "&bname=" + $("#bname").val();
			var sprice = "&sprice=" + $("#sprice").val();
			return userid + bname + sprice + sendText;
		}
	}
</script>
</head>

<body>
	<form id="form1">
		<table align="center" border="0">
			<tr>
				<td>读者编号:</td>
				<td><input type="text" id="userid" name="userid" ltype="text"
					ligerui="{width:160}" onblur="gb()"
					validate="{required:true,messages:{required:'请输入读者编号'}}"></td>
				<td><label style="color:#A9A9A9;size:10px;">例：1-0001</label></td>
			</tr>
			<tr>
				<td>图书编号:</td>
				<td><input type="text" id="bname" name="bname" ltype="text"
					ligerui="{width:160}" onblur="gh()"
					validate="{required:true,messages:{required:'请输入图书名！'}}"></td>
				<td><label style="color:#A9A9A9">例：TS001LX0010001</label></td>
			</tr>
			<tr>
				<td>赔偿金额:</td>
				<td><input type="text" id="sprice" name="sprice" ltype="text"
					ligerui="{width:160}"></td>
			</tr>
		</table>

	</form>
</body>
</html>
