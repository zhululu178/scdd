<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>新增用户</legend>
			</fieldset>
			<form class="layui-form" action="${webRoot}/sys/user/save" method="POST">
				<input type="hidden" name="id" value="${(user.id)!}"/>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-inline">
							<input type="text" name="name" lay-verify="required" placeholder="请输入" value="${(user.name)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">手机</label>
						<div class="layui-input-block">
							<input type="tel" name="phone" lay-verify="phone" placeholder="请输入" value="${(user.phone)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">用户代码</label>
						<div class="layui-input-inline">
							<input type="text" name="code" lay-verify="required" placeholder="请输入" value="${(user.code)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">生日</label>
						<div class="layui-input-block">
							<input type="text" name="birthday" id="date" readonly placeholder="yyyy-mm-dd" value="<#if user?? && user.birthday??>${(user.birthday?string("yyyy-MM-dd"))!}</#if>" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">代理</label>
					<div class="layui-input-block">
						<input type="checkbox" <#if user?? && user.agentFlag?? && user.agentFlag == '1'>checked=""</#if> name="agentFlag" lay-skin="switch" lay-filter="switchTest" title="开关">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-block">
						<#if user??>
							<input type="radio" name="gender" value="2" title="女" <#if user.gender?? && user.gender == '2'>checked=""</#if>>
							<input type="radio" name="gender" value="1" title="男" <#if user.gender?? && user.gender == '1'>checked=""</#if>>
						<#else>
							<input type="radio" name="gender" value="2" title="女">
							<input type="radio" name="gender" value="1" title="男" checked="">
						</#if>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">微信</label>
						<div class="layui-input-inline">
							<input type="tel" name="wx" placeholder="请输入" value="${(user.wx)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">QQ</label>
						<div class="layui-input-block">
							<input type="text" name="qq" placeholder="请输入" value="${(user.qq)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">地址</label>
					<div class="layui-input-block">
						<input type="text" name="address" placeholder="请输入" value="${(user.address)!}" autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a href="${webRoot}/sys/user/list" class="layui-btn layui-btn-primary">返回</a>
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
			});
		</script>
	</body>
	<#include "/common/footer.ftl">
</html>