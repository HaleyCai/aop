<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>系统首页</title>
</head>
<script type="text/javascript">
    var account = "${account}"
    var role = "${role}"
    console.log(account +"===" +role)

    function select() {
        window.open("/select1?account="+account+"&role="+role)
    }

    function create() {
        window.open("/create1?account="+account+"&role="+role)
    }

    function update() {
        window.open("/update1?account="+account+"&role="+role)
    }

    function deletes() {
        window.open("/delete1?account="+account+"&role="+role)
    }

    function count() {
        window.open("/count1?account="+account+"&role="+role)
    }

    function down() {
        window.open("/download1?account="+account+"&role="+role)
    }
</script>
<div>
    <button>用户名  ：${account}</button>
    <button>用户权限：${role}</button>
</div>
<br>
<div>
    <a onclick="select()"><button>查询</button></a>
</div>
<br>
<div>
    <a onclick="create()"><button>创建</button></a>
</div>
<br>
<div>
    <a onclick="update()"><button>更新</button></a>
</div>
<br>
<div>
    <a onclick="deletes()"><button>删除</button></a>
</div>
<br>
<div>
    <a onclick="count()"><button>汇总</button></a>
</div>
<br>
<div>
    <a onclick="down()"><button>下载</button></a>
</div>

<body>

</body>
</html>