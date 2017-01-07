<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {

        $('#appCaseAddForm').form({
            url : '${path }/appCase/add',
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
        <form id="appCaseAddForm" method="post" enctype="multipart/form-data">
            <table class="grid">
                <tr>
                    <td>案例名称</td>
                    <td><input name="applicationCaseName" type="text" placeholder="请输入案例名称" class="easyui-validatebox" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>案例介绍</td>
                    <td><textarea name="applicationCaseInfo" placeholder="请输入案例介绍" data-options="required:true" class="easyui-validatebox" rows="3" cols="40"></textarea></td>
                </tr>
                <tr>
                    <td>案例类别</td>
                    <td>
                    <select name="applicationCaseCategory" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                         <option value=""  selected="selected">--请选择--</option>
                            <option value="1">机床行业应用案例</option>
                            <option value="2">印刷行业应用案例</option>
                            <option value="3">包装行业应用案例</option>
                            <option value="4">医疗行业应用案例</option>
                            <option value="5">食品行业应用案例</option>
                            <option value="6">雕刻行业应用案例</option>
                            <option value="7">金属加工行业应用案例</option>
                     </select>
                    </td>
                </tr>
                <tr>
                    <td>上传图片1</td>
                    <td><input type="file" name="image1"/></td>
                </tr>
                <tr>
                    <td>上传图片2</td>
                    <td><input type="file" name="image2"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>