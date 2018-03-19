<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="wu-toolbar-2">
			<div class="wu-toolbar-button">
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="removeAll()" plain="true">批量删除</a> 
				<a href="javascript:;" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a> 
			</div>
			<div class="wu-toolbar-search">
				<form id="wu-form-1" method="post">
					<label>用户名：</label>
					<input id="uuname" class="wu-text" style="width: 100px;height:20px">
					<label>性别：</label> 
					<select id="uusex" class="easyui-combobox" panelHeight="auto" style="width: 100px" >
						<option value="">选择性别</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
					<label>部门：</label> 
					<select id="ssdepartment" style="height:20px">
							<option value="0" selected="selected">请选择部门</option>
							<s:iterator value="departments" var="department">
								<option value="${department.depId}">${department.depName} </option>
							</s:iterator>
					</select> 
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchEmployee();">开始检索</a>
				</form>
			</div>
		</div>
		<!-- End of toolbar -->
		<table id="wu-datagrid-3" class="easyui-datagrid"
			toolbar="#wu-toolbar-2"></table>
	</div>

	<!--修改页面  Begin of easyui-dialog -->
	<div id="wu-dialog-3" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 400px; padding: 10px;">
		<form id="wu-form-3" method="post">
			<input type="hidden" name="employee.empId" id="eid" />
			<table>
				<tr>
					<td width="60" align="right">姓 名：</td>
					<td><input type="text" name="employee.empName" id="ename" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">性 别：</td>
					<td><input type="text" name="employee.empSex" id="esex" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">年龄：</td>
					<td><input type="text" name="employee.empAge" id="eage" style="height:20px"
						class="wu-text" /></td>
				</tr>
				<tr>
					<td align="right">部 门：</td>
					<td>	<select id="edepId" name="employee.depId" style="height:20px">
							<option value="0" selected="selected">请选择部门</option>
							<s:iterator value="departments" var="department">
								<option value="${department.depId}">${department.depName} </option>
							</s:iterator>
					</select> 
					</td>
				</tr>
				<tr>
					<td align="right">工资：</td>
					<td><input type="text" name="employee.wages.salary" id="esalary" style="height:20px" class="wu-text" />
							<input type="hidden" name="employee.wages.wagesId" id="sid" />
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
			$('#wu-datagrid-3').datagrid('reload');
		}
		/*  操作标识，添加是为add，修改时为mod */
		var operType="";
		/**
		 * Name 添加 / 修改记录
		 */
		function edit() {
			$('#wu-form-3').form('submit', {
				url : 'employeeEdit',
				success : function(data) {
					if (data) {
						if(operType=="add"){
		                	$.messager.alert('提示', '添加成功！', 'info');
		                	$('#wu-dialog-3').dialog('close');
							$('#wu-datagrid-3').datagrid('reload');
		            	}else{
		            		$.messager.alert('提示', '修改成功！', 'info');
		            		$('#wu-dialog-3').dialog('close');
							$('#wu-datagrid-3').datagrid('reload');
		            	}
						$('#wu-datagrid-3').datagrid('reload');
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
						url : 'employeeDelete',
						data : {'empid' : id},
						success : function(data) {
							if (data) {
								$.messager.alert('信息提示', '删除成功！', 'info');
								$('#wu-datagrid-3').datagrid('reload');
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
			var items = $('#wu-datagrid-3').datagrid('getSelections');
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
									$('#wu-datagrid-3').datagrid('reload');
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
			$('#wu-form-3').form('clear');
			$('#wu-dialog-3').dialog({
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
						$('#wu-dialog-3').dialog('close');
					}
				} ]
			});
		}

		/**
		 * Name 打开修改窗口
		 */
		function openEdit() {
			operType="mod";
			$('#wu-form-3').form('clear');
			var item = $('#wu-datagrid-3').datagrid('getSelected'); // json对象
			//将选中的数据加载到修改页面中
			//$("#wu-form-3").form("load", item);
			$("#eid").val(item.empId);
			$("#ename").val(item.empName);
			$("#esex").val(item.empSex);
			$("#eage").val(item.empAge);
			$("#esalary").val(item.wages.salary);
			$("#edepId").val(item.depId);
			$("#sid").val(item.wages.wagesId);
			$('#wu-dialog-3').dialog({
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
						$('#wu-dialog-3').dialog('close');
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
		 * Name 模糊查询数据
		 */
		function searchEmployee() {
			var name = $('#uuname').val();
			var place = $('#uuplace').val();
			var sex = $('#uusex').combobox('getValue');
			var birthday = $('#uubirthday').datebox('getValue');
			$('#wu-datagrid-3').datagrid('load', {
				'user.username' : name,
				'user.place' : place,
				'user.sex' : sex,
				'user.birthday' : birthday
			});
		}

		/**
		 * Name 载入数据
		 */
		$('#wu-datagrid-3')
				.datagrid(
						{
							url : 'employeeList',
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
										field : 'empId',
										title : '编号',
										width : 100,
										sortable : true
									},
									{
										field : 'empName',
										title : '职工名',
										width : 100,
										sortable : true
									},
									{
										field : 'empSex',
										title : '性别',
										width : 100
									},
									{
										field : 'empAge',
										title : '年龄',
										width : 100
									},
									{
										field : 'wages',
										title : '工资',
										width : 100,
										formatter:function(value,row){
											return row.wages.salary;
										}
									},
									{
										field : '_operate',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, rows, index) {
											var str = '';
											str += '<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="openEdit();" >编辑</a>';
											str += '<a href="javascript:void(0)" class="user-easyui-linkbutton-remove" data-options="plain:true,iconCls:\'icon-remove\'"  onclick="remove('+rows.empId+');">删除</a>';
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