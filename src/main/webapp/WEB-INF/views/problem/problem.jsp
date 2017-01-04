<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户问题列表</title>
<script type="text/javascript">
var dataGrid;
$(function(){
        dataGrid = $('#dataGrid').datagrid({
            url : '${path}/problem/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '150',
                title : '公司名称',
                field : 'corporateName'
            }, {
                width : '200',
                title : '公司地址',
                field : 'address'
            },{
                width : '150',
                title : '联系人',
                field : 'contacts'
            },{
                width : '50',
                title : '城市',
                field : 'city'
            }, {
                width : '50',
                title : '国家',
                field : 'country'
            }, {
                width : '80',
                title : '公司网页',
                field : 'companyWebsite'
            }, {
                width : '50',
                title : 'Email',
                field : 'email'
            }, {
                width : '80',
                title : '公司电话',
                field : 'tel'
            }, {
                width : '80',
                title : '传真',
                field : 'fax'
            }, {
                width : '180',
                title : '有关问题',
                field : 'problem'
            }, {
                width : '180',
                title : '问题描述',
                field : 'problemDescription'
            }, {
                field : 'action',
                title : '操作',
                width : '60',
                formatter : function(value, row, index) {
                	var str = '';
	                   // <shiro:hasPermission name="/problem/edit">
	                   //     str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
	                  //  </shiro:hasPermission>
	                    <shiro:hasPermission name="/problem/delete">
	                     //   str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
	                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
	                    </shiro:hasPermission>
               		 return str;
                }
            }] ],
            onLoadSuccess:function(data){
              //  $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
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
            href : '${path }/problem/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;// 因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#problemAddForm');
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
	             $.post('${path }/problem/delete', {
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
            href : '${path }/problem/problemPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;// 因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#problemEditForm');
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
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchForm">
            <table>
                <tr>
                    <th>公司名称:</th>
                    <td><input name="corporateName" placeholder="请输入公司名称"/></td>
                    <th>相关问题:</th>
                    <td>
                    <input name="problem" placeholder="请输入相关问题"/>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'客户问题列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
   <!--  <div id="toolbar" style="display: none;">
        <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    </div> -->
</body>
</html>