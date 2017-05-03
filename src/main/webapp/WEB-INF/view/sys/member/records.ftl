<#if list??>
<#list list as member>
<tr>
	<td><input type="checkbox"></td>
	<td>${member.name}</td>
	<td>${member.phone}</td>
	<td>
		<#if member.gender?? && member.gender == '1'>男<#else>女</#if>
	</td>
	<td>${member.wx}</td>
	<td>${member.qq}</td>
	<td>${member.level}</td>
	<td><#if member.createDate??>${member.createDate?string("yyyy-MM-dd")}</#if></td>
	<td>${member.address}</td>
	<td>
		<a href="/detail-1" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">预览</a>
		<a href="${webRoot}/sys/member/edit?id=${member.id}" class="layui-btn layui-btn-mini">编辑</a>
		<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
	</td>
</tr>
</#list>
</#if>