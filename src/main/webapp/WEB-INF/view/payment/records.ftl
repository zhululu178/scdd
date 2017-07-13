<#if list??>
<#list list as payment>
<#if (list?size - 1 == payment_index) >
<tr>
	<td>合计</td>
	<td>${(payment.userName)!}</td>
	<td></td>
	<td>${(payment.amount?c)!}</td>
	<td></td>
</tr>
<#else>
<tr id="${(payment.id)!}TD">
	<td>${(payment.paymentType)!}</td>
	<td>${(payment.userName)!}</td>
	<td>${(payment.amount?c)!}</td>
	<td><#if payment.payDate??>${payment.payDate?string("yyyy-MM-dd")}</#if></td>
	<td>
		<a href="${webRoot}/payment/edit?id=${payment.id}" class="layui-btn layui-btn-mini">编辑</a>
		<a href="javascript:del(this, ${(payment.id)!});" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
	</td>
</tr>
</#if>
</#list>
</#if>