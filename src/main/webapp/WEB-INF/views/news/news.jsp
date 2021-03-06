<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品管理</title>
<script type="text/javascript">
var dataGrid;
$(function(){
	
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/news/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '180',
                title : '标题',
                field : 'title'
            }, {
                width : '180',
                title : '内容',
                field : 'content'
            },{
                width : '180',
                title : '时间',
                field : 'createTime'
            }, {
                field : 'action',
                title : '操作',
                width : 130,
                formatter : function(value, row, index) {
                	var str = '';
	                    <shiro:hasPermission name="/news/edit">
	                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
	                    </shiro:hasPermission>
	                    <shiro:hasPermission name="/news/delete">
	                        str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
	                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
	                    </shiro:hasPermission>
               		 return str;
                }
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
            toolbar : '#toolbar'
        });
});
    
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path }/news/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;// 因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#newsAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function deleteFun(id) {
        if (id == undefined) {// 点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {// 点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除这条记录？', function(b) {
            if (b) {
	             progressLoad();
	             $.post('${path }/news/delete', {
	                 id : id
	             }, function(result) {
	                 if (result.success) {
	                     parent.$.messager.alert('提示', result.msg, 'info');
	                     dataGrid.datagrid('reload');
	                 }
	                 progressClose();
	             }, 'JSON');
                }
        });
    }
    
    function editFun(id) {
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 300,
            href : '${path }/news/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;// 因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#newsEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
    function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:true,title:'产品列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
        <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    </div>
</body>
</html>