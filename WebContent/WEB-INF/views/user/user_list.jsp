<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="wu-toolbar-2">
			<div class="wu-toolbar-button">
				<a href="javascript:;" download="a.jpg" id="a_dc"><span id="spanAutoClick"></span></a>
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="removeAll()" plain="true">批量删除</a> 
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a> 
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-download" onclick="exportt()" plain="true">导出</a>
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-upload" onclick="importExcel()" plain="true">导入</a>
			</div>
			<div class="wu-toolbar-search">
				<form id="wu-form-1" method="post">
					<label>用户名：</label><input id="uuname" class="wu-text"
						style="width: 100px;height:20px"> <label>来自：</label><input
						id="uuplace" class="wu-text" style="width: 100px;height:20px"> <label>出生年月：</label><input
						id="uubirthday" class="easyui-datebox" style="width: 100px">
					<label>性别：</label> <select id="uusex" class="easyui-combobox"
						panelHeight="auto" style="width: 100px" >
						<option value="">选择性别</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select> <a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="searchUser();">开始检索</a>
				</form>
			</div>
		</div>
		<!-- End of toolbar -->
		<table id="wu-datagrid-2" class="easyui-datagrid"
			toolbar="#wu-toolbar-2"></table>
	</div>

	<!--修改页面  Begin of easyui-dialog -->
	<div id="wu-dialog-2" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 400px; padding: 10px;">
		<form id="wu-form-2" method="post">
			<input type="hidden" name="user.id" id="uid" />
			<table>
				<tr>
					<td width="60" align="right">姓 名：</td>
					<td><input type="text" name="user.username" id="uname" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">密 码：</td>
					<td><input type="text" name="user.password" id="upassword" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">生 日：</td>
					<td><input  name="user.birthday" 
						id="ubirthday" class="easyui-datebox" style="width: 200px"></td>
				</tr>
				<tr>
					<td align="right">性 别：</td>
					<td><input type="text" name="user.sex" id="usex" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">电 话：</td>
					<td><input type="text" name="user.tel" id="utel" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td valign="top" align="right">来 自：</td>
					<td><input type="text" name="user.place" id="uplace" style="height:20px"
						class="wu-text" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<!-- 导入数据窗口 -->
	<div id="formExcel" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px;height:135px; padding:10px;">
		<form method="post" id="formexcel" enctype="multipart/form-data">
	        <table>
	            <tr>
	                <td width="60" align="right"><font size="1px">选择文件:</font></td>
	                <td>
	                	<input id="uploadFile" type="file" name="uploadFile" style="width: 90%"/>
	                </td>
	                <td width="100" align="right">
	                	<a href="downloadTemplate?fileName=users.xlsx" class="easyui-linkbutton">下载模板</a>
	                </td>
	               
	            </tr>
	         </table>
	   </form>
	</div>
	
	<!-- End of easyui-dialog -->
	<script type="text/javascript">
	
		/**
		 * Name 刷新页面
		 */
		function reload() {
			$('#wu-datagrid-2').datagrid('reload');
		}

		/**
		 * Name 添加记录
		 */
		function add() {
			$('#wu-form-2').form('submit', {
				url : 'userAdd',
				success : function(data) {
					if (data) {
						$.messager.alert('信息提示', '提交成功！', 'info');
						$('#wu-dialog-2').dialog('close');
						$('#wu-datagrid-2').datagrid('reload');
					} else {
						$.messager.alert('信息提示', '提交失败！', 'info');
					}
				}
			});
		}

		/**
		 * Name 修改记录
		 */
		function edit() {
			$('#wu-form-2').form('submit', {
				url : 'userUpdate',
				success : function(data) {
					if (data) {
						$.messager.alert('信息提示', '提交成功！', 'info');
						$('#wu-dialog-2').dialog('close');
						$('#wu-datagrid-2').datagrid('reload');
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
						method:'post',
						url : 'userDelete',
						data : {'ided' : id},
						success : function(data) {
							if (data) {
								$.messager.alert('信息提示', '删除成功！', 'info');
								$('#wu-datagrid-2').datagrid('reload');
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
			var items = $('#wu-datagrid-2').datagrid('getSelections');
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
							url : 'userDelete',
							type : 'post',
							traditional : true,//传数组进后台需要设置该属性
							data : {
								'ids' : ids
							},
							success : function(data) {
								if (data) {
									$.messager.alert('信息提示', '删除成功！', 'info');
									$('#wu-datagrid-2').datagrid('reload');
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
			$('#wu-form-2').form('clear');
			$('#wu-dialog-2').dialog({
				closed : false,
				modal : true,
				title : "添加信息",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : add
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#wu-dialog-2').dialog('close');
					}
				} ]
			});
		}

		/**
		 * Name 打开修改窗口
		 */
		function openEdit() {
			$('#wu-form-2').form('clear');
			var item = $('#wu-datagrid-2').datagrid('getSelected'); // json对象
			//将选中的数据加载到修改页面中
			//$("#wu-form-2").form("load", item);
			$("#uid").val(item.id);
			$("#uname").val(item.username);
			$("#upassword").val(item.password);
			$('#ubirthday').datebox('setValue', item.birthday);
			$("#usex").val(item.sex);
			$("#utel").val(item.tel);
			$("#uplace").val(item.place);

			$('#wu-dialog-2').dialog({
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
						$('#wu-dialog-2').dialog('close');
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
		 * Name 打开导入窗口
		 */
		function importExcel() {
			$('#formExcel').dialog({
				closed: false,
				modal:true,
			     title: "导入数据",
			     buttons: [{
		         text: '确定',
		         iconCls: 'icon-ok',
		         handler: loead
		     }, {
		         text: '取消',
		         iconCls: 'icon-cancel',
		         handler: function () {
		             $('#formExcel').dialog('close');                    
		         }
		     }]
		 });
		}
		/**
		 * Name 上传 excel, 实现上传
		 */
		function loead(){
			$('#formexcel').form('submit', {
				url:'userImport',
				success:function(data){
					if(data){
						$.messager.alert('信息提示','提交成功！','info');
						$('#wu-datagrid-2').datagrid('reload');
						$('#formExcel').dialog('close');
					}
					else
					{
						$.messager.alert('信息提示','提交失败！','info');
					}
				}
			});
		}

		/**
		 * Name 导出数据
		 */
		function exportt() {
			$.messager.confirm('信息提示', '确定导出吗？', function(result) {
				if (result) {
					var items = $('#wu-datagrid-2').datagrid('getSelections');
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
		function searchUser() {
			var name = $('#uuname').val();
			var place = $('#uuplace').val();
			var sex = $('#uusex').combobox('getValue');
			var birthday = $('#uubirthday').datebox('getValue');
			$('#wu-datagrid-2').datagrid('load', {
				'user.username' : name,
				'user.place' : place,
				'user.sex' : sex,
				'user.birthday' : birthday
			});
		}

		/**
		 * Name 载入数据
		 */
		$('#wu-datagrid-2')
				.datagrid(
						{
							url : 'userList',
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
										width : 100,
										sortable : true
									},
									{
										field : 'username',
										title : '用户名',
										width : 100,
										sortable : true
									},
									{
										field : 'birthday',
										title : '出生年月',
										width : 100, 
										formatter : function(value){
						                    var date = new Date(value);
						                    var y = date.getFullYear();
						                    var m = date.getMonth() + 1;
						                    var d = date.getDate();
						                    return y + '-' +m + '-' + d;
						                }	
									},
									{
										field : 'sex',
										title : '性别',
										width : 100
									},
									{
										field : 'tel',
										title : '电话',
										width : 100
									},
									{
										field : 'place',
										title : '来自',
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