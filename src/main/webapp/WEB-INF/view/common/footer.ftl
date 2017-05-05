<#if err_msg??>
<script type="text/javascript" src="${webRoot}/plugins/layui/lay/modules/layer.js"></script>
<script>
	layer.alert('${(err_msg)!}', {icon: 5});
</script>
</#if>