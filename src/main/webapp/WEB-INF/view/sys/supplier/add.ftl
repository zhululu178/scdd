<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>新增供应商</legend>
			</fieldset>
			<form class="layui-form" action="${webRoot}/sys/supplier/save" method="POST">
				<input type="hidden" name="id" value="${(supplier.id)!}"/>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">供应商名称</label>
						<div class="layui-input-inline">
							<input type="text" name="name" lay-verify="required" placeholder="请输入" value="${(supplier.name)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">供应商电话</label>
						<div class="layui-input-block">
							<input type="tel" name="phone" lay-verify="phone" placeholder="请输入" value="${(supplier.phone)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">联系人</label>
						<div class="layui-input-inline">
							<input type="text" name="contact" placeholder="请输入" value="${(supplier.contact)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">地址</label>
						<div class="layui-input-block">
							<input type="text" name="address" placeholder="请输入" value="${(supplier.address)!}" autocomplete="off" placeholder="请输入标题" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">微信</label>
						<div class="layui-input-inline">
							<input type="tel" name="wx" placeholder="请输入" value="${(supplier.wx)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">QQ</label>
						<div class="layui-input-block">
							<input type="text" name="qq" placeholder="请输入" value="${(supplier.qq)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" name="remark" placeholder="请输入" value="${(supplier.remark)!}" autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a href="${webRoot}/sys/supplier/list" class="layui-btn layui-btn-primary">返回</a>
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
					});
				});
			});
		</script>
	</body>

</html>