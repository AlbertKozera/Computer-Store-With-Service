<%--
  Created by IntelliJ IDEA.
  User: Alfu
  Date: 21.06.2019
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String login = (String) request.getParameter("user_name").replace("_", " ");
    %>
    <br/>
    <%=login%>
    <br/>
    <script type="text/javascript">
        function func() {
            var login = document.getElementById("login").value='<%=login%>';

            var pass = document.getElementById("pass").value='<%=login%>';

            document.getElementById("sub").click();
        }

    </script>

</head>
<body onload="func();">
<div class="form-wrap">
    <form action="j_security_check" method="post">
        <h1>Zaloguj się</h1>
        <input type="text" name="j_username" id="login"/>
        <input type="password" name="j_password" id="pass"/>
        <input type="submit" value="login" id="sub"/>
    </form>
</div>
</body>
</html>
