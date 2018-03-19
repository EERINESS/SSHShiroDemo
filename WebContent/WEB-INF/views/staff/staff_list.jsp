<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="easyui-layout">
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="wu-toolbar-1">
			<div class="wu-toolbar-button">
				<a href="javascript:;" download="a.jpg" id="a_dc"><span id="spanAutoClick"></span></a>
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="removeAll()" plain="true">批量删除</a> 
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a> 
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-download" onclick="exportt()" plain="true">导出</a>
			</div>
			<div class="wu-toolbar-search">
				<form id="wu-form-search" method="post">
					<label>用户名：</label><input id="ssname"  class="wu-text" style="width: 100px;height:20px"> 
					<label>性别：</label> 
						<select id="sssex" class="easyui-combobox" panelHeight="auto" style="width: 100px">
						<option value="">选择性别</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
					<label>省份：</label> 
						<select id="ssprovince">
							<option value="">请选择省份</option>
							<s:iterator value="provinces" var="province">
								<option value="${province.pId}">${province.pname} </option>
							</s:iterator>
						</select>
					<label>城市：</label><select id="sscity" style="width: 100px;height:20px"></select>
					<label>县镇：</label><select id="sscounty" style="width: 100px;height:20px"></select>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchStaff();">开始检索</a>
				</form>
			</div>
		</div>
		<!-- End of toolbar -->
		<table id="wu-datagrid-1" class="easyui-datagrid"
			toolbar="#wu-toolbar-1"></table>
	</div>

	<!--修改页面  Begin of easyui-dialog -->
	<div id="wu-dialog-1" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 400px; padding: 10px;">
		<form id="wu-form-1" method="post">
			<input type="hidden" name="staff.id" id="sid" />
			<table>
				<tr>
					<td width="60" align="right">姓 名：</td>
					<td><input type="text" name="staff.name" id="sname" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">年 龄：</td>
					<td><input type="text" name="staff.age" id="sage" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">性 别：</td>
					<td><input type="text" name="staff.sex" id="ssex" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">电 话：</td>
					<td><input type="text" name="staff.phone" id="sphone" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">省 份：</td>
					<td><select id="sprovince" name="staff.province" style="height:20px">
							<option value="0" selected="selected">请选择省份</option>
							<s:iterator value="provinces" var="province">
								<option value="${province.pId}">${province.pname} </option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">城 市：</td>
					<td><select id="scity" name="staff.city" style="width: 100px;height:20px"></select></td>
				</tr>
				<tr>
					<td valign="top" align="right">县 镇：</td>
					<td><select id="scounty" name="staff.county" style="width: 100px;height:20px"></select></td>
				</tr>
			</table>
		</form>
	</div>

	<!-- End of easyui-dialog -->
	<script type="text/javascript">
	
	$("#ssprovince").change(function () {
			var param = $("#ssprovince").val();
			$.ajax( {
				type : "post",
				url : "cityList",
				data : {"provinceid":param},
				cache : false,
				dataType : "json",
				success : function(json) {
					var str = "<option>请选择</option>";
					$("#sscity").html("");
					for ( var i = 0; i < json.length; i++) {
						str += "<option value='" + json[i].cId
								+ "'>" + json[i].cname
								+ "</option>";
					}
					$("#sscity").append(str);
					$("#sscounty").html("");
					$("#sscounty").append("<option>请选择</option>");
				},
				error : function() {
					alert("请与管理员联系");
				}
			});
		}); 
	 $("#sscity").change( 
		function () {
			var param = $("#sscity").val();
			$.ajax( {
				type : "post",
				url : "countyList",
				data : {"cityid":param},
				cache : false,
				dataType : "json",
				success : function(json) {
					var str = "<option>请选择</option>";
					$("#sscounty").html("");
					for ( var i = 0; i < json.length; i++) {
						str += "<option value='" + json[i].countyId
								+ "'>" + json[i].countyName
								+ "</option>";
					}
					$("#sscounty").append(str);
				},
				error : function() {
					alert("请与管理员联系");
				}
		});
	});
	 
	 $("#sprovince").change(function () {
			var param = $("#sprovince").val();
			$.ajax( {
				type : "post",
				url : "cityList",
				data : {"provinceid":param},
				cache : false,
				dataType : "json",
				success : function(json) {
					var str = "<option>请选择</option>";
					$("#scity").html("");
					for ( var i = 0; i < json.length; i++) {
						str += "<option value='" + json[i].cId
								+ "'>" + json[i].cname
								+ "</option>";
					}
					$("#scity").append(str);
					$("#scounty").html("");
					$("#scounty").append("<option>请选择</option>");
				},
				error : function() {
					alert("请与管理员联系");
				}
			});
		}); 
	 $("#scity").change( 
		function () {
			var param = $("#scity").val();
			$.ajax( {
				type : "post",
				url : "countyList",
				data : {"cityid":param},
				cache : false,
				dataType : "json",
				success : function(json) {
					var str = "<option>请选择</option>";
					$("#scounty").html("");
					for ( var i = 0; i < json.length; i++) {
						str += "<option value='" + json[i].countyId
								+ "'>" + json[i].countyName
								+ "</option>";
					}
					$("#scounty").append(str);
				},
				error : function() {
					alert("请与管理员联系");
				}
		});
	});
	
		/**
		 * Name 刷新页面
		 */
		function reload() {
			$('#wu-datagrid-1').datagrid('reload');
		}
		/*  操作标识，添加是为add，修改时为mod */
		var operType="";
		/**
		 * Name 添加 / 修改记录
		 */
		function edit() {
			$('#wu-form-1').form('submit', {
				url : 'staffEdit',
				success : function(data) {
					if (data) {
						if(operType=="add"){
		                	$.messager.alert('提示', '添加成功！', 'info');
		                	$('#wu-dialog-1').dialog('close');
							$('#wu-datagrid-1').datagrid('reload');
		            	}else{
		            		$.messager.alert('提示', '修改成功！', 'info');
		            		$('#wu-dialog-1').dialog('close');
							$('#wu-datagrid-1').datagrid('reload');
		            	}
						$('#wu-datagrid-1').datagrid('reload');
					} else {
						$.messager.alert('信息提示', '提交失败！', 'info');
					}
				}
			});
		}


		/**
		 * Name 删除单个记录
		 */
		function remove(id) {
			$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
				if (result) {
					$.ajax({
						url : 'staffDelete',
						data : {
							'staffid' : id
						},
						success : function(data) {
							if (data) {
								$.messager.alert('信息提示', '删除成功！', 'info');
								$('#wu-datagrid-1').datagrid('reload');
							} else {
								$.messager.alert('信息提示', '删除失败！', 'info');
							}
						}
					});
				}
			});
		}

		/**
		 * Name 删除多个记录
		 */
		function removeAll() {
			var items = $('#wu-datagrid-1').datagrid('getSelections');
			if(items==0){
				$.messager.alert("温馨提示","请至少勾选一项删除");
				return;
			}else{
				$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
					if (result) {
						
						var ids = [];
						$(items).each(function() {
							ids.push(this.id);
						});
						//alert(ids);return;
						$.ajax({
							url : 'staffDelete',
							type : 'post',
							traditional : true,//传数组进后台需要设置该属性
							data : {
								'ids' : ids
							},
							success : function(data) {
								if (data) {
									$.messager.alert('信息提示', '删除成功！', 'info');
									$('#wu-datagrid-1').datagrid('reload');
								} else {
									$.messager.alert('信息提示', '删除失败！', 'info');
								}
							}
						});
					}
				});
			}
			
		}

		/**
		 * Name 打开添加窗口
		 */
		function openAdd() {
			operType="add";
			$('#wu-form-1').form('clear');
			$('#wu-dialog-1').dialog({
				closed : false,
				modal : true,
				title : "添加信息",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : edit
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#wu-dialog-1').dialog('close');
					}
				} ]
			});
		}

		/**
		 * Name 打开修改窗口
		 */
		function openEdit() {
			$('#wu-form-1').form('clear');
			var item = $('#wu-datagrid-1').datagrid('getSelected'); // json对象
			/* 查询城市 */
			$.ajax( {
				type : "post",
				url : "cityList",
				data : {"provinceid":item.province},
				cache : false,
				dataType : "json",
				success : function(json) {
					var str = "";
					$("#scity").html("");
					for ( var i = 0; i < json.length; i++) {
						str += "<option value='" + json[i].cId
								+ "'>" + json[i].cname
								+ "</option>";
					}
					$("#scity").append(str);
					$("#scity").val(item.city);
				},
				error : function() {
					alert("请与管理员联系");
				}
			});
			/* end */
			
			/* 查询县镇 */
			$.ajax( {
				type : "post",
				url : "countyList",
				data : "cityid=" + item.city,
				cache : false,
				dataType : "json",
				success : function(json) {
					var str = "";
					$("#scounty").html("");
					for ( var i = 0; i < json.length; i++) {
						str += "<option value='" + json[i].countyId
								+ "'>" + json[i].countyName
								+ "</option>";
					}
					$("#scounty").append(str);
					$("#scounty").val(item.county);
				},
				error : function() {
					alert("请与管理员联系");
				}
			});
			/* end */
			

			//将选中的数据加载到修改页面中
			//$("#wu-form-1").form("load", item);
			$("#sid").val(item.id);
			$("#sname").val(item.name);
			$("#sage").val(item.age);
			$("#ssex").val(item.sex);
			$("#sphone").val(item.phone);
			$("#sprovince").val(item.province);

			$('#wu-dialog-1').dialog({
				closed : false,
				modal : true,
				title : "修改信息",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : edit
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#wu-dialog-1').dialog('close');
					}
				} ]
			});
		}

		/**
		 * Name 分页过滤器
		 */
		function pagerFilter(data) {
			if (typeof data.length == 'number'
					&& typeof data.splice == 'function') {// is array                
				data = {
					total : data.length,
					rows : data
				}
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
				onSelectPage : function(pageNum, pageSize) {
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh', {
						pageNumber : pageNum,
						pageSize : pageSize
					});
					dg.datagrid('loadData', data);
				}
			});
			if (!data.originalRows) {
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		}

		/**
		 * Name 导出数据
		 */
		function exportt() {
			$.messager.confirm('信息提示', '确定导出吗？', function(result) {
				if (result) {
					var items = $('#wu-datagrid-1').datagrid('getSelections');
					var name = $('#uuname').val();
					var place = $('#uuplace').val();
					var sex = $('#uusex').combobox('getValue');
					var birthday = $('#uubirthday').datebox('getValue');
					var ids = [];
					$(items).each(function() {
						ids.push(this.id);
					});
					$.ajax({
						url : 'userExportt',
						type : 'post',
						traditional : true,//传数组进后台需要设置该属性
						data : {
							'ids' : ids,
							'user.username' : name,
							'user.place' : place,
							'user.sex' : sex,
							'user.birthday' : birthday
						},
						success : function(data) {
							console.info(data);
							if (data != null) {
								document.getElementById("a_dc").href = "xls/"+ data;
								document.getElementById("a_dc").download = data;
								$("#spanAutoClick").click();
								$.messager.alert('信息提示', '导出成功！', 'info');
							} else {
								l$.messager.alert('信息提示', '导出失败！', 'info');
							}
						}
					});
				}
			});
		}

		/**
		 * Name 模糊查询数据
		 */
		function searchStaff() {
			var name = $('#ssname').val();
			var sex = $('#sssex').combobox('getValue');
			alert(sex);
			var province = $('#ssprovince').combobox('getValue');
			alert(province);
			var city = $('#sscity').combobox('getValue');
			alert(city);
			var county = $('#sscounty').combobox('getValue');
			alert(county);
			$('#wu-datagrid-1').datagrid('load', {
				'staff.name' : name,
				'staff.sex' : sex,
				'staff.province' : province,
				'staff.city' : city,
				'staff.county' : county
			});
		}

		/**
		 * Name 载入数据
		 */
		$('#wu-datagrid-1')
				.datagrid(
						{
							url : 'staffList',
							loadFilter : pagerFilter,
							rownumbers : true,
							singleSelect : false,
							pageSize : 10,
							pagination : true,
							multiSort : true,
							fitColumns : true,
							fit : true,
							columns : [ [
									{
										checkbox : true
									},
									{
										field : 'id',
										title : '编号',
										width : 60,
										sortable : true
									},
									{
										field : 'name',
										title : '用户名',
										width : 100,
										sortable : true
									},
									{
										field : 'age',
										title : '年龄',
										width : 80
									},
									{
										field : 'sex',
										title : '性别',
										width : 100
									},
									{
										field : 'phone',
										title : '电话',
										width : 100
									},
									{
										field : 'pname',
										title : '省份',
										width : 100
									},
									{
										field : 'cname',
										title : '城市',
										width : 100
									},
									{
										field : 'coname',
										title : '县镇',
										width : 100
									},
									{
										field : '_operate',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, rows, index) {
											var str = '';
											str += '<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="openEdit();" >编辑</a>';
											str += '<a href="javascript:void(0)" class="user-easyui-linkbutton-remove" data-options="plain:true,iconCls:\'icon-remove\'"  onclick="remove('+rows.id+');">删除</a>';
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.user-easyui-linkbutton-edit').linkbutton({
									text : '编辑'
								});
								$('.user-easyui-linkbutton-remove').linkbutton(
										{
											text : '删除'
										});
							},
						});
		
		
			
	</script>
  </body>
</html>