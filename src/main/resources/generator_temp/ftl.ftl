<%@ page language="java" import="java.util.*" pageEncoding="utf-8" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
    <title>${tableClass.className}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${r'${'}pageContext.request.contextPath${r'}'}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${r'${'}pageContext.request.contextPath${r'}'}/static/bootstraptable/bootstrap-table.min.css">
</head>

<body>
<script type="text/javascript" src="${r'${'}pageContext.request.contextPath${r'}'}/static/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${r'${'}pageContext.request.contextPath${r'}'}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${r'${'}pageContext.request.contextPath${r'}'}/static/bootstraptable/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${r'${'}pageContext.request.contextPath${r'}'}/static/bootstraptable/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="${r'${'}pageContext.request.contextPath${r'}'}/static/authscript/${tableClass.className}Script.js"></script>
<div class="container" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-12">
            <p>
                <button type="button" class="btn btn-success" onclick="create()">添加</button>
                <button type="button" class="btn btn-danger"onclick="del()">删除</button>
            </p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="tableGridView">

            </table>
        </div>
    </div>
</div>


</body>
</html>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="rue">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改</h4>
            </div>
            <div class="modal-body">
                <input type="text" hidden id="myAjaxType">
                <form class="form-horizontal" role="form" id="myForm">
                    <#list fields as f>
                        <div class="form-group">
                            <label for="${f.field}" class="col-sm-2 control-label">${(f.comment=='')?string(f.field,f.comment)}</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="${f.field}" id="${f.field}" placeholder="请输入${(f.comment=='')?string(f.field,f.comment)}">
                            </div>
                        </div>
                    </#list>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="save()">保存</button>
            </div>
        </div>
    </div>
</div>
