<#if list??>
<#list list as goods>
<tr>
	<td><input type="checkbox"></td>
	<td>${(goods.name)!}</td>
	<td>${(goods.shortName)!}</td>
	<td>${(goods.price)!}</td>
	<td>${(goods.purchasePrice)!}</td>
	<td>${(goods.agentPrice)!}</td>
	<td>${(goods.activityPrice)!}</td>
	<td>${(goods.supplierName)!}</td>
	<td>${(goods.stockNum)!}</td>
	<td><#if goods.createDate??>${goods.createDate?string("yyyy-MM-dd")}</#if></td>
	<td>
		<a href="/detail-1" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">预览</a>
		<a href="${webRoot}/sys/goods/edit?id=${goods.id}" class="layui-btn layui-btn-mini">编辑</a>
		<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
	</td>
</tr>
</#list>
</#if>