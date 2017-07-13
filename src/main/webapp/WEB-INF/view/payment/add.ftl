<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>新增支出</legend>
			</fieldset>
			<form class="layui-form" action="${webRoot}/payment/save" method="POST">
				<input type="hidden" name="id" value="${(payment.id)!}"/>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">支出类型</label>
						<div class="layui-input-inline">
							<select name="paymentTypeId">
								<option value="">请选择支出类型</option>
								<#if paymentTypeList??>
									<#list paymentTypeList as paymentType>
									<#if paymentType.children??>
										<optgroup label="${paymentType.name}">
										<#list paymentType.children as childPaymentType>
											<option value="${(childPaymentType.id)!}" <#if payment?? && payment.paymentTypeId?? && payment.paymentTypeId == childPaymentType.id>selected</#if>>${(childPaymentType.name)!}</option>
										</#list>
										</optgroup>
									<#else>
										<optgroup label="${paymentType.name}">
											<option value="${paymentType.id}" <#if payment?? && payment.paymentTypeId?? && payment.paymentTypeId == paymentType.id>selected</#if>>${paymentType.name}</option>
										</optgroup>
									</#if>
									</#list>
								</#if>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">支出金额</label>
						<div class="layui-input-inline">
							<input type="text" name="amount" lay-verify="amount" placeholder="请输入" value="${(payment.amount?c)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">支出人</label>
						<div class="layui-input-inline">
							<select lay-verify="required" name="userId">
								<option value="">请选择</option>
								<#if userList??>
									<#list userList as usert>
										<option value="${(usert.id)!}" <#if payment?? && payment.userId?? && payment.userId == usert.id>selected</#if>>${(usert.name)!}</option>
									</#list>
								</#if>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">支付日期</label>
						<div class="layui-input-inline">
							<input type="text" name="payDate" id="date" readonly placeholder="yyyy-mm-dd" value="<#if payment?? && payment.payDate??>${(payment.payDate?string("yyyy-MM-dd"))!}</#if>" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" name="remark" placeholder="请输入" value="${(payment.remark)!}" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a href="${webRoot}/payment/list" class="layui-btn layui-btn-primary">返回</a>
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
					amount: [/^\d+(\.\d+)?$/, '请输入正确的支出金额']
				});
			});
		</script>
	</body>
	<#include "/common/footer.ftl">
</html>