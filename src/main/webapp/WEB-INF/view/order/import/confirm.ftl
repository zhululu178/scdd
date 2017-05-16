<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>批量订单输入</legend>
			</fieldset>

			<form class="layui-form" action="${webRoot}/order/import/index" method="POST">
				<input type="hidden" name="transDate" value="${transDate}"/>
				<input type="hidden" name="orderDetails" value="${orderDetails}"/>
			<#if import_error_list??>
			<#list import_error_list as err_msgt>
			${(err_msgt)!}<p>
			</#list>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn">返回</button>
					</div>
				</div>
			<#else>
			数据导入成功.
				<div class="layui-form-item">
					<div class="layui-input-block">
						<a href="${webRoot}/order/import/index" class="layui-btn layui-btn-primary">返回</a>
					</div>
				</div>
			</#if>
			</form>
		</div>
	</body>
</html>