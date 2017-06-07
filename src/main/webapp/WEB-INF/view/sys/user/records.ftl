<#if list??>
<#list list as user>
<tr>
	<td><input type="checkbox"></td>
	<td>${(user.name)!}</td>
	<td>${(user.phone)!}</td>
	<td>${(user.code)!}</td>
	<td style="text-align:center;"><#if user.agentFlag == '1'><i class="layui-icon" style="color:green;"></i></#if></td>
	<td>
		<#if user.gender?? && user.gender == '1'>男<#else>女</#if>
	</td>
	<td>${(user.wx)!}</td>
	<td>${(user.qq)!}</td>
	<td><#if user.createDate??>${user.createDate?string("yyyy-MM-dd")}</#if></td>
	<td>${(user.address)!}</td>
	<td>
		<a href="/detail-1" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">预览</a>
		<a href="${webRoot}/sys/user/edit?id=${user.id}" class="layui-btn layui-btn-mini">编辑</a>
		<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
	</td>
</tr>
</#list>
</#if>