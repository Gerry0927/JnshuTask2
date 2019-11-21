<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>monkey1024</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<div class="container theme-showcase" role="main">

    ${msg}
    <div class="page-header">
        <form id="queryById" action="/findById.do" method="post">
            <input type="text" name="id" placeholder="请输入id" value="${id}">
            <button id="query" type="button" class="btn btn-sm btn-primary">查询</button>
            <a id="add" type="button" class="btn btn-sm btn-success" href="/jsp/user_add.jsp">添加</a>
        </form>

    </div>
    <div class="row">
        <div class="">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>QQ</th>
                    <th>修真类型</th>
                    <th>毕业学校</th>
                    <th>学号</th>
                    <th>辅导师兄</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allStu}" var="stu">
                    <tr>
                        <td>${stu.id}</td>
                        <td>${stu.name}</td>
                        <td>${stu.qq}</td>
                        <td>${stu.jnshuType}</td>
                        <td>${stu.onlineNum}</td>
                        <td>${stu.counsellor}</td>
                        <td>
                            <a type="button" class="btn btn-sm btn-info" href="/delete.do?id=${user.key}">删除</a>
                            <a type="button" class="btn btn-sm btn-warning" href="/goUpdate.do?id=${user.key}">修改</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script>
    $(function () {

        $("#query").click(function () {
            $("#queryById").submit();
        })

    });

</script>
</body>
</html>