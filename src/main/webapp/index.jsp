<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员中心</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
</head>
<body>
<div class="topbar-wrap white">
    <!--<div class="topbar-inner clearfix">-->
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">管理员中心</a></h1>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="administer-分类管理（标签）.html"><i class="icon-font">&#xe008;</i>订单导入</a></li>
                        <li><a href="administer-公告发布.html"><i class="icon-font">&#xe005;</i>公告发布</a></li>
                        <li><a href="administer-订单查询.html"><i class="icon-font">&#xe006;</i>订单查询</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul class="sub-menu">
                        <li><a href="system.html"><i class="icon-font">&#xe017;</i>系统设置</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe037;</i>清理缓存</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe046;</i>数据备份</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe045;</i>数据还原</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html" color="#white">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">订单查询</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="search-sort" id="">
                                    <option value="">全部</option>
                                    <option value="19">运动健身</option>
                                     <option value="20">旅游</option>
                                    <option value="21">文学艺术</option>
                                    <option value="22">演讲</option>
                                    <option value="23">经济</option>
                                    <option value="24">电影</option>
                                    <option value="25">科技</option>
                                    <option value="26">美食</option>
                                </select>
                            </td>
                            <th width="70">关键字:</th>
                            <td><input class="common-text" placeholder="关键字" name="keywords" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"></th>
                            <th>标签ID</th>
                            <th>课程名称</th>
                            <th>购买者用户名</th>
                            <th>销售者用户名</th>
                            <th>订单时间</th>
                            <th>课程价格</th>
                        </tr>
                        <tr>
                            <td class="tc"><input name="id[]" value="" type="checkbox"></td>
                            <td>01</td> <!--标签ID-->
                            <td><a target="_blank" href="中国传统人生智慧.html">中国传统人生智慧</a> <!--课程名称-->
                            </td>
                            <td>张</td> <!--购买者用户名-->
                            <td>王</td> <!--销售者用户名-->
                            <td>2014-03-15 21:11:01</td> <!--订单时间-->
                            <td>129元</td> <!--课程价格-->
                        </tr>
                        <tr>
                            <td class="tc"><input name="id[]" value="" type="checkbox"></td>
                            <td>02</td> <!--标签ID-->
                            <td><a target="_blank" href="科学究竟是什么.html">科学究竟是什么</a> <!--课程名称-->
                            </td>
                            <td>张</td> <!--购买者用户名-->
                            <td>王</td> <!--销售者用户名-->
                            <td>2014-03-25 21:11:01</td> <!--订单时间-->
                            <td>99元</td> <!--课程价格-->
                        </tr>
                    </table>
                    <div class="list-page"> 2 条 1/1 页</div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>