<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图书管理</title>
<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css">
<link href="../../css/nbcss/bm.css" rel="stylesheet" type="text/css">
<link href="../../css/input.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerComboBox.js"></script>
<script src="../../js/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerRadio.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="../../js/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerGrid.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerDialog.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerDrag.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerToolBar.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerTip.js"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerMenu.js"></script>
<script type="text/javascript" src="../../js/XHD.js"></script>
<script type="text/javascript">
	var url = null;
	var ids = 0;
	var menuId = getparastr("menuId");
	var url = null;
	var type = null;
	$(function() {
		$("form").ligerForm();
		if (getparastr("userid")) {
			type = "add";
			var uid = getparastr("userid");
			userid = uid;
			show();
		}
		urls();
		xh();
		toolbar();
	});
	function urls() {
		if (menuId == 8) {
			ids = menuId;
			$("#url").val("getBM");
		} else if (menuId == 9) {
			ids = menuId;
			$("#url").val("findTimes");
		}
		$("#xuanzhe").ligerComboBox({
			selectBoxHeight : 80,
			data : [ {
				text : '图书名',
				id : 1
			}, {
				text : 'ISBN',
				id : 2
			}, {
				text : '作者',
				id : 3
			}, {
				text : '出版社',
				id : 4
			} ],
			onSelected : function(value) {
				if (value == 1) {
					$("#url").val("findBook");
				} else if (value == 2) {
					$("#url").val("findISBN");
				} else if (value == 3) {
					$("#url").val("findAuthor");
				} else {
					$("#url").val("findPress");
				}
				$("#shuru").val("");
			}
		});
	}
	function xh() {
		url = $("#url").val();
		shuru = $("#shuru").val();
		url = url + "?shuru=" + encodeURI(encodeURI(shuru));
		$("#zhuti").ligerGrid(
				{
					columns : [
							{
								display : '序号',
								width : 40,
								render : function(item, index) {
									return (index + 1);
								}
							},
							{
								display : 'bid',
								name : 'bid',
								hide : true,
								type : 'int'
							},
							{
								display : '乡村',
								name : 'cname',
								width : '6%'
							},
							{
								display : '图书编号',
								name : 'bookid',
								width : '11%'
							},
							{
								display : '图书名',
								name : 'bname',
								width : '15%'
							},
							{
								display : 'ISBN',
								name : 'ISBN',
								width : '10%'
							},
							{
								display : '类型',
								name : 'btype',
								width : '6%'
							},
							{
								display : '作者',
								name : 'author',
								width : '6%'
							},
							{
								display : '出版社',
								name : 'press',
								width : '12%'
							},
							{
								display : '价格',
								name : 'price',
								width : '5%'
							},
							{
								display : '数量',
								name : 'num',
								width : '4%'
							},
							{
								display : '入库时间',
								name : 'addtime',
								width : '9%'
							},
							{
								display : '操作员',
								name : 'uname',
								width : '6%'
							},
							{
								display : '操作',
								name : 'jie',
								width : '6%',
								render : function(rowd) {
									if (type != null) {
										return '<a href="javaScript:jie('
												+ rowd.bid + ')">' + rowd.jie
												+ '</a>';
									} else {
										return '<div>无</div>';
									}
								}
							} ],
					dataAction : 'local',
					pageSize : 20,
					pageSizeOptions : [ 20, 30, 50, 100 ],
					url : url,
					width : '100%',
					height : '100%',
					heightDiff : -1,
					record : 'total',
					usePage : true,
					enabledEdit : true,
					checkbox : true,//是否有多选框
					onContextmenu : function(parm, e) {
						actionCustomerID = parm.data.id;
						menu.show({
							top : e.pageY,
							left : e.pageX
						});
						return false;
					}
				});
		var a = $("#zhuti").ligerGetGridManager();
		a.GetDataByURL(url);
	}
	function toolbar() {
		urls();
		$.getJSON("getButtons?menuId=" + ids + "&rnd=" + Math.random(),
				function(data, textStatus) {
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
	function f_reload() {
		var manager = $("#zhuti").ligerGetGridManager();
		manager.loadData(true);
		location.reload();
	};
	var activeDialog = null;
	function f_openWindow(url, title, width, height) {
		var dialogOptions = {
			width : width,
			height : height,
			title : title,
			url : url,
			buttons : [ {
				text : '保存',
				onclick : function(item, dialog) {
					f_save(item, dialog);
				}
			}, {
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
	function f_openWindow1(url, title, width, height) {
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
		f_openWindow("cty/addBookManage.jsp", "新增图书", 740, 330);
	}
	/* function types() {
		f_openWindow1("cty/findType.jsp", "类型查找", 750, 500);
	} */
	function show() {
		if ($("#show").css("display") == 'none') {
			$("#show").css("display", "block");
			$("form").ligerForm();
		} else {//如果show是显示的
			$("#show").css("display", "none");
		}
	}
	function sousuo() {
		var xz = $("#xuanzhe").val();
		var shuru = $("#shuru").val();
		if (xz == '' && shuru == '') {
			top.$.ligerDialog.warn('请选择输入内容！');
		} else {
			xh();
		}
	}
	function jie(OP) {
		$.ajax({
			url : "findNum",
			type : "POST",
			data : {
				bid : OP,
			},
			success : function(res) {
				if (res > 0) {
					$.ajax({
						url : "addBor",
						type : "POST",
						data : {
							bid : OP,
							userid : userid
						},
						success : function(res) {
							if (res == "true") {
								alert("操作成功！请手动刷新");
								parent.$.ligerDialog.close();
							}
						},
						error : function() {
							top.$.ligerDialog.error('操作失败！');
						}
					});
				} else {
					alert("图书已借出!");
					top.$.ligerDialog.warn('图书已借出！');
					parent.$.ligerDialog.close();
				}
			},
			error : function() {
				top.$.ligerDialog.error('操作失败！');
			}
		});
	}
	function edit() {
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		var c = $("#c").text();
		if (row) {
			var d = row.cname;
			if (c == d) {
				url = "cty/addBookManage.jsp?bid=" + row.bid;
				f_openWindow(url, "修改图书信息", 750, 330);
			} else {
				$.ligerDialog.warn('无权限修改！');
			}
		} else {
			$.ligerDialog.warn('请选择行！');
		}
	}

	function f_save(item, dialog) {
		var manager = $("#zhuti").ligerGetGridManager();
		var row = manager.getSelectedRow();
		var issave = dialog.frame.f_save();
		var type = issave.slice(issave.lastIndexOf("=") + 1, issave.length);
		var url = type == "add" ? "addBM" : "editBM?bid=" + row.bid;
		if (issave) {
			dialog.close();
			/* $.ligerDialog.waitting('数据保存中,请稍候...'); */
			$.ajax({
				url : url,
				type : "POST",
				data : issave,
				success : function(responseText) {
					$.ligerDialog.closeWaitting();
					$.ligerDialog.success('操作成功！');
					f_reload();
				},
				error : function() {
					$.ligerDialog.closeWaitting();
					$.ligerDialog.error('操作失败！');
				}
			});

		}
	}
	function del() {
		var m = $("#zhuti").ligerGrid();
		var getid = m.getCheckedRows();

		var cx = $("#c").text();

		var str = "";
		var s = "";

		$(getid).each(function() {
			str += this.bid + ",";
			s += this.cname + " ";
		});
		if (str != "") {
			var b = null;
			var c = s.split(" ");
			var arr = new Array();
			for ( var i = 0; i < c.length; i++) {
				arr.push(c[i]);
			}
			for ( var j = 0; j < arr.length - 1; j++) {
				if (arr[j] != cx) {
					b = false;
					j++;
				}
			}
			if (b != null) {
				$.ligerDialog.warn('无权限修改！');
			} else {
				$.ligerDialog.confirm("信息删除后不能恢复，\n您确定移除？", function(yes) {
					if (yes) {
						$.ajax({
							url : 'delBM',
							type : 'post',
							dataType : 'json',
							data : [ {
								name : 'bids',
								value : str
							} ],
							success : function(responseText) {
								if (typeof (responseText) == "number") {
									if (responseText == 1) {
										top.$.ligerDialog.success('操作成功！');
										location.reload();
									} else {
										top.$.ligerDialog.error('删除失败！', "",
												null, 9003);
									}
								}
							},
						});
					}
				});
			}
		} else {
			$.ligerDialog.warn("请选择行!");
		}
	}
</script>
<%
	String cname = null;
	cname = request.getSession().getAttribute("country").toString();
%>
</head>

<body>
	<form id="mainform" onsubmit="return false">
		<div>
			<div id="toolbar"></div>
			<div id="c" style="display: none;"><%=cname%></div>
			<div id="show" style="display: none;">
				<div style="float: left;">
					<input type="text" id="xuanzhe" name="xuanzhe">
				</div>
				<div style="float: left;margin-left:25px;">
					<input type="text" id="shuru" name="shuru" ltype="text"
						style="width:160px;" />
				</div>
				<div id="an">
					<input type="button" id="chazhao" class="btn1" name="chazhao"
						onclick="sousuo()"><input type="text" id="url"
						style="display: none;" />
				</div>
			</div>
			<div id="zhuti" style="margin: -1px;"></div>
		</div>
	</form>
</body>
</html>
