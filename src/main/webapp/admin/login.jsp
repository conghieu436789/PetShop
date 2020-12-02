<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 01/12/2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Login"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Pet Shop</title>
    <!-- Custom fonts for this template-->
    <link href="../assets/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/custom-login.css">
</head>
<body>
<div class="login-container text-white col-md-8 col-lg-5">
    <img class="my-4 rounded mx-auto d-block" src="../assets/images/logo.png" alt="Logo CodeGym">
    <form action="${pageContext.request.contextPath}/administrator/login" method="post" class="p-5">
        <h1>Sign in</h1>
        <% if (session.getAttribute("msg") != null) { %>
        <%=session.getAttribute("msg") %>
        <% session.removeAttribute("msg"); %>
        <% } %>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="Username" autofocus>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="remember">
            <label class="form-check-label" for="remember">Remember me</label>
        </div>
        <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
        <div class="text-right my-2">or <a href="${pageContext.request.contextPath}/admin/sign-up" class="text-white">create an account</a></div>
    </form>
</div>
<!-- Bootstrap core JavaScript-->
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/bootstrap.bundle.min.js"></script>
<script>
    // Enable tooltips
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
</script>
</body>
</html>
