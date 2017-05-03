<#if list??>
<#list list as supplier>
<tr>
	<td><input type="checkbox"></td>
	<td>${(supplier.name)!}</td>
	<td>${(supplier.phone)!}</td>
	<td>${(supplier.contact)!}</td>
	<td>${(supplier.wx)!}</td>
	<td>${(supplier.qq)!}</td>
	<td>${(supplier.address)!}</td>
	<td><#if supplier.createDate??>${supplier.createDate?string("yyyy-MM-dd")}</#if></td>
	<td>
		<a href="/detail-1" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">预览</a>
		<a href="${webRoot}/sys/supplier/edit?id=${(supplier.id)!}" class="layui-btn layui-btn-mini">编辑</a>
		<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
	</td>
</tr>
</#list>
</#if>