﻿/**
 * 引用JS和CSS头文件
 */
var rootPath = getRootPath(); //项目路径

/**
 * 动态加载CSS和JS文件
 */
var dynamicLoading = {
    meta : function(){
        document.write('<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">');
    },
    css: function(path){
        if(!path || path.length === 0){
            throw new Error('argument "path" is required!');
        }
        document.write('<link rel="stylesheet" type="text/css" href="' + path + '">');
    },
    js: function(path, charset){
        if(!path || path.length === 0){
            throw new Error('argument "path" is required!');
        }
        document.write('<script charset="' + (charset ? charset : "utf-8") + '" src="' + path + '"></script>');
    }
};

/**
 * 取得项目路径
 * @author wul
 */
function getRootPath() {
    //取得当前URL
    var path = window.document.location.href;
    //取得主机地址后的目录
    var pathName = window.document.location.pathname;
    var post = path.indexOf(pathName);
    //取得主机地址
    var hostPath = path.substring(0, post);
    //取得项目名
    var name = pathName.substring(0, pathName.substr(1).indexOf("/") + 1);
    return hostPath + name + "/";
}

//动态生成meta
dynamicLoading.meta();

//动态加载项目 css文件
dynamicLoading.css(rootPath + "/static/h-ui/css/H-ui.min.css");
dynamicLoading.css(rootPath + "/static/h-ui.admin/css/H-ui.admin.css");
dynamicLoading.css(rootPath + "/lib/Hui-iconfont/1.0.8/iconfont.css");
dynamicLoading.css(rootPath + "/static/h-ui.admin/skin/default/skin.css");
dynamicLoading.css(rootPath + "/static/h-ui.admin/css/style.css");

