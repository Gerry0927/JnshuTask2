<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
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
<div class="page-header"></div>
<div class="container">
    <form action="${pageContext.request.contextPath}/student/${param.id}" method="post" style="max-width: 330px;padding: 15px;margin: 0 auto;">
        <input name="id" type="hidden" value="${param.id}">
        <input name="_method" type="hidden" value="PUT">
        <div class="form-group">
            <label for="slogan">口号:</label>
            <input type="text" class="form-control" id="slogan" name="slogan" value="${param.slogan}">
        </div>
        <input type="submit" value="修改">
    </form>
</div>
<script src="${pageContext.request.contextPath}/static/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>