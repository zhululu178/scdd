<!DOCTYPE html>
<html>
<head>
	<#include "/common/header.ftl">
    <script src="${webRoot}/plugins/echarts/echarts.min.js"></script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div class="layui-inline">
		<div class="layui-input-inline">
			<select id="orderAmountDlyReportType">
				<#if orderReportTypeList??>
					<#list orderReportTypeList as reportType>
						<option value="${(reportType.code)!}">${(reportType.title)!}</option>
					</#list>
				</#if>
			</select>
		</div>
	</div>
    <div id="orderAmountDly" style="width: 800px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var orderAmountDlyChart = echarts.init(document.getElementById('orderAmountDly'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '销售额'
            },
            tooltip: {},
            legend: {
                data:['销售额（单位：元）']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '销售额（单位：元）',
                type: 'line',
                data: []
            }]
        };
        
        //选择时间
		$("#orderAmountDlyReportType").change(function(){
			getOrderAmountDly($(this).children('option:selected').val());
		});
		
		function getOrderAmountDly(type) {
			$.ajax({ 
			    type: "post", 
			    async: false, //同步执行 
			    url: "${webRoot}/report/order/dailyamount?type=" + type, 
			    dataType: "json", //返回数据形式为json 
			    success: function(result) {
			    	if(result.length > 0) {
				    	var dateArr = new Array();
				    	var amountArr = new Array();
				    	for(var i=0;i<result.length;i++) {
				    		dateArr.push(result[i].transDate);
				    		amountArr.push(result[i].actualAmount);
				    	}
				        orderAmountDlyChart.hideLoading(); //隐藏加载动画
				        orderAmountDlyChart.setOption({ //渲染数据 
				            xAxis: {
				            	boundaryGap: false,
				            	axisLabel: {
						        	interval: 0//横轴信息全部显示
								},
				                data: dateArr
				            },
				            series: [{ 
				                name: '销售额（单位：元）',
	                			type: 'line',
				                data: amountArr,
				                itemStyle: { normal: {label : {show: true}}}
				            }] 
				        }); 
			    	}
			    }, 
			    error: function() { 
			        alert("请求数据失败!"); 
			    } 
			});
		}
        // 使用刚指定的配置项和数据显示图表。
        orderAmountDlyChart.setOption(option);
        getOrderAmountDly("SEVEN_DAY");
    </script>
</body>
</html>