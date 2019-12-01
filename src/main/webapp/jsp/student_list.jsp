<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>monkey1024</title>
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<div class="container theme-showcase" role="main">

    <div class="page-header">
        <a id="add" type="button" class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/jsp/student_add.jsp">添加</a>
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
                    <th>口号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="stu">
                    <tr>
                        <td>${stu.id}</td>
                        <td>${stu.name}</td>
                        <td>${stu.qq}</td>
                        <td>${stu.jnshuType}</td>
                        <td>${stu.school}</td>
                        <td>${stu.onlineNum}</td>
                        <td>${stu.counsellor}</td>
                        <td>${stu.slogan}</td>
                        <td>
                            <a type="button" class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/jsp/student_update.jsp?id=${stu.id}&slogan=${stu.slogan}">修改</a>
                            <form action="${pageContext.request.contextPath}/student/${stu.id}" method="post" >
                                <input name="_method" type="hidden" value="DELETE">
                                <input type="submit" class="btn btn-sm btn-info" value="删除">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <table border="0" cellspacing="0" cellpadding="0" width="900px">
                <tr>
                    <td class="td2">
                        <span>第${pageInfo.pageNum}/ ${pageInfo.pages}页</span>&nbsp;&nbsp;
                        <span>总记录数：${pageInfo.total}&nbsp;&nbsp;每页显示:${pageInfo.pageSize}</span>&nbsp;&nbsp;
                        <span>
                           <c:if test="${pageInfo.pageNum != 5}">
                               <a href="${pageContext.request.contextPath }/student/">[首页]</a>&nbsp;&nbsp;
                               <a href="${pageContext.request.contextPath }/student/?pageNum=${pageInfo.pageNum-1}">[上一页]</a>&nbsp;&nbsp;
                           </c:if>

                           <c:if test="${pageInfo.pageNum != pageInfo.total}">
                               <a href="${pageContext.request.contextPath }/student/?pageNum=${pageInfo.pageNum+1}">[下一页]</a>&nbsp;&nbsp;
                               <a href="${pageContext.request.contextPath }/student/?pageNum=${pageInfo.total}">[尾页]</a>&nbsp;&nbsp;
                           </c:if>
                        </span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script>
    $(function () {

        $("#query").click(function () {
            $("#queryById").submit();
        })

    });

</script>
</body>
</html>