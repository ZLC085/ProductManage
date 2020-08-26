<%--
  Created by IntelliJ IDEA.
  User: ManagerZhang
  Date: 2019/8/20
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%-- 单个文件 --%>
    <form action="${ctx}/con/upload" method="post" enctype="multipart/form-data">
        上传文件：<input type="file" name="file"><br>
        <input type="hidden" id="id" name="id" value="12"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
