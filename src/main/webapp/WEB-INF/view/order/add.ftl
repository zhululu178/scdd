<!DOCTYPE html>
<html>
	<#include "/common/header.ftl">
	<link rel="stylesheet" href="${webRoot}/css/global.css" media="all">
	<link rel="stylesheet" href="${webRoot}/css/table.css" />
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
							<input type="text" name="actualAmount" lay-verify="actualAmount" placeholder="请输入" value="${(order.actualAmount?c)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">销售人</label>
						<div class="layui-input-inline">
							<select lay-verify="required" name="userId">
								<option value="">请选择</option>
								<#if userList??>
									<#list userList as usert>
										<option value="${(usert.id)!}" <#if order?? && order.userId?? && order.userId == usert.id>selected</#if>>${(usert.name)!}|${(usert.agentFlag)!}</option>
									</#list>
								</#if>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">快递公司</label>
						<div class="layui-input-inline">
							<select name="expressCompany">
								<option value="">请选择</option>
								<#if expressCompanyList??>
									<#list expressCompanyList as expressCompany>
										<option value="${(expressCompany.code)!}" <#if order?? && order.expressCompany?? && order.expressCompany == expressCompany.code>selected</#if>>${(expressCompany.title)!}</option>
									</#list>
								</#if>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">快递单号</label>
						<div class="layui-input-inline">
							<input type="text" name="expressNum" placeholder="请输入" value="${(order.expressNum)!}" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">订单地址</label>
					<div class="layui-input-block">
						<input type="text" name="deliveryAddr" lay-verify="required" placeholder="请输入" value="${(order.deliveryAddr)!}" autocomplete="off" class="layui-input">
					</div>
				</div>
				<fieldset class="layui-elem-field">
					<legend>订单明细</legend>
					<div class="layui-field-box">
						<table class="site-table table-hover" id="goods_table">
							<thead>
								<tr>
									<th>商品名称</th>
									<th>单价</th>
									<th>数量</th>
									<th>折扣</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="list_table">
								<#if order?? && order.details??>
								<#list order.details as orderDetail>
								<tr>
									<td>
		                            	<div class="table-fill-container">
		                            		<div class="table-fill">
			                                    <input type="text" class="J-jQAutoComplete" data-autoparams="${webRoot}/sys/goods/find" value="${(orderDetail.goods.name)!}"></input>
			                                	<input type="text" style="display: none;" lay-verify="required" name="details[${orderDetail_index}].goodsId" id="code" value="${(orderDetail.goods.id)!}" />
			                                </div>
		                            	</div>
									</td>
									<td><input type="text" name="details[${orderDetail_index}].unitPrice" id="unitPrice" lay-verify="unitPrice" value="${(orderDetail.unitPrice?c)!}" /></td>
									<td><input type="text" name="details[${orderDetail_index}].quantity" lay-verify="quantity" value="${(orderDetail.quantity?c)!}" /></td>
									<td><input type="text" name="details[${orderDetail_index}].discount" value="${(orderDetail.discount?c)!}" /></td>
									<td>
										<a href="#" onClick="deleteTr(this);" class="layui-btn layui-btn-mini">删除</a>
									</td>
								</tr>
								</#list>
								</#if>
							</tbody>
						</table>
					</div>
				</fieldset>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a href="#" onclick="addTr();" class="layui-btn layui-btn-primary">新增商品</a>
						<a href="#" onclick="javascript:history.back(-1);" class="layui-btn layui-btn-primary">返回</a>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${webRoot}/plugins/layui/layui.js"></script>
		<script>
	    	//通用的自动补全
	        var AutoComplete = function(node){
	            this.node = $(node);
	            this.hiddenCodeInp = this.node.closest('.table-fill').find('#code');
	            this.posBox = this.node.closest('.table-fill-container');
	            this.priceInp = this.posBox.parent().parent().find('#unitPrice');//单价
	            this.datasource = (this.node).data('autoparams');
	            this.rangeparams = (this.node).data('rangeparams');
	            this._init();
	        }
	        AutoComplete.prototype={
	            "constructor": AutoComplete,
	            _init: function(){
	                var that = this;
	                (that.node).autocomplete({
	                    appendTo: (this.posBox)[0],
	                    mustMatch: true,
	                    source: function( request, response ) {
	                    	var userAgent = $('body').find('.layui-form-select').find('.layui-this').text().split("|");
                        	$.ajax({
                                url: that.datasource,
                                type: "POST",
                                dataType: "json",
                                data: {
                                	keyword: request.term,
                                    range: that.rangeparams,
                                    agentflag: userAgent[1]
                                },
                                success: function( data ) {
                                    var dataList = data;
                                    var matcher = new RegExp( $.ui.autocomplete.escapeRegex( request.term ), "i" );
                                    response( $.grep( dataList, function( value ) {
                                            value = value.label;
                                            if(matcher.test( value ) || matcher.test( that._normalize( value ) )){
                                                return matcher.test( value ) || matcher.test( that._normalize( value ) ) ;
                                            }
                                        })

                                    )
                                }
                            });
	                    },
	                    select: function( event, ul ) {
	                        that.node.val( ul.item.value );
	                        that.hiddenCodeInp.val(ul.item.key);
	                        that.priceInp.val(ul.item.mark);
	                        that.posBox.find(".J-controllerTip").remove();
	                        return false;
	                    },
	                    minLength: 1,
	                    response: function( event, ui ){
	                        var uiArry = [],
	                            idInps,
	                            bool
	                            ;
	                        uiArry = ui.content;
	                        idInps = that.node.val();
	                        bool = that.posBox.find('.ui-autocomplete').css('display');
	                        if(bool == "none"){
	                            that.hiddenCodeInp.val('');
	                            if(!that.posBox.find('.J-controllerTip').length){
	                            	 that.posBox.append('<div class="controller-tip J-controllerTip"><p class="error"><span class="tip-txt required">该字段不能为空！</span></p></div>');
	                                 
	                            }
	                        }
	
	                    },
	                    close: function( event, ui ){
	                        var uiArry = [],
	                            idInps
	                            ;
	                        uiArry = ui.content;
	                        idInps = that.node.val();
	                        $uiMenuItem = that.posBox.find('.autocomplete-list-item');
	                        if(($uiMenuItem.length === 1) && (idInps != $uiMenuItem.find(".valueKey").html())){
	                            that.node.val('');
	                        }
	                    }
	                })
	                    .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	                    return $( "<li class='autocomplete-list-item'>" )
	                        .append( "<span class='code'>" + item.key + "</span><span class='valueKey'>" + item.label + "</span>" )
	                        .appendTo( ul );
	                };
	
	
	
	            },
	            _normalize: function(term){
	                var ret = "", accentMap=[];
	                for ( var i = 0; i < term.length; i++ ) {
	                    ret += accentMap[ term.charAt(i) ] || term.charAt(i);
	                }
	                return ret;
	            }
	        };
             $(function() {
             	//需要自动补全的商品明细信息
				var $jQAutoComplete = $('body').find('.J-jQAutoComplete');
		        $.each($jQAutoComplete, function(index, item){
		            new AutoComplete(item);
		        });
             
             	//会员自动补全
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
             
             //删除当前商品
             function deleteTr(curTd) {
	             if(confirm("确定要删除当前商品?")) {
				 	$(curTd).parent().parent().remove();
				 }
             }
             
             var goodsIndex = <#if order?? && order.details??>${order.details?size}<#else>0</#if>;
             var newTr = "<tr><td><div class='table-fill-container'><div class='table-fill'>" + 
             			"<input type='text' class='J-jQAutoComplete' data-autoparams='${webRoot}/sys/goods/find' value=''></input>" +
             			"<input type='text' style='display: none;' lay-verify='required' name='details[{orderDetail_index}].goodsId' id='code' value='' />" +
             			"</div></div></td>" +
             			"<td><input type='text' name='details[{orderDetail_index}].unitPrice' id='unitPrice' lay-verify='unitPrice' value='' /></td>" +
             			"<td><input type='text' name='details[{orderDetail_index}].quantity' lay-verify='quantity' value='' /></td>" +
             			"<td><input type='text' name='details[{orderDetail_index}].discount' value='' /></td>" +
             			"<td><a href='#' onClick='deleteTr(this);' class='layui-btn layui-btn-mini'>删除</a></td></tr>";
             //新增一个商品
             function addTr() {
	             if(confirm("确定要新增一个商品?")) {
	             	var aTr = newTr.replace(/{orderDetail_index}/g, '' + goodsIndex);
				 	$("#goods_table").append(aTr);
				 	
	             	//需要自动补全的商品明细信息
					var $jQAutoComplete = $('body').find('.J-jQAutoComplete');
			        $.each($jQAutoComplete, function(index, item){
			            new AutoComplete(item);
			        });
			        goodsIndex = goodsIndex + 1;
				 }
             }
             
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer,
					layedit = layui.layedit,
					laydate = layui.laydate;
				//自定义验证规则
				form.verify({
					actualAmount: [/^\d+(\.\d+)?$/, '请输入正确的实际金额'],
					quantity: [/^\d+(\.\d+)?$/, '请输入正确的商品数量'],
					unitPrice: [/^\d+(\.\d+)?$/, '请输入正确的商品单价']
				});
			});

		</script>
	</body>
	<#include "/common/footer.ftl">
</html>