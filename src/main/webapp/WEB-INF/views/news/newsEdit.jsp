<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {

        $('#newsEditForm').form({
            url : '${path }/news/edit',
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
        <form id="newsEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>产品名称</td>
                    <td><input name="id" type="hidden"  value="${news.id}"/>
                    <input name="title" type="text" placeholder="请输入产品名称" class="easyui-validatebox" data-options="required:true" value="${news.title }"></td>
                </tr>
                <tr>
                    <td>内容</td>
                    <td>
                    <textarea name="content" placeholder="请输入内容" class="easyui-validatebox" data-options="required:true"><c:out value="${news.content }"></c:out></textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>