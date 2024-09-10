<%--
  Created by IntelliJ IDEA.
  User: Alfu
  Date: 21.06.2019
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="StyleLogin.css"%>
    </style>
</head>
<body>
<div class="form-wrap">
    <form action="j_security_check" method="POST">
        <h1>Zaloguj się</h1>

        <input type="text" name="j_username" placeholder="Login"/>
        <input type="password" name="j_password" placeholder="Hasło" />
        <input type="submit" value="login" />
    </form>

<script>
    // This is called with the results from from FB.getLoginStatus().
    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            testAPI();
        } else if (response.status === 'not_authorized') {
            // The person is logged into Facebook, but not your app.
        } else {
            // The person is not logged into Facebook, so we're not sure if
            // they are logged into this app or not.
        }
    }
    // This function is called when someone finishes with the Login
    // Button. See the onlogin handler attached to it in the sample
    // code below.
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    }
    window.fbAsyncInit = function() {
        FB.init({
            appId : '2106519296320040',
            cookie : true, // enable cookies to allow the server to access
            // the session
            xfbml : true, // parse social plugins on this page
            version : 'v2.2' // use version 2.2
        });
        // Now that we've initialized the JavaScript SDK, we call
        // FB.getLoginStatus(). This function gets the state of the
        // person visiting this page and can return one of three states to
        // the callback you provide. They can be:
        //
        // 1. Logged into your app ('connected')
        // 2. Logged into Facebook, but not your app ('not_authorized')
        // 3. Not logged into Facebook and can't tell if they are logged into
        // your app or not.
        //
        // These three cases are handled in the callback function.

        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    };
    // Load the SDK asynchronously
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    // Here we run a very simple test of the Graph API after login is
    // successful. See statusChangeCallback() for when this call is made.
    function testAPI() {
        console.log('Welcome! Fetching your information.... ');
        FB.api('/me?fields=name', function(response) {
            console.log('Successful login for: ' + response.name);
            document.getElementById("status").innerHTML = '<h2>Weryfikacja powiodła się ✔️</h2><a id=kontynuacja href=faces/login_register/fblogincontroller.jsp?user_name='+ response.name.replace(" ", "_") +'> <h3>Przejdź do swojego konta ➔👤 </h3></a>'
        });
    }
</script>

<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/pl_PL/sdk.js#xfbml=1&version=v3.3&appId=2106519296320040&autoLogAppEvents=1"></script>
<!--
 Below we include the Login Button social plugin. This button uses
 the JavaScript SDK to present a graphical Login button that triggers
 the FB.login() function when clicked.
-->
<div class="fb-login-button">
<fb:login-button size="large" autologoutlink="true"
                 onlogin="checkLoginState();" styleClass="fbbutton">
    Zaloguj się przez Facebooka
</fb:login-button>
</div>
<br><br>
<div id="status">
</div>
<script type="text/javascript">
</script>
</div>
</body>
</html>
