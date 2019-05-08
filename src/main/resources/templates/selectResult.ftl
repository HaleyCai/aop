<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>查询结果</title>
</head>

<body>
<a>this is select result</a>
<br>
<br>
<#if stuList?exists>
<table id="students">
    <tr>
        <td>姓名</td>
        <td>性别</td>
        <td>生日</td>
        <td>入学年份</td>
        <td>毕业年份</td>
        <td>工作城市</td>
        <td>工作单位</td>
        <td>岗位</td>
        <td>手机</td>
        <td>微信</td>
        <td>邮箱</td>
    </tr>
    <#list stuList as thisUser>
        <tr>
            <td >${thisUser.getStudentName()}</td>
            <td >${thisUser.getGender()}</td>
            <td >${thisUser.getBirthday()}</td>
            <td >${thisUser.getEnterYear()}</td>
            <td >${thisUser.getGraduateYear()}</td>
            <td >${thisUser.getCity()}</td>
            <td >${thisUser.getWorkplace()}</td>
            <td >${thisUser.getJob()}</td>
            <td >${thisUser.getPhone()}</td>
            <td >${thisUser.getWechat()}</td>
            <td >${thisUser.getEmail()}</td>
        </tr>
    </#list>
</table>
<#else > <a>无查询结果</a>
</#if>
</body>
</html>