<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {

        $('#problemAddForm').form({
            url : '${path }/problem/add',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="problemAddForm" method="post" enctype="multipart/form-data">
            <table class="grid">
                <tr>
                    <td>产品名称</td>
                    <td><input name="productName" type="text" placeholder="请输入产品名称" class="easyui-validatebox" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>产品分类</td>
                    <td>
                    <select name="categoryId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                         <option value=""  selected="selected">--请选择--</option>
                            <option value="1">减速机</option>
                            <option value="2">机械手臂</option>
                            <option value="3">动力刀塔</option>
                            <option value="4">齿轮齿条</option>
                     </select>
                    </td>
                </tr>
                <tr>
                    <td>上传图片</td>
                    <td><input type="file" name="image" data-options="required:true"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>