<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员登录</title>

<link href="css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="js/jquery/jQuery.md5.js"></script>
<script>
	//获取时间
	function current() {
		var d = new Date();
		var str = "";
		str += d.getFullYear() + '-';//获取当前年份
		str += d.getMonth() + 1 + '-';//获取当前月份
		str += d.getDate() + '  ';
		str += d.getHours() + ':';
		str += d.getMinutes() + ':';
		str += d.getSeconds();
		return str;
	}
	$(function() {
		$("#uname").focus(function() {
			var username = $(this).val();
			if (username == '输入账号') {
				$(this).val('');
			}
		});

		$("#uname").focusout(function() {
			var username = $(this).val();
			if (username == '') {
				$(this).val('输入账号');
			}
		});

		$("#pwd").focus(function() {
			var pwd = $(this).val();
			if (pwd == '输入密码') {
				$(this).val('');
			}
		});
		$("#pwd").focusout(function() {
			var pwd = $(this).val();
			if (pwd == '') {
				$(this).val('输入密码');
			}
		});
		$("#valiCode").focus(function() {
			var valicode = $(this).val();
			if (valicode == '输入验证码') {
				$(this).val('');
			}
		});
		$("#valiCode").focusout(function() {
			var valicode = $(this).val();
			if (valicode == '') {
				$(this).val('输入验证码');
			}
		});
		//点击图片更换验证码
		$("#valiImg").click(
				function() {
					$(this).attr(
							"src",
							"img/SecurityImgAction?timestamp="
									+ new Date().getTime());
				});
		$("#send-btn").click(function() {
			var uname = $("#uname").val();
			var pwd = $("#pwd").val();
			var valiCode = $("#valiCode").val();
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : 'login',
				data : [ {
					name : 'userid',
					value : uname
				}, {
					name : 'pwd',
					value : $.md5(pwd)
				}, {
					name : 'valiCode',
					value : valiCode
				} ],
				success : function(result) {
					if (typeof (result == "number")) {
						switch (result) {
						case 0:
							alert("用户名或密码错误");
							$("#pwd").focus();
							break;
						case 1:
							location.href = decodeURIComponent("jsp/main.jsp");
							break;
						case 2:
							alert("验证码错误！");
							$("#valiCode").focus();
							break;
						}
					} else {
						alert('登陆失败,账号或密码有误!');
						$("#pwd").focus();
						return;
					}
				}
			});
		});
	});
</script>
</head>

<body>
	<div id="header">
		<!-- <div id="header_1"> -->
			<form method="post" class="adminform">
				<div>
					<img src="images/name.png" style="float: left;" /> <input
						type="text" name="uname" id="uname" class="i-text" value="输入账号" />
				</div>
				<div style="margin-top: 21px;">
					<img src="images/password.png" style="float: left;" /> <input
						type="password" name="pwd" id="pwd" class="i-text" value="输入密码" />
				</div>
				<div style="position:relative;top:12.5px;">
					<div style="float: left;">
						<input type="text" value="输入验证码" class="valiCode" name="valiCode"
							id="valiCode" />
					</div>
					<img src="img/SecurityImgAction?Math.random()" id="valiImg"
						alt="看不清，点击一下" style="margin-left:50px;" />
				</div>
				<div style="margin-top:30px;">
					<input type="button" value="登      录" id="send-btn" class="dl"
						style="float: left;"> <input type="reset" value="重      置"
						class="dl" style="margin-left:30px;">
				</div>
			</form>
		</div>
	<!-- </div> -->
</body>
</html>
