<#if list??>
<#list list as order>
<tr>
	<td><input type="checkbox"></td>
	<td>${(order.memberName)!}</td>
	<td>${(order.memberPhone)!}</td>
	<td>${(order.amount?c)!}</td>
	<td>${(order.actualAmount?c)!}</td>
	<td><#if order.transDate??>${order.transDate?string("yyyy-MM-dd")}</#if></td>
	<td>${(order.userName)!}</td>
	<td>${(order.deliveryAddr)!}</td>
	<td><#if order.createDate??>${order.createDate?string("yyyy-MM-dd")}</#if></td>
	<td>
		<a href="/detail-1" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">预览</a>
		<a href="${webRoot}/order/edit?id=${order.id}" class="layui-btn layui-btn-mini">编辑</a>
		<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
	</td>
</tr>
</#list>
</#if>