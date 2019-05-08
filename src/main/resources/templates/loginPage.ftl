<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>登录</title>
</head>

<body>
<div >
    校友管理系统登录
</div>
<div >
    <form name="logBox" id="logBox" method="post" action="/login">
        <div>
            <input type="text" name="account"  id="account" placeholder="用户名">
        </div>
        <div>
            <input type="password" name="password" id="password" autocomplete="off" placeholder="密码">
        </div>
        <p><input type="submit" name="Submit" value="登录">  </p>
    </form>
</div>
</body>
</html>