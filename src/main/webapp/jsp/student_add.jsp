<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <form action="${pageContext.request.contextPath}/student/" method="post" style="max-width: 330px;padding: 15px;margin: 0 auto;">
        <div class="form-group">
            <label for="name">姓名:</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="qq">qq:</label>
            <input type="text" class="form-control" id="qq" name="qq">
        </div>
        <div class="form-group">
            <label for="school">毕业学校:</label>
            <input type="text" class="form-control" id="school" name="school">
        </div>
        <div class="form-group">
            <label for="jnshuType">修真类型:</label>
            <input type="text" class="form-control" id="jnshuType" name="jnshuType">
        </div>
        <div class="form-group">
            <label for="onlineNum">学号:</label>
            <input type="text" class="form-control" id="onlineNum" name="onlineNum">
        </div>

        <div class="form-group">
            <label for="counsellor">辅导师兄:</label>
            <input type="text" class="form-control" id="counsellor" name="counsellor">
        </div>
        <div class="form-group">
            <label for="slogan">口号:</label>
            <input type="text" class="form-control" id="slogan" name="slogan">
        </div>
        <input type="submit" value="提交">
    </form>
</div>
<script src="${pageContext.request.contextPath}/static/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>