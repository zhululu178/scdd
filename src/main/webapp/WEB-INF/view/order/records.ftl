<#if list??>
<#list list as order>
<#if (list?size - 1 == order_index) >
<tr>
	<td></td>
	<td>合计</td>
	<td>${(order.memberPhone)!}</td>
	<td>${(order.purchaseAmount?c)!}</td>
	<td>${(order.actualAmount?c)!}</td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<#else>
<tr>
	<td><input type="checkbox"></td>
	<td>${(order.memberName)!}</td>
	<td>${(order.memberPhone)!}</td>
	<td>${(order.purchaseAmount?c)!}</td>
	<td>${(order.actualAmount?c)!}</td>
	<td><#if order.transDate??>${order.transDate?string("yyyy-MM-dd")}</#if></td>
	<td>${(order.userName)!}</td>
	<td>${(order.deliveryAddr)!}</td>
	<td><#if order.createDate??>${order.createDate?string("yyyy-MM-dd")}</#if></td>
	<td>
		<a href="${webRoot}/order/edit?id=${(order.id)!}" class="layui-btn layui-btn-mini">编辑</a>
		<span onclick="javascript:del(this, ${(order.id)!});" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</span>
	</td>
</tr>
</#if>
</#list>
</#if>