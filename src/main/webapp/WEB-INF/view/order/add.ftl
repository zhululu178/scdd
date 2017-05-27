<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<script type="text/javascript" src="${webRoot}/plugins/jquery-ui/1.12.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="${webRoot}/plugins/jquery-ui/1.12.1/jquery-ui.min.css" media="all" />
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>新增商品</legend>
			</fieldset>
			<form class="layui-form" action="${webRoot}/order/save" method="POST">
				<input type="hidden" name="id" value="${(order.id)!}"/>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">会员姓名</label>
						<div class="layui-input-inline">
							<input type="text" id="keyword" placeholder="请输入" value="${(order.member.name)!}" lay-verify="required" class="layui-input">
							<input type="hidden" id="memberIdT" lay-verify="required" name="memberId" value="${(order.memberId)!}" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">交易日期</label>
						<div class="layui-input-inline">
							<input type="text" name="transDate" id="date" readonly placeholder="yyyy-mm-dd" value="<#if order?? && order.transDate??>${(order.transDate?string("yyyy-MM-dd"))!}</#if>" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">实际金额</label>
						<div class="layui-input-inline">
							<input type="text" name="actualAmount" lay-verify="actualAmount" placeholder="请输入" value="${(order.actualAmount)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">销售人</label>
						<div class="layui-input-inline">
							<select name="userId" lay-verify="required">
								<option value="">请选择</option>
								<#if userList??>
									<#list userList as user>
										<option value="${(user.id)!}" <#if order?? && order.userId?? && order.userId == user.id>selected</#if>>${(user.name)!}</option>
									</#list>
								</#if>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">订单地址</label>
					<div class="layui-input-block">
						<input type="text" name="deliveryAddr" lay-verify="required" placeholder="请输入" value="${(order.deliveryAddr)!}" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a href="${webRoot}/order/list" class="layui-btn layui-btn-primary">返回</a>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${webRoot}/plugins/layui/layui.js"></script>
		<script>
             $(function() {
                 $('#keyword').autocomplete({
                     max: 10,    //列表里的条目数
                     minChars: 0,    //自动完成激活之前填入的最小字符
                     width: 400,     //提示的宽度，溢出隐藏
                     scrollHeight: 300,   //提示的高度，溢出显示滚动条
                     matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                     autoFill: false,    //自动填充
                     source: function (request, response) {
				        $.ajax({
					        type: "POST",
					        url:"${webRoot}/sys/member/find",
					        data: {term:request.term},
					        success: response,
					        dataType: 'json',
					        minLength: 2,
					        delay: 100
				     	})
				     },
                     select: function(event, row) {
	                     $('#memberIdT').val(row.item.key);
                     }
                 });
             });
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer,
					layedit = layui.layedit,
					laydate = layui.laydate;
				//自定义验证规则
				form.verify({
					actualAmount: [/^\d+(\.\d+)?$/, '请输入正确的实际金额'],
					purchasePrice: [/^\d+(\.\d+)?$/, '请输入正确的商品进价'],
					agentPrice: [/^\d+(\.\d+)?$/, '请输入正确的商品代理价'],
					activityPrice: [/^\d+(\.\d+)?$/, '请输入正确的商品活动价']
				});
			});

		</script>
	</body>
	<#include "/common/footer.ftl">
</html>