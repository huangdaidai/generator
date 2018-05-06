﻿<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <LINK rel="Bookmark" href="/favicon.ico">
    <LINK rel="Shortcut Icon" href="/favicon.ico"/>

    <#include "${request.contextPath}/view/common/header.ftl">
    <#--<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />-->
    <#--<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />-->
    <#--<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />-->
    <#--<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />-->
    <#--<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />-->

    <style type="text/css">
        .col-xs-8 {
            padding-left: 0;
        }
        .table {
            margin-bottom: 10px;
        }
    </style>
    <title>代码生成配置</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 代码生成 <span
        class="c-gray en">&gt;</span> 用户配置 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div>
        <div class="col-xs-8">
            <table class="table table-border table-bordered table-bg radius ">
                <thead>
                    <tr>
                        <th colspan="7" scope="col">项目基本配置</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="text-c">
                        <td>包名：</td>
                        <td><input name="package" type="text" class="input-text radius size-M" placeholder="com.hdd.autoCreate"/></td>
                    </tr>
                    <tr class="text-c">
                        <td>作者：</td>
                        <td><input name="author" type="text" class="input-text radius size-M" placeholder="hdd"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div>
        <div class="col-xs-8">
            <form id="dataSource">
                <table class="table table-border table-bordered table-bg radius ">
                    <thead>
                        <tr>
                            <th colspan="7" scope="col">
                                数据源配置
                                <span class="radio-box">
                                    <input type="radio" id="radio-1" name="dbType" value="mysql" ><label for="radio-1">mysql</label>
                                    &nbsp;
                                    <input type="radio" id="radio-2" name="dbType" value="oracle" checked><label for="radio-2">oracle</label>
                                </span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="text-c">
                            <td>URL</td>
                            <td><input name="url" type="text" class="input-text radius size-M" value="58.62.207.50"/></td>
                        </tr>
                        <tr class="text-c">
                            <td>端口</td>
                            <td><input name="port" type="text" class="input-text radius size-M" value="5352"/></td>
                        </tr>
                        <tr class="text-c">
                            <td>数据库名称 </td>
                            <td><input name="dbName" type="text" class="input-text radius size-M" value="dkdz"/></td>
                        </tr>
                        <tr class="text-c">
                            <td>账号</td>
                            <td><input name="userName" type="text" class="input-text radius size-M" value="db_dkdz_test"/></td>
                        </tr>
                        <tr class="text-c">
                            <td>密码</td>
                            <td><input name="password" type="text" class="input-text radius size-M" value="foresee"/></td>
                        </tr>
                        <tr class="text-c">
                            <td colspan="2">
                                <a class="btn btn-secondary radius" onclick="loadTables()">确定</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div class="col-xs-4">
            <p class="text-l va-m">
                若无外网数据库，可使用本站公用数据库进行体验在线生成代码。<br>
                URL: 192.168.0.1:3306<br>
                username: root<br>
                password: root<br>
                <strong>注意：从安全角度考虑，本数据库仅用于体验代码生成器，不可将实际项目表结构导入本数据库，因此导致数据泄露，本站概不负责！</strong>
            </p>
        </div>
    </div>
    <div>
        <div class="col-xs-8">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                <tr>
                    <th colspan="2">
							<span class="l">
								<a class="btn btn-primary radius" data-title="添加父级菜单"
                                   onclick="addParentMenu()" href="javascript:;"><i
                                        class="Hui-iconfont">&#xe600;</i> 添加父级菜单</a>
							</span>
                    </th>
                </tr>
                <tr class="text-c">
                    <th>父级菜单名称</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="parentMenuTbody">

                </tbody>
            </table>
        </div>
    </div>
    <div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                <tr>
                    <th colspan="6">
							<span class="l">
								<a class="btn btn-primary radius" data-title="添加菜单"
                                   onclick="page_add('添加菜单','dialog/page-add.html')" href="javascript:;"><i
                                        class="Hui-iconfont">&#xe600;</i> 添加子菜单</a>
							</span>
                        <span class="r">共有菜单数据：<strong>3</strong> 条</span>
                    </th>
                </tr>
                <tr class="text-c">
                    <th>菜单名称</th>
                    <th>父菜单名称</th>
                    <th>数据来源（表名）</th>
                    <th>列名</th>
                    <th>操作权限</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr class="text-c">
                    <td>
                        <a>用户管理</a>
                    </td>
                    <td>系统管理</td>
                    <td>user/role</td>
                    <td>用户名/角色/注册时间</td>
                    <td>批量删除/添加/删除/编辑</td>
                    <td class="f-14 td-manage"><a style="text-decoration:none" onClick="article_stop(this,'10001')"
                                                  href="javascript:;" title="下架"><i
                            class="Hui-iconfont">&#xe6de;</i></a> <a style="text-decoration:none" class="ml-5"
                                                                     onClick="article_edit('资讯编辑','article-add.html','10001')"
                                                                     href="javascript:;" title="编辑"><i
                            class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5"
                                                                     onClick="article_del(this,'10001')"
                                                                     href="javascript:;" title="删除"><i
                            class="Hui-iconfont">&#xe6e2;</i></a></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="../js/common/common.js"></script>
</body>
<script type="text/javascript">
    var tableDetail;
    var parentMenu=[];
    function page_add(title, url) {
        layer.open({
            type: 2,
            area: ['80%', '85%'],
            fix: false, //不固定
            maxmin: true,
            shade: 0.4,
            title: title,
            content: url
        });
    }

    function addParentMenu(){
        var trHtml="<tr class=\"text-c\">" +
            "<td><input type=\"text\" name=\"parentMenuName\" onchange='parentMenuNameChange()' class=\"input-text radius size-M\" placeholder=\"请输入父级菜单名称\"/></td>\n" +
            "<td class=\"f-14 td-manage\">" +
            "<a style=\"text-decoration:none\" class=\"ml-5\"   onClick=\"deleteParentMenu(this)\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>" +
            "</td></tr>";
       $("#parentMenuTbody").append(trHtml);
    }
    function deleteParentMenu(a) {
        $(a).parents("tr").remove();
        parentMenuNameChange();
    }
    function parentMenuNameChange(){
        parentMenu=[];
        $("input[name='parentMenuName']").each(function (index,item) {
            parentMenu[index]=$(this).val();
        });
        console.info(parentMenu);
    }
    function loadTables() {
    	var param=autoCreate.common.getFormAsJson("dataSource");
    	autoCreate.common.ajax(rootPath + "userConfig/loadTables",param,true,function(result){
            tableDetail=JSON.parse(result);
            if (tableDetail.code == '-1'){
                layer.alert(tableDetail.msg);
            }else {
                tableDetail.each(function (t) {

                });
            }
    	});
    }
    

</script>
</html>