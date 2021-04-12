<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户管理列表</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<script>
    // function deleteById() {
    //     var id = getCheckId()
    //     if(id) {
    //         if(confirm("你确认要删除此条记录吗？")) {
    //             location.href="/system/user/delete.do?id="+id;
    //         }
    //     }else{
    //         alert("请勾选待处理的记录，且每次只能勾选一个")
    //     }
    // }

    // function roleList() {
    //     var id = getCheckId()
    //     if(id) {
    //         location.href="/system/user/roleList.do?id="+id;
    //     }else{
    //         alert("请勾选待处理的记录，且每次只能勾选一个")
    //     }
    // }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>用户管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">用户列表</h3>
        </div>
        <div class="box-body">
            <div class="table-box">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/admin/user/toAddPage"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            <button type="button" class="btn btn-default" title="角色" onclick="roleList()"><i class="fa fa-user-circle-o"></i> 角色</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;"> </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">用户名</th>
                        <th class="sorting">别名</th>
                        <th class="sorting">联系电话</th>
                        <th class="sorting">邮箱</th>
                        <th class="sorting">性别</th>
                        <th class="sorting">年龄</th>
                        <th class="sorting">角色</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="item" varStatus="status">
                        <tr>
                            <td><input name="ids" value="${item.id}" type="checkbox"></td>
                            <td>${status.index+1}</td>
                            <td><a href="/system/user/toUpdata.do?id=${item.id}">${item.username}</a></td>
                            <td>${item.nickName }</td>
                            <td>${item.phone }</td>
                            <td>${item.email }</td>
                            <td>${item.gender ==1?'男':'女'}</td>
                            <td>${item.age}</td>
                            <td>${item.role}</td>
                            <th class="text-center">
                                <button type="button" class="btn bg-olive btnxs" onclick='location.href="/admin/user/toUpdatePage?id=${item.id}"'>编辑</button>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/admin/user/list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>
<script>
    function deleteById() {
        var id=getCheckId()
        if (id){
            if(confirm("你确定要删除此条记录吗")){
                location.href="${pageContext.request.contextPath}/admin/user/delete?id="+id
            }else {
                alert("只能勾选一个");
            }
        }
    }
</script>
</html>
