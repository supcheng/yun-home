<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云+ 房屋用户注册</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/index/lib/layer/css/login1.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
<!--导入jquery-->
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="qiqiu1 qiqiu">
    <img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q2.png"/>
    <div class="text">love</div>
</div>
<div class="qiqiu2 qiqiu">
    <img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q3.png"/>
    <div class="text">love</div>
</div>
<div class="qiqiu3 qiqiu">
    <img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q4.png"/>
    <div class="text">love</div>
</div>
<div class="qiqiu4 qiqiu">
    <img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q5.png"/>
    <div class="text">love</div>
</div>
<div class="qiqiu5 qiqiu">
    <img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q6.png"/>
    <div class="text">love</div>
</div>

<div id="pop_body" style="display: block;">
    <div class="bg_mask"></div>
    <div class="overflow"></div>
    <div class="Top_links" style="margin-top: 25px"><a class="backHome" href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
        <a class="goToLogin" href="${pageContext.request.contextPath}/user/toLoginPage">已有账号？去登录</a>
    </div>
    <form id="registerForm" action="${pageContext.request.contextPath}/user/register" method="post">
        <div class="error_msg phonenum">${msg}</div>
        <div id="mask_body" style="top: 70px;">
            <img style="margin-top: -35px;" class="logo5 customization_elements" src="${pageContext.request.contextPath}/img/hopu_logo.png">
            <div class="mask_body_item phonenum_div">
                <input class="pop_input" type="text"
                       id="telephone"  name="phone"  placeholder="手机号" maxlength="11" required style="border-color: rgb(235, 235, 235);" onblur="checkPhone()">
                <span id="mask_body_item_getcode" class="baseColorFt  disabled" onclick="sendSmsCode()">获取动态码</span>
                <%--<span id="mask_body_item_getcode" class="baseColorFt disabled">42s后重新获取</span>--%>
            </div>
            <div class="error_msg phonenum"><span class="msgIcon"></span><label class="msgColor" id="phoneErrorMsg"></label>
            </div>

            <div class="mask_body_item validcode_div">
                <input class="pop_input" type="text" id="smsCode"
                       name="code"  placeholder="动态码" maxlength="6" style="border-color: rgb(235, 235, 235);" required onblur="checkSmsCode()"></div>
            <div class="error_msg validcode"><span class="msgIcon"></span><label class="msgColor" id="codeErrorMsg"></label>
            </div>

            <div class="mask_body_item"><input class="pop_input" type="text" name="username" id="username"
                                               placeholder="账号名" maxlength="20" required onblur="checkUsername()"></div>
            <div class="error_msg username"><span class="msgIcon"></span><label class="msgColor" id="usernameErrorMsg"></label></div>
            <div class="slidecode">
                <div id="slidecodediv"></div>
            </div>

            <div class="mask_body_item">
                <input class="pop_input " id="password" type="password" autocomplete="new-password" name="password" placeholder="密码" onblur="checkPassword()">
                <div class="error_msg password"><span class="msgIcon"></span><label class="msgColor" id="passwordErrorMsg"></label>
                </div>
            </div>

            <div class="mask_body_item2">
                <label>性别：</label>
                <input style="-webkit-appearance: radio;" class="line-radio" type="radio" name="gender" value="1" checked>男
                <input style="-webkit-appearance: radio;" class="line-radio" type="radio" name="gender" value="0">女
            </div>

            <div class="mask_body_item">
                <input class="pop_input" type="number" name="age" placeholder="年龄" maxlength="3" required>
            </div>
            <div class="error_msg ">
                <span class="msgIcon"></span><label class="msgColor">请输入年龄</label>
            </div>

            <div class="protocol">注册即同意
                <a target="_blank" class="baseColorFt" href="javascript:void(0)">《厚溥云租房使用协议》</a>&amp;
                <a target="_blank" class="baseColorFt" href="javascript:void(0)">《隐私政策》</a>
            </div>
            <div class="mask_body_item login_item baseColorBg" style="margin-bottom: 15px;height: 20px">
                <button id="mask_body_item_login" onclick="checkForm()">确定</button>
            </div>
            <div class="links_botton">
                <a>© myhopu.com</a>
                <a href="javascript:void(0)">联系客服</a>
                <a href="javascript:void(0)">帮助</a>
                <div class="clear"></div>
            </div>
        </div>
    </form>

</div>

</body>

<script>
    // 用户名校验
    function checkUsername() {
        // 先清空之前的错误提示
        $("#usernameErrorMsg").html("");
        // 获取用户输入的用户名值
        let username = $("#username").val();

        // 1、先对用户名进行js正则校验
        let usernamereg = /^[A-Za-z0-9]{4,15}$/;
        if(!usernamereg.test(username)){
            // 表示用户名不匹配
            $("#usernameErrorMsg").html("用户名必须是4~15位的字母和数字组成！");
            $(".username").css("display","block");
            return false;
        }
        // 2、再对用户名进行重名校验
        // 定义标记，对查询结果进行标记；同时声明同步请求，避免异步进入下一个方法
        let flag=true;
        $.get({
            url: "${pageContext.request.contextPath}/user/checkUserName?username="+username,
            success:function(resp){
                if(resp=="1"){
                    $("#usernameErrorMsg").html("该用户名太受欢迎了，请重新注册！");
                    $(".username").css("display","block");
                    flag= false;
                }else {
                    $(".username").css("display","none");
                    flag= true;
                }
            },
            async:false
        });
        return flag;
    }

    // 密码校验
    function checkPassword() {
        // 先清空之前的错误提示
        $("#passwordErrorMsg").html("");
        // 获取用户输入的用户名值
        let password = $("#password").val();

        // 对用户输入的密码进行正则校验
        let pwdreg = /^[A-Za-z0-9]{5,10}$/;
        if(!pwdreg.test(password)){
            // 提出提示
            $("#passwordErrorMsg").html("密码必须是5~10位的字母和数字组成！");
            $(".password").css("display","block");
            // 返回，阻止继续向下执行
            return false;
        }else {
            $(".password").css("display","none");
            return true;
        }
    }

    // 手机号码校验
    function checkPhone() {
        // 获取用户输入的用户名值
        let telephone = $("#telephone").val();

        // 对用户输入的密码进行正则校验
        let phonereg = /^1(3|4|5|6|7|8|9)\d{9}$/;
        if(!phonereg.test(telephone)){
            // 提出提示
            $("#phoneErrorMsg").html("用户手机号码格式错误！");
            $(".phonenum").css("display","block");
            // 返回，阻止继续向下执行
            return false;
        }else {
            $(".phonenum").css("display","none");

            return true;
        }
    }

    // 发送验证码
    function sendSmsCode() {
        let telephone=$("#telephone").val();
        $.get("${pageContext.request.contextPath}/user/sendSMSCode?telephone="+telephone);
    }

    // 验证码校验
    function checkSmsCode() {
        let smsCode = $("#smsCode").val();
        // 发送请求校验
        let flag=true;
        $.get({
            url:"${pageContext.request.contextPath}/user/checkCode?smsCode="+smsCode,
            success:function(resp){
                // alert(resp)
                if(resp !="ok"){
                    // 错误
                    $("#codeErrorMsg").html("验证码过期或错误，请重新输入！");
                    $(".validcode").css("display","block");
                    flag= false;
                }else {
                    flag= true;
                    // 先清空验证码错误提示
                    $(".validcode").css("display","none");
                }
            },
        });
        return flag;
    }

    // 表单点击提交进行数据校验
    function checkForm() {
        // if(checkUsername()&&checkPassword()&&checkPhone()&&checkSmsCode()){
        //     $("#registerForm").submit();
        // }
        if(checkUsername()&&checkPassword()&&checkPhone()){
            $("#registerForm").submit();
        }
    }
</script>
</body>
</html>
