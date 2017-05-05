<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>新增商品</legend>
			</fieldset>
			<form class="layui-form" action="${webRoot}/sys/goods/save" method="POST">
				<input type="hidden" name="id" value="${(goods.id)!}"/>
				<div class="layui-form-item">
					<label class="layui-form-label">商品名称</label>
					<div class="layui-input-block">
						<input type="text" name="name" lay-verify="required" placeholder="请输入" value="${(goods.name)!}" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">商品简称</label>
					<div class="layui-input-block">
						<input type="text" name="shortName" lay-verify="required" placeholder="请输入" value="${(goods.shortName)!}" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商品售价</label>
						<div class="layui-input-inline">
							<input type="text" name="price" lay-verify="price" placeholder="请输入" value="${(goods.price)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">商品进价</label>
						<div class="layui-input-block">
							<input type="text" name="purchasePrice" lay-verify="purchasePrice" placeholder="请输入" value="${(goods.purchasePrice)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商品代理价</label>
						<div class="layui-input-inline">
							<input type="text" name="agentPrice" lay-verify="agentPrice" placeholder="请输入" value="${(goods.agentPrice)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">商品活动价</label>
						<div class="layui-input-block">
							<input type="text" name="activityPrice" lay-verify="activityPrice" placeholder="请输入" value="${(goods.activityPrice)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">供应商</label>
						<div class="layui-input-inline">
							<select name="supplierId" lay-filter="aihao">
								<option value="">请选择</option>
								<#if supplierList??>
									<#list supplierList as supplier>
										<option value="${(supplier.id)!}" <#if goods?? && goods.supplierId?? && goods.supplierId == supplier.id>selected</#if>>${(supplier.name)!}</option>
									</#list>
								</#if>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">库存</label>
						<div class="layui-input-inline">
							<input type="text" name="stockNum" lay-verify="number" placeholder="请输入" value="${(goods.stockNum)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a href="${webRoot}/sys/goods/list" class="layui-btn layui-btn-primary">返回</a>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${webRoot}/plugins/layui/layui.js"></script>
		<script>
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer,
					layedit = layui.layedit,
					laydate = layui.laydate;
				//自定义验证规则
				form.verify({
					price: [/^\d+(\.\d+)?$/, '请输入正确的商品售价'],
					purchasePrice: [/^\d+(\.\d+)?$/, '请输入正确的商品进价'],
					agentPrice: [/^\d+(\.\d+)?$/, '请输入正确的商品代理价'],
					activityPrice: [/^\d+(\.\d+)?$/, '请输入正确的商品活动价']
				});

				//监听提交
				form.on('submit(demo1)', function(data) {
					layer.alert(JSON.stringify(data.field), {
						title: '最终的提交信息'
					});
				});
			});
		</script>
	</body>
	<#include "/common/footer.ftl">
</html>