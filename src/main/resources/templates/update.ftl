<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>更新</title>
</head>

<body>

<a>this is update</a>
<br>
<#if (s == "null")>
    <a>无查询结果，请重新输入条件选择</a>
<#else>
    <form name="updateBox" id="updateBox" method="post" action="/update">
        <input type="hidden" name="id" id="id" value="${s.getId()}">
        <div>
            <a>姓名    </a><input type="text" name="studentName"  id="studentName" value="${s.getStudentName()}">
        </div>
        <div>
            <a>性别    </a><input type="text" name="gender"  id="gender" value="${s.getGender()}">
        </div>
        <div>
            <a>生日    </a><input type="text" name="birthday"  id="birthday" value="${s.getBirthday()}">
        </div>
        <div>
            <a>入学年份</a><input type="text" name="enterYear"  id="enterYear" value="${s.getEnterYear()}">
        </div>
        <div>
            <a>毕业年份</a><input type="text" name="graduateYear"  id="graduateYear" value="${s.getGraduateYear()}">
        </div>
        <div>
            <a>工作城市</a><input type="text" name="city"  id="city" value="${s.getCity()}">
        </div>
        <div>
            <a>工作单位</a><input type="text" name="workplace"  id="workplace" value="${s.getWorkplace()}">
        </div>
        <div>
            <a>职业    </a><input type="text" name="job"  id="job" value="${s.getJob()}">
        </div>
        <div>
            <a>电话    </a><input type="text" name="phone"  id="phone" value="${s.getPhone()}">
        </div>
        <div>
            <a>微信    </a><input type="text" name="wechat"  id="wechat" value="${s.getWechat()}">
        </div>
        <div>
            <a>邮箱    </a><input type="text" name="email"  id="email" value="${s.getEmail()}">
        </div>
        <p><input type="submit" name="Submit" value="修改"></p>
    </form>
</#if>

</body>
</html>