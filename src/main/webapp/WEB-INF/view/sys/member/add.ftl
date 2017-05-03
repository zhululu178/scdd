<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>新增会员</legend>
			</fieldset>
			<form class="layui-form" action="${webRoot}/sys/member/save" method="POST">
				<input type="hidden" name="id" value="${(member.id)!}"/>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-inline">
							<input type="text" name="name" lay-verify="required" placeholder="请输入" value="${(member.name)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">手机</label>
						<div class="layui-input-block">
							<input type="tel" name="phone" lay-verify="phone" placeholder="请输入" value="${(member.phone)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">微信</label>
						<div class="layui-input-inline">
							<input type="text" name="wx" placeholder="请输入" value="${(member.wx)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">生日</label>
						<div class="layui-input-block">
							<input type="text" name="birthday" id="date" readonly placeholder="yyyy-mm-dd" value="<#if member?? && member.birthday??>${(member.birthday?string("yyyy-MM-dd"))!}</#if>" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-block">
						<#if member??>
							<input type="radio" name="gender" value="2" title="女" <#if member.gender?? && member.gender == '2'>checked=""</#if>>
							<input type="radio" name="gender" value="1" title="男" <#if member.gender?? && member.gender == '1'>checked=""</#if>>
						<#else>
							<input type="radio" name="gender" value="2" title="女" checked="">
							<input type="radio" name="gender" value="1" title="男">
						</#if>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">QQ</label>
						<div class="layui-input-block">
							<input type="text" name="qq" placeholder="请输入" value="${(member.qq)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">会员等级</label>
						<div class="layui-input-block">
							<select name="level" lay-filter="aihao">
								<#list levels?keys as key>
									<option value="${(key)!}" <#if member?? && member.level?? && member.level == key>selected</#if>>${(levels[key])!}</option>
								</#list>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">地址</label>
					<div class="layui-input-block">
						<input type="text" name="address" placeholder="请输入" value="${(member.address)!}" autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a href="${webRoot}/sys/member/list" class="layui-btn layui-btn-primary">返回</a>
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