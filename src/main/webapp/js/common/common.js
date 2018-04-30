autoCreate.common = {
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
	}
	
}