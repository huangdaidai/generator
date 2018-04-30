$(document).ready(function () {
    initTble();
});

(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

function initTble() {
    $('#tableGridView').bootstrapTable({
        method: 'get',
        url: '../${tableClass.className?lower_case}',
        striped: true,
        cache: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        sidePagination: "client",
        search: true,
        uniqueId: "id",
        clickToSelect: true,
        columns: [{
            field: 'check',
            checkbox: true,
            formatter: function (value, row, index) {
                return false;
            }
        },
        <#list fields as f>
        {
            field: '${f.field}',
            title: '${(f.comment=='')?string(f.field,f.comment)}',
            align: 'center'
        },
        </#list>
        {
            field: 'edit',
            title: '修改',
            align: 'center',
            formatter: function (value, row, index) {
                var a = '<button class="btn btn-default" onclick="edit(' + row.id + ')">修改</button>';
                return a;
            }
        }],
        onDblClickCell:function(field, value, row, $element){
            edit(row.id);
        }
    });
}

function edit(id) {
    var row=$('#tableGridView').bootstrapTable('getRowByUniqueId', id);
    $('#myAjaxType').val("put");
    $('#myModalLabel').val("修改");
    <#list fields as f>
        $('#${f.field}').val(row.${f.field});
    </#list>
    $('#editModal').modal('show');
}

function create() {
    $('#myAjaxType').val("post");
    $('#myModalLabel').val("添加");
    <#list fields as f>
        $('#${f.field}').val('');
    </#list>
    $('#editModal').modal('show');
}

function save() {
    var json=$('#myForm').serializeJson();
    console.log(toJSONString(json));
    $.ajax({
        type:$('#myAjaxType').val(),
        url:"../${tableClass.className?lower_case}",
        contentType:'application/json',
        data:JSON.stringify(json),
        success:function(result){
            console.log(result);
            $('#editModal').modal('hide');
            $('#tableGridView').bootstrapTable('refresh');
        }
    });
}


function del() {
    var rows=$('#tableGridView').bootstrapTable('getSelections');
    var params="[";
    for(var i=0;i<rows.length;i++){
        params+=rows[i].id+",";
    }
    params=params.substring(0,params.length-1)+"]";
    console.log(params);
    $.ajax({
        type:'delete',
        url:"../${tableClass.className?lower_case}",
        contentType:'application/json',
        data:params,
        success:function(result){
            console.log(result);
            $('#tableGridView').bootstrapTable('refresh');
        }
    });
}

function toJSONString(jsonObj) {
    var jStr = "{";
    for(var item in jsonObj){
        jStr += "'"+item+"':'"+jsonObj[item]+"',";
    }
    return jStr.substring(0,jStr.length-1)+'}';
}