<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>查看公告</title>

<link href="../../css/nbcss/showPn.css" rel="stylesheet"
	type="text/css" />
<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="../../js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDrag.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerMenu.js"
	type="text/javascript"></script>
<script src="../../js/XHD.js" type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<script src="../../js/jquery-validation/jquery.validate.js"
	type="text/javascript"></script>
<script src="../../js/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerRadio.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$.metadata.setType("attr", "validate");
		XHD.validate($(ys));
		$("div").ligerForm();
		if (getparastr("pnid")) {
			loadForm(getparastr("pnid"));
		}
	});

	function loadForm(oaid) {
		$.ajax({
			type : "GET",
			url : "findPn",
			data : {
				id : oaid,
				rnd : Math.random()
			},
			contentType : "application/json;charset=utf-8",
			dataType : "json",
			success : function(result) {
				var obj = eval(result);
				$("#title").text(obj.title);
				$("#userid").text("发送人："+obj.uname);
				$("#time").text("发布时间："+obj.time);
				$("#content1").text(obj.content);
				$("#num").text("浏览次数："+obj.num);
			}
		});
	}
</script>
<style type="text/css">
textarea{
	border:0;
}
</style>
</head>
<body style="text-align: center;">
	<div id="ys">
		<div id="title"></div>
		<div id="rests">
			<div id="userid"></div>
			<div id="time"></div>
		</div>
		<div id="content"><textarea rows="25" cols="92" id="content1"></textarea></div>
		
		<div id="num"></div>
	</div>
</body>
</html>
