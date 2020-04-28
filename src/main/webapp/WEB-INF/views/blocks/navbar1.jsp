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

    <base href="/">

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" type="text/css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/resources/jquery/forLogin.js"></script>
    <link rel="stylesheet" href="/resources/css/navbar-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/modal.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/deleteForm.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body onload="timer()">
                <div id="navbar1" class="collapse navbar-collapse">
                    <a href="/" id="home">
                        <img src='/resources/images/eda1.png' width="50" height="50" class="d-inline-block align-top" alt=""/>Chilling.by

                    </a>
            <img src='/resources/images/phone.png' width="35" height="35" class="d-inline-block align-top" alt=""/>+375 44 7654901
            <img src='/resources/images/timer.png' width="35" height="35" class="d-inline-block align-top" alt=""/>Понедельник-Пятница: 10-20
<span id="timer"> </span>
                </div>
</body>
</html>
