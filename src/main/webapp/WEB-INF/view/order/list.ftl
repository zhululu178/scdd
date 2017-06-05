<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<link rel="stylesheet" href="${webRoot}/css/global.css" media="all">
	<link rel="stylesheet" href="${webRoot}/css/table.css" />
	<body>
		<div class="admin-main">
			<form id="search_form">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">会员姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="memberName" placeholder="请输入" value="${(order.memberName)!}" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">会员手机</label>
					<div class="layui-input-inline">
						<input type="text" name="memberPhone" placeholder="请输入" value="${(order.memberPhone)!}" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">销售人</label>
					<div class="layui-input-block">
						<input type="text" name="userName" placeholder="请输入" value="${(order.userName)!}" class="layui-input"/>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">交易日期</label>
					<div class="layui-input-block">
						<input type="text" name="transDateStart" id="transDateStart" readonly placeholder="yyyy-mm-dd" value="<#if order?? && order.transDateStart??>${(order.transDateStart?string("yyyy-MM-dd"))!}</#if>" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">至
						<input type="text" name="transDateEnd" id="transDateEnd" readonly placeholder="yyyy-mm-dd" value="<#if order?? && order.transDateEnd??>${(order.transDateEnd?string("yyyy-MM-dd"))!}</#if>" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
					</div>
				</div>
			</div>
			</form>
			<blockquote class="layui-elem-quote">
				<a href="${webRoot}/order/add" class="layui-btn layui-btn-small" id="add">
					<i class="layui-icon">&#xe608;</i> 新增订单
				</a>
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>
			</blockquote>
			<fieldset class="layui-elem-field">
				<legend>订单列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="selected-all"></th>
								<th>会员姓名</th>
								<th>会员手机</th>
								<th>订单总价</th>
								<th>订单实际总价</th>
								<th>交易日期</th>
								<th>销售人员</th>
								<th>订单地址</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="list_table">
							<#include "/order/records.ftl"/>
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
			layui.use(['laypage', 'laydate'], function() {laypage = layui.laypage;laydate = layui.laydate;});
			function page(curr){
				$.ajax({
					url: "${webRoot}/order/search?pageNo=" + curr,
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
				$.post("${webRoot}/order/list", $("#search_form").serialize(),
			    function(data) {
			    	page(1);
			    });
			 });
			 // 初始化运行
			 page(1);
		</script>
	</body>

</html>