<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>权限管理</title>
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
<%
	int rid = new Integer(request.getParameter("rid"));
%>
<script type="text/javascript">
	var rname="<%=rid%>";
	$.getJSON("getAppAuthority?rid=" + rname, function(data) {
		$.each(data, function(index, item) {
			$("#content").append(
					"<label><input type='checkbox' value='"+item.appId+"' id='"+item.appId+"'/>"
							+ item.appName + "</label><br/>");
			if (item.boolean == "true")
				$("#" + item.appId).attr("checked", "checked");
		});
	});
	function f_update() {
		var label = new Array();
		var input = new Array();
		var length = 0;
		var data = "";
		label = $("label");
		input = $("input");
		for ( var i = 0; i < label.length; i++) {
			if ($(input[i]).attr("checked")) {
				data = data + "appId" + length + "=" + $(input[i]).attr("id")
						+ "&appName" + length + "=" + $(label[i]).text() + "&";
				length++;
			}
		}
		data = data + "length=" + length + "&rid=" + rname + "";
		return data;
	}
</script>
</head>

<body>
	<form id="form1" onsubmit="return false">
		<div id="content"></div>
		<div id="zhuti"></div>
	</form>
</body>
</html>
