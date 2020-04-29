<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Chilling.by</title>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" type="text/css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/resources/jquery/forLogin.js"></script>
    <link rel="stylesheet" href="/resources/css/navbar-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/modal.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/deleteForm.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/forWelcomePage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/enter.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/enterforbutton.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<jsp:include page="blocks/navbar1.jsp"/>
<jsp:include page="blocks/navbar.jsp"/>
<body>

<div class="row">
    <div class="col">
            <h5 class="title"><spring:message code="signin"/></h5>
        <div class="modal-body">
            <form id="loginForm" action="/j_spring_security_check" method="post">
                <div class="form-label-group">
                    <input type="email" id="email" name="email" class="form-control" placeholder="Email" required
                           autofocus onfocus="  $('#mistakeMessage').detach();">
                    <label for="email"><spring:message code="email"/></label>
                </div>
                <div class="form-label-group">
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password"
                           required autofocus onfocus="  $('#mistakeMessage').detach();">
                    <label for="password"><spring:message code="password"/></label>
                </div>
                <c:if test="${not empty error}">
                    <div id="loginMistake"><p class='alert alert-danger' role='alert'><spring:message
                            code="${error}"/></p></div>
                </c:if>
                <button class="btn btn-outline-primary" id="loginBtn"
                        type="submit"><spring:message code="signin.button"/>
                </button>
            </form>
        </div>
        <br><br><br><br><br>
    </div>

<div class="col2">
            <h5 class="title" style="position:relative; left:140px;"><spring:message code="signup.label"/></h5>
        <div class="modal-body">
            <form id="signUpForm">
                <div class="form-label-group">
                    <input type="text" id="surnameSignUp" name="email" class="form-control" placeholder="Name" required
                           autofocus onfocus="  $('#mistakeMessage').detach();">
                    <label for="surnameSignUp"><spring:message code="name"/></label>
                </div>
                <div class="form-label-group">
                    <input type="email" id="emailSignUp" name="email" class="form-control" placeholder="Email"
                           required
                           autofocus onfocus="  $('#mistakeMessage').detach();">
                    <label for="emailSignUp"><spring:message code="email"/></label>
                </div>
                <div class="form-label-group">
                    <input type="text" id="phoneSignUp" name="phone" class="form-control" placeholder="Phone"
                           required
                           autofocus onfocus="  $('#mistakeMessage').detach();">
                    <label for="phoneSignUp"><spring:message code="phone"/></label>
                </div>
                <div class="form-label-group">
                    <input type="password" id="passwordSignUp" name="password" class="form-control"
                           placeholder="Password" required
                           onfocus="  $('#mistakeMessage').detach();">
                    <label for="passwordSignUp"><spring:message code="password"/></label>
                </div>
                <a class="btn btn-outline-primary" id="signUpBtn"
                   onclick="signUp()"><spring:message code="signup.button"/>
                </a>
            </form>
        </div>
</div>
</div>
</body>
</html>
