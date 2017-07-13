<#if list??>
<#list list as payment>
<tr id="${(payment.id)!}TD">
	<td>${(payment.paymentType)!}</td>
	<td>${(payment.userName)!}</td>
	<td>${(payment.amount)!}</td>
	<td><#if payment.payDate??>${payment.payDate?string("yyyy-MM-dd")}</#if></td>
	<td>
		<a href="${webRoot}/payment/edit?id=${payment.id}" class="layui-btn layui-btn-mini">编辑</a>
		<a href="javascript:del(this, ${(payment.id)!});" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
	</td>
</tr>
</#list>
</#if>