<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录</title>
</head>
<body>

<div>
    <h1 style="text-align:center">
        <strong>欢迎使用XX管理系统</strong>
    </h1>
</div>
<div style="width:100%;text-align:center">
    <form action="/MIM_war_exploded/user/checkLogin" method="post">
        <h3 class="text-center" style="text-align:center">用户登录</h3>
        用户名:
        <span style="display:inline-block;width:200px;text-align:center;">
        <input id="username" name="username" type="text"></span><br/>
        密 码:
        <span style="display:inline-block;width:200px;text-align:center;">
        <input id="password" name="password" type="password"></span>
        <br/><br/>
        <input type="submit" value="登录" style="text-align:center">
    </form>
</div>
</body>
</html>
