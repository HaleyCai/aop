<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>汇总</title>
</head>

<body>

<a>this is count</a>
<br>
<form name="countBox" id="countBox" action="/count">
    <div>
        <input type="text" name="item"  id="item" placeholder="查询项目">
    </div>
    <div>
        <input type="text" name="value" id="value" placeholder="查询条件">
    </div>
    <p><input type="submit" name="Submit" value="统计"></p>
</form>
<div>
    <h3>统计结果：共有${num}条记录</h3>
</div>
</body>
</html>