<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<link rel="stylesheet" href="${webRoot}/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${webRoot}/css/global.css" media="all">
	<link rel="stylesheet" href="${webRoot}/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${webRoot}/css/table.css" />
	<body>
		<div class="admin-main">
			<form id="search_form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">供应商名称</label>
					<div class="layui-input-inline">
						<input type="text" name="name" placeholder="请输入" value="${(supplier.name)!}" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">供应商电话</label>
					<div class="layui-input-block">
						<input type="text" name="phone" placeholder="请输入" value="${(supplier.phone)!}" class="layui-input"/>
					</div>
				</div>
			</div>
			</form>
			<blockquote class="layui-elem-quote">
				<a href="${webRoot}/sys/supplier/add" class="layui-btn layui-btn-small" id="add">
					<i class="layui-icon">&#xe608;</i> 新增供应商
				</a>
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>数据列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="selected-all"></th>
								<th>供应商名称</th>
								<th>供应商电话</th>
								<th>联系人</th>
								<th>微信</th>
								<th>QQ</th>
								<th>地址</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="list_table">
							<#include "/sys/supplier/records.ftl"/>
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page">
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${webRoot}/plugins/layui/layui.js"></script>
		<script>
			layui.config({
				base: '${webRoot}/plugins/layui/modules/'
			});
			var laypage;
			layui.use(['laypage'], function() {laypage = layui.laypage;});
			function page(curr){
				$.ajax({
					url: "${webRoot}/sys/supplier/search?pageNo=" + curr,
					data : $("#search_form").serialize(), 
					type : 'POST',
					success : function(data) {
						$('#list_table').html();
						$('#list_table').html(data.contents);
						//显示分页
				        laypage({
				            cont: 'page', // 容器
				            pages: data.pageNum,     // 总页数(后台的)
				            curr: data.pageCur, //当前页(后台获取到的)
				            jump: function(obj, first){ //触发分页后的回调(单击页码后)
				                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
				               	    page(obj.curr);  // (被单击的页码)
				                }
				            }
				        });
					}
				});
			 };
			 $('#search').on('click', function() {
				$.post("${webRoot}/sys/supplier/list", $("#search_form").serialize(),
			    function(data) {
			    	page(1);
			    });
			 });
			 // 初始化运行
			 page(1);
		</script>
	</body>

</html>