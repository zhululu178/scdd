<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>批量订单输入</legend>
			</fieldset>

			<form class="layui-form" action="${webRoot}/order/import/save" method="POST">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">交易日期</label>
						<div class="layui-input-block">
							<input type="text" name="transDate" value="${(transDate)!}" id="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">订单信息</label>
					<div class="layui-input-block">
						<textarea name="orderDetails" placeholder="请输入内容" class="layui-textarea" lay-verify="required">${(orderDetails)!}</textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
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

				//创建一个编辑器
				var editIndex = layedit.build('LAY_demo_editor');
				//自定义验证规则
				form.verify({
					title: function(value) {
						if(value.length < 5) {
							return '标题至少得5个字符啊';
						}
					},
					pass: [/(.+){6,12}$/, '密码必须6到12位'],
					content: function(value) {
						layedit.sync(editIndex);
					}
				});

				//监听提交
				form.on('submit(demo1)', function(data) {
					layer.alert(JSON.stringify(data.field), {
						title: '最终的提交信息'
					})
				});
			});
		</script>
	</body>
</html>