<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {

        $('#productEditForm').form({
            url : '${path }/product/edit',
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
        <form id="productEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>产品名称</td>
                    <td><input name="id" type="hidden"  value="${product.id}"/>
                    <input name="productName" type="text" placeholder="请输入产品名称" class="easyui-validatebox" data-options="required:true" value="${product.productName }"></td>
                </tr>
                <tr>
                    <td>产品分类</td>
                    <td>
                    <select id="categoryId" name="categoryId" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="1">减速机</option>
                            <option value="2">机械手臂</option>
                            <option value="3">动力刀塔</option>
                            <option value="4">齿轮齿条</option>
                     </select>
                    </td>
                </tr>
                 <tr>
                    <td>产品概要</td>
                    <td>
                    <textarea name="overview" placeholder="请输入内容" class="easyui-validatebox" data-options="required:true" rows="3" cols="40"><c:out value="${product.overview }"></c:out></textarea>
                    </td>
                </tr>
                <tr>
                    <td>核心参数</td>
                    <td>
                    <textarea name="coreParameter" placeholder="请输入内容" class="easyui-validatebox" data-options="required:true" rows="3" cols="40"><c:out value="${product.coreParameter }"></c:out></textarea>
                    </td>
                </tr>
                <tr>
                    <td>图片地址</td>
                    <td><input type="text" name="imageAddress" data-options="required:true" value="${product.imageAddress }"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>