<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
	type = "add";
	$(function() {
		$("#tname").ligerComboBox({
			url : "getBTJson",
			valueField : 'id',
			textField : 'text',
			selectBoxWidth : 180,
			autocomplete : true,
			width : 180,
			selectHeight : 70
		});
		$(function() {
			$.metadata.setType("attr", "validate");
			XHD.validate($(form1));
			$("form").ligerForm();
			if (getparastr("bid")) {
				loadForm(getparastr("bid"));
			}
		});

		function loadForm(bid) {
			type = "edit";
			$.ajax({
				url : "findBM",
				type : "GET",
				data : {
					bid : bid,
					rnd : Math.random()
				},
				contentType : "application/json;charset=utf-8",
				dataType : "json",
				success : function(result) {
					var obj = eval(result);
					$("#bname").val(obj.bname);
					$("#tname").val(obj.tname);
					$("#tname_val").val(obj.tid);
					$("#ISBN").val(obj.ISBN);
					$("#author").val(obj.author);
					$("#press").val(obj.press);
					$("#price").val(obj.price);
					$("#num").val(obj.num);
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
</head>

<body>
	<form id="form1" onsubmit="return false">
		<div>
			<table class="bodytable0" align="center" border="0" cellpadding="3"
				cellspacing="1" style="background: #fff;">

				<tr>
					<td height="40" width="70px" class="style1">
						<div align="left" style="width: 70px">图书名称：</div>
					</td>
					<td height="40" class="style1" colspan="3">
						<div style="float:left; height: 20px;">
							<input type="text" id="bname" name="bname" ltype="text"
								style="width:450px;"
								validate="{required:true,maxlength:25,messages:{required:'请输入图书名'}}" />
						</div>
					</td>
				</tr>
				<tr>
					<td style="vertical-align:top">

						<div align="left" style="width: 70px">ISBN：</div>
					</td>
					<td><input type="text" id="ISBN" name="ISBN"
						ligerui="{width:180}" ltype="text" validate="{required:true}" /></td>
					<td style="vertical-align:top">

						<div align="left" style="width: 70px;left:5px;">作者：</div>
					</td>
					<td><input type="text" id="author" name="author"
						ligerui="{width:180}" ltype="text" /></td>

				</tr>
				<tr>
					<td style="vertical-align:top">

						<div align="left" style="width: 70px">出版社：</div>
					</td>
					<td><input type="text" id="press" name="press"
						ligerui="{width:180}" ltype="text" /></td>
					<td style="vertical-align:top">
						<div align="left" style="width: 70px">价格：</div>
					</td>
					<td><input type="text" id="price" name="price"
						ligerui="{width:180}" ltype="text" validate="{required:true}"
						onkeyup="this.value=this.value.replace(/\D/g,'')"
						onafterpaste="this.value=this.value.replace(/\D/g,'')"
						maxlength="5" /></td>
				</tr>

				<tr>
					<td style="vertical-align:top">

						<div align="left" style="width: 70px">数量：</div>
					</td>
					<td><input type="text" id="num" name="num"
						ligerui="{width:180}" ltype="text" validate="{required:true}"
						onkeyup="this.value=this.value.replace(/\D/g,'')"
						onafterpaste="this.value=this.value.replace(/\D/g,'')"
						maxlength="3" /></td>

					<td style="vertical-align:top">

						<div align="left" style="width: 70px">类型：</div>
					</td>
					<td><input type="text" id="tname" name="tname"
						ligerui="{width:180}" ltype="text"
						validate="{required:true,messages:{required:'请选择类型'}}" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
