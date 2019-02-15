<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>借书</title>

<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="../../js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerGrid.js"></script>
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
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerToolBar.js"></script>
<script src="../../js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script src="../../js/XHD.js" type="text/javascript"></script>
<script src="../../js/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("form").ligerForm();
		toolbar();
		xh();
	});
	var a = null;
	var url = null;
	function xh() {
		var a = $("#shuru").val();
		url = "findUid?a=" + a;
		$("#zhuti").ligerGrid(
				{
					columns : [
							{
								display : '序号',
								width : 50,
								render : function(item, index) {
									return (index + 1);
								}
							},
							{
								display : 'jid',
								name : 'jid',
								type : 'int',
								hide : true
							},
							{
								display : '图书名',
								name : 'bname'
							},
							{
								display : '读者',
								name : 'uname'
							},
							{
								display : '借书时间',
								name : 'jieTime'
							},
							{
								display : '应还时间',
								name : 'yingTime'
							},
							{
								display : '还书',
								name : 'active',
								render : function(rowd) {
									return '<a href="javascript:huan('
											+ rowd.jid + ')">' + rowd.active
											+ '</a>';
								}
							},
							{
								display : '续借',
								name : 'xu',
								render : function(rowd) {
									return '<a href="javascript:xu(' + rowd.jid
											+ ')">' + rowd.xu + '</a>';
								}
							} ],
					dataAction : 'local',//提交数据的方式：本地(local)或(server),选择本地方式时将在客服端分页、排序
					pageSize : 20,//每页默认的结果数
					pageSizeOptions : [ 20, 30, 50, 100 ],
					url : url,
					width : '100%',
					height : '100%',
					record : 'total',//数据源记录数字段名
					usePager : true,//是否分页
					enabledEdit : true, //是否允许编辑
					onContextmenu : function(parm, e) {
						actionCustomerID = parm.data.id;
						menu.show({
							top : e.pageY,
							left : e.pageX
						});
						return false;
					}
				});
		var aq = $("#zhuti").ligerGetGridManager();
		aq.GetDataByURL(url);
	}
	function f_reload() {
		var manager = $("#zhuti").ligerGetGridManager();
		manager.loadData(true);
		location.reload();
	};
	function huan(rowd) {
		$.ajax({
			url : "editBorbook",
			type : "POST",
			data : {
				jid : rowd
			},
			success : function(res) {
				if (res == "true") {
					top.$.ligerDialog.success('操作成功！');
					f_reload();
				}
			},
			error : function() {
				top.$.ligerDialog.error('操作失败！');
			}
		});
	}
	function xu(rowd) {
		$.ajax({
			url : "editXu",
			type : "POST",
			data : {
				xid : rowd
			},
			success : function(res) {
				if (res == "true") {
					top.$.ligerDialog.success('续借成功！');
					f_reload();
				} else {
					top.$.ligerDialog.warn('只能续借1次 ,请先还书！');
				}
			},
			error : function() {
				top.$.ligerDialog.error('操作失败！');
			}
		});
	}

	function toolbar() {
		$.getJSON("getButtons?menuId=2&rnd=" + Math.random(), function(data,
				textStatus) {
			var items = [];
			var arr = data.Items;
			for ( var i = 0; i < arr.length; i++) {
				arr[i].icon = "../" + arr[i].icon;
				items.push(arr[i]);
			}
			$("#toolbar").ligerToolBar({
				items : items
			});
			$("#zhuti").ligerGetGridManager().onResize();
		});
	}
	var activeDialog = null;
	function f_openWindow(url, title, width, height) {
		var dialogOptions = {
			width : width,
			height : height,
			title : title,
			url : url,
			buttons : [ {
				text : '关闭',
				onclick : function(item, dialog) {
					dialog.close();
				}
			} ],
			isResize : true,
			timeParmName : 'a'
		};
		activeDialog = parent.jQuery.ligerDialog.open(dialogOptions);
	}
	function add() {
		userid = $("#shuru").val();
		if (userid == "") {
			top.$.ligerDialog.warn('请输入读者编号！');
		} else {
			$.ajax({
				url : "findJid",
				post : "post",
				data : {
					userid : userid
				},
				success : function(res) {
					if (res == 0) {
						f_openWindow("cty/cty_BookManage.jsp?userid=" + userid,
								"添加借书", 980, 500);
					} else if (res < 5) {
						f_openWindow("cty/cty_BookManage.jsp?userid=" + userid,
								"添加借书", 980, 500);
					} else {
						top.$.ligerDialog.warn('每个人最多可借5本书！');
					}
				}
			});
		}
	}
	function cz() {
		a = $("#shuru").val();
		toolbar();
		xh();
		if (a == "") {
			top.$.ligerDialog.warn('请输入读者编号！');
		}
	}
	function gb() {
		a = $("#shuru").val();
		$.ajax({
			url : "findUserid",
			type : "POST",
			data : {
				userid : a
			},
			success : function(res) {
				if (res == "true") {
					cz();
				} else {
					alert("读者编号错误！");
					$("#shuru").val("");
				}
			}
		});
	}
</script>
<style type="text/css">
.btn1 {
	margin: 0 80px 0 30px;
	height: 30px;
	background: url('../../images/book/an.png') no-repeat;
	border: none;
	width: 75px;
}
</style>

</head>

<body>
	<form id="form1" onsubmit="return false" style="height:95%;">
		<div style="height:6%;margin-left:30px;border:1px solid #fff">

			<div style="float:left;margin:3px;">请输入读者编号：</div>

			<div style="float:left; ">
				<input type="text" id="shuru" name="shuru" onblur="gb()"
					ltype="text" style="width:160px;" />
			</div>
			<div style="float: left;">
				<input type="button" id="chazhao" class="btn1" name="chazhao"
					onclick="cz()">
			</div>
		</div>
		<div id="content">
			<div id="toolbar" style="margin:-30px 0 0 -8px;width:1115px;"></div>
			<div id="zhuti" style="margin:-8;padding:0;"></div>
		</div>
	</form>
</body>
</html>
