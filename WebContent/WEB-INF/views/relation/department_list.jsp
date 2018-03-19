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
		</div>
		<!-- End of toolbar -->
		<table id="wu-datagrid-4" class="easyui-datagrid"
			toolbar="#wu-toolbar-2"></table>
	</div>

	<!--修改页面  Begin of easyui-dialog -->
	<div id="wu-dialog-4" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 400px; padding: 10px;">
		<form id="wu-form-4" method="post">
			<input type="hidden" name="department.depId" id="did" />
			<table>
				<tr>
					<td width="60" align="right">部门名称：</td>
					<td><input type="text" name="department.depName" id="dname" style="height:20px"
						class="wu-text" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 查看所属部门的员工页面 -->
	<div id="see-dialog"  class="easyui-dialog" title="部门下的员工有：" style="width: 580px; padding: 10px;" data-options="closed:true"
      resizable:true,modal:true>   
	     <table id="employee-datagrid" class="easyui-datagrid"  style="width:540px;height:350px">
	    </table>
	</div>  
	
	<!-- End of easyui-dialog -->
	<script type="text/javascript">
		/**
		 * Name 刷新页面
		 */
		function reload() {
			$('#wu-datagrid-4').datagrid('reload');
		}
		/*  操作标识，添加是为add，修改时为mod */
		var operType="";
		/**
		 * Name 添加 / 修改记录
		 */
		function edit() {
			$('#wu-form-4').form('submit', {
				url : 'employeeEdit',
				success : function(data) {
					if (data) {
						if(operType=="add"){
		                	$.messager.alert('提示', '添加成功！', 'info');
		                	$('#wu-dialog-4').dialog('close');
							$('#wu-datagrid-4').datagrid('reload');
		            	}else{
		            		$.messager.alert('提示', '修改成功！', 'info');
		            		$('#wu-dialog-4').dialog('close');
							$('#wu-datagrid-4').datagrid('reload');
		            	}
						$('#wu-datagrid-4').datagrid('reload');
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
								$('#wu-datagrid-4').datagrid('reload');
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
			var items = $('#wu-datagrid-4').datagrid('getSelections');
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
							url : 'employeeDelete',
							type : 'post',
							traditional : true,//传数组进后台需要设置该属性
							data : {
								'empids' : ids
							},
							success : function(data) {
								if (data) {
									$.messager.alert('信息提示', '删除成功！', 'info');
									$('#wu-datagrid-4').datagrid('reload');
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
			$('#wu-form-4').form('clear');
			$('#wu-dialog-4').dialog({
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
						$('#wu-dialog-4').dialog('close');
					}
				} ]
			});
		}

		/**
		 * Name 打开修改窗口
		 */
		function openEdit() {
			operType="mod";
			$('#wu-form-4').form('clear');
			var item = $('#wu-datagrid-4').datagrid('getSelected'); // json对象
			//将选中的数据加载到修改页面中
			//$("#wu-form-4").form("load", item);
			$("#eid").val(item.empId);
			$("#ename").val(item.empName);
			$("#esex").val(item.empSex);
			$("#eage").val(item.empAge);
			$("#esalary").val(item.wages.salary);
			$("#edepId").val(item.depId);
			$('#wu-dialog-4').dialog({
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
						$('#wu-dialog-4').dialog('close');
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
		 * Name 载入数据
		 */
		$('#wu-datagrid-4')
				.datagrid(
						{
							url : 'departmentList',
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
										field : 'depId',
										title : '编号',
										width : 100,
										sortable : true
									},
									{
										field : 'depName',
										title : '职工名',
										width : 100,
										sortable : true
									},{
										field : '_operate',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, rows, index) {
											var str = '';
											str += '<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="openEdit();" >编辑</a>';
											str += '<a href="javascript:void(0)" class="user-easyui-linkbutton-remove" data-options="plain:true,iconCls:\'icon-remove\'"  onclick="remove('+rows.depId+');">删除</a>';
											str += '<a href="javascript:void(0)" class="user-easyui-linkbutton-see" data-options="plain:true,iconCls:\'icon-tip\'"  onclick="see('+rows.depId+');">查看部门下的员工</a>';
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
								$('.user-easyui-linkbutton-see').linkbutton(
										{
											text : '查看部门下的员工'
										});
							},
						});
		function see(id){
                 $('#employee-datagrid').datagrid({
                            striped : true,
                            url :'employeeByDepid',
                            iconCls : "icon-add",
                            fitColumns: false,
                            loadMsg : "数据加载中......",
                            pagination : true,
                            rownumbers  : true,
                            nowrap : false,
                            showFooter : true,
                            singleSelect : true,
                            pageList : [100,50,20,10],
                            queryParams: {depid: id},
                            columns : [ [ {
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
							} ] ],
                             onLoadSuccess : function() {
                                //数据加载成功后久可以拖动
                                $(this).datagrid('enableDnd');
                            },
                            onDrop : function(targetRow, sourceRow, point) {
                                console.log(targeRow + "," + sourceRow + "," + point);
                            } 
                        }); 
		               //弹出dialog框
		                $('#see-dialog').window('open'); 
			
		}
	</script>