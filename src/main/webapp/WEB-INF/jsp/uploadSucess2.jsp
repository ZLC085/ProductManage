<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ManagerZhang
  Date: 2019/8/20
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
上传成功！文件名：
<c:forEach items="${fileNames}" var="obj">
    <h1>${obj}</h1>
</c:forEach>
</body>
</html>
