var generator={};
var rootPath ; //项目路径
generator.common = {
	/**
	 * ajax请求
	 * @param url
	 * @param data
	 * @param async
	 * @param callback
	 */
	ajax : function(url, param, async, callback) {
		if (async == "false" || async == false) {
			async = false;
		} else {
			async = true;
		}
		$.ajax({
			type : "POST",
			url : url,
			data : param,
			dataType : "json",
			contentType: 'application/json;charset=utf-8',
			async : async,
			beforeSend : this.beforeSend,
			complete : function() {
			},
			success : function(result) {
				if (typeof callback == 'function') {
					bl = callback.call(this, result);
				}
			},
			error : function(xhr, errorMsg, errorThrown) {
				
			}
		});
	},
	/**
	 * form表单转jsonString
	 * @param id
	 * @returns
	 */
	getFormAsJson: function(id){
		var formObject = {};
        var formArray = $("#"+id).serializeArray();
        $.each(formArray, function (i, item) {
            formObject[item.name] = item.value;
        });
        return JSON.stringify(formObject);
	},
    /**
     * 取得项目路径
     * @author wul
     */
    getRootPath:function () {
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
}
rootPath = generator.common.getRootPath();