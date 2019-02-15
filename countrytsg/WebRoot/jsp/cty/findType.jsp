<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>类型查找</title>
<link href="../../css/nbcss/bm.css" rel="stylesheet" type="text/css">
<link href="../../js/ligerUI/skins/ext/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="../../css/input.css" rel="stylesheet" type="text/css">
<script src="../../js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script type="text/javascript"
	src="../../js/ligerUI/js/plugins/ligerGrid.js"></script>
<script type="text/javascript">
	var url = null;
	$(function() {
		$
				.ajax({
					type : 'post',
					url : "getBTJson",
					contentType : "application/json;charset=utf-8",
					dataType : 'json',
					success : function(res) {
						var obj = eval(res);
						for ( var i = 0; i < obj.length; i++) {
							var tname = obj[i].text;
							$("#types").append(
									'<div class="cx">'
											+ '<a href="javascript:cx('
											+ obj[i].id + ')">' + tname
											+ '</a></div>');
						}
					}
				});
	});
	function cx(op) {
		var tid = op;
		url = "findTid?tid=" + tid;
		$("#zhuti").ligerGrid({
			columns : [ {
				display : '序号',
				width : 50,
				render : function(item, index) {
					return (index + 1);
				}
			}, {
				display : '图书编号',
				name : 'bookid',
				width : '11%'
			}, {
				display : '图书名',
				name : 'bname',
				width : '16%'
			}, {
				display : 'ISBN',
				name : 'ISBN',
				width : '16%'
			}, {
				display : '作者',
				name : 'author',
				width:'9%'
			}, {
				display : '出版社',
				name : 'press'
			}, {
				display : '数量',
				name : 'num',
				width:'6%'
			}, {
				display : '价格',
				name : 'price',
				width:'8%'
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
			onContextmenu : function(parm, e) {
				actionCustomerID = parm.data.id;
				menu.show({
					top : e.pageY,
					left : e.pageX
				});
				return false;
			}
		});
		a = $("#zhuti").ligerGetGridManager();
		a.GetDataByURL(url);
	}
</script>
<style type="text/css">
.cx {
	width: 100px;
	height: 20px;
	float: left;
	margin: 10px;
	text-align: center;
	border: 1px dotted #C9C7DC;
}

.cx a {
	font-size: 18px;
	color: black;
	text-decoration: none;
}

.cx a:hover {
	color: #FA7350;
}

.cx a:focus {
	color: #C9C7DC;
}

#types {
	width: 100%;
	height: 20%;
	/* border:1px solid red; */
}
</style>
</head>

<body>
	<div id="types">
		<a href=""></a>
	</div>
	<div id="zhuti">
		<div id="ts"></div>
	</div>
</body>
</html>
