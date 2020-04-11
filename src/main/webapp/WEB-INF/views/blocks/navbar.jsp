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


    <title>Only Travel</title>

    <base href="/">

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" type="text/css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/webapp/resources/jquery/forLogin.js"></script>
    <link rel="stylesheet" href="/resources/css/navbar-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/modal.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/deleteForm.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<header>


    <div class="modal fade " id="loginModal">
        <div class="modal-dialog formContent">
            <input type="hidden" id="token" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h5 id="title"><spring:message code="signin"/></h5>
            </div>
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
                    <button class="btn btn-primary btn-block" id="loginBtn"
                            type="submit"><spring:message code="signin.button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="signUpModal">
        <div class="modal-dialog formContent">
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h5><spring:message code="signup.label"/></h5>
            </div>
            <div class="modal-body">
                <form id="signUpForm">
                    <div class="form-label-group">
                        <input type="text" id="nameSignUp" name="email" class="form-control" placeholder="Name" required
                               autofocus onfocus="  $('#mistakeMessage').detach();">
                        <label for="nameSignUp"><spring:message code="name"/></label>
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
                    <a class="btn btn-primary btn-block" id="signUpBtn"
                       onclick="signUp()"><spring:message code="signup.button"/>
                    </a>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="accountSettingsModal">
        <div class="modal-dialog formContent">
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h5><spring:message code="update.label"/></h5>
            </div>
            <div class="modal-body">
                <form id="settingsForm">
                    <input type="hidden" id="idUpdateViaUser" name="id">
                    <input type="hidden" id="roleUpdateViaUser" name="role">
                    <div class="form-label-group">
                        <input type="text" id="nameUpdateViaUser" class="form-control" placeholder="Name" required
                               autofocus>
                        <label for="nameUpdateViaUser"><spring:message code="name.label"/></label>
                    </div>
                    <div class="form-label-group">
                        <input type="text" id="phoneUpdateViaUser" class="form-control" placeholder="Phone" required
                               autofocus>
                        <label for="phoneUpdateViaUser"><spring:message code="phone.label"/></label>
                    </div>
                    <div class="form-label-group">
                        <input type="text" id="emailUpdateViaUser" class="form-control"
                               placeholder="Email" required autofocus>
                        <label for="emailUpdateViaUser"><spring:message code="email.label"/></label>
                    </div>
                    <div id="uniqueFieldInSettingsMistake" hidden><p class='alert alert-danger' role='alert'>
                        <spring:message
                                code="uniqueField.error"/></p></div>
                    <div id="emptyFieldInSettingsMistake" hidden><p class='alert alert-danger' role='alert'>
                        <spring:message
                                code="emptyField.error"/></p></div>
                    <a href="/account/showAccounts" class="btn btn-primary btn-block" id="updateRecord"
                       onclick="updateAccount();return false;"><spring:message code="save.button"/></a>
                </form>
            </div>
        </div>
    </div>


    <nav class="navbar navbar-expand-md  fixed-top">
        <div class="container">
            <div id="navbar" class="collapse navbar-collapse">
                <a href="/" id="home">
                    <img src='/resources/images/home_label.svg' width="50" height="50" class="d-inline-block align-top" alt=""/>OnlyTravel

                </a>
                <div id="buttons" class="position-groupTourBtn"></div>
                <div id="user" class="position-for-logBtn"></div>
                <div id="logoutBtn"></div>
                <sec:authorize access="isAnonymous()">
                    <script>
                        $('#user').append('<button id="locales" class="btn btn-outline-primary dropdown-toggle position-button" type="button"\n' +
                            '                            data-toggle="dropdown"><spring:message code="lang.label"/><span class="caret"></span></button>\n' +
                            '                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">\n' +
                            '                        <li role="presentation"><a role="menuitem" tabindex="-1"\n' +
                            '                                                   onclick="languageSelect(\'en\');"><spring:message code="lang.eng"/></a></li>\n' +
                            '                        <li role="presentation"><a role="menuitem" tabindex="-1"\n' +
                            '                                                   onclick="languageSelect(\'ru\');"><spring:message code="lang.ru"/></a></li>\n' +
                            '                    </ul>\n' +
                            '                </div><button type="button" class="btn btn-info position-button" id="signup" onclick="signUpForm()"><spring:message code="signup.button"/></button><button type="button" class="btn btn-info position-button" id="login" onclick="loginForm(0)"><spring:message code="signin.button"/></button>')
                    </script>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <script>

                        $('#buttons').append('    <a href="/showFoods/cruise"  class="btn btn-outline-primary"><spring:message code="seeFood.label"/></a>\n' +
                            '                <a href="/showFoods/bus" class="btn btn-outline-primary "><spring:message code="busTour.label"/></a>\n' +
                            '                <a href="/showFoods/air" class="btn btn-outline-primary "><spring:message code="airTour.label"/></a>\n'+
                            '                <a href="/" class="btn btn-outline-primary "><spring:message code="allTour.label"/></a>\n');

                        $('#user').append(' <div class="position-for-langBtn"><button id="locales" class="btn btn-outline-primary dropdown-toggle position-lang" type="button"\\n\' +\n' +
                            '       data-toggle="dropdown"><spring:message code="lang.label"/><span class="caret"></span></button>\n' +
                            '       <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">\n' +
                            '                 <li role="presentation"><a role="menuitem" tabindex="-1"\n' +
                            '                                               onclick="languageSelect(\'en\');"><spring:message code="lang.eng"/></a></li>\n' +
                            '              <li role="presentation"><a role="menuitem" tabindex="-1"\n' +
                            '                             onclick="languageSelect(\'ru\');"><spring:message code="lang.ru"/></a></li>\n' +
                            '                   </ul>' +
                            '</div><button class="btn btn-outline-primary dropdown-toggle position-button" type="button" data-toggle="dropdown">${pageContext.request.userPrincipal.name}<span class="glyphicon glyphicon-user"/>\n' +
                            '                        <span class="caret"></span></button>\n' +
                            '                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">\n' +
                            '                        <li role="presentation"><a role="menuitem" tabindex="-1" href="/account/showAccounts"><spring:message code="account"/></a></li>\n' +
                            '                <li role="presentation"><a role="menuitem" tabindex="-1" onclick="updateAccountSettings()"><spring:message code="settings.label"/></a></li>\n' +
                            ' </ul>\n' +
                            '   <a href="<c:url value="/logout" />" class="btn btn-outline-primary position-button"><spring:message code="logout"/></a>');

                    </script>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <script>

                        $('#buttons').append('    <a href="/showTours/cruise"  class="btn btn-outline-primary"><spring:message code="seeFood.label"/></a>\n' +
                            '                <a href="/showTours/bus" class="btn btn-outline-primary"><spring:message code="busTour.label"/></a>\n' +
                            '                <a href="/showTours/air" class="btn btn-outline-primary"><spring:message code="airTour.label"/></a>\n'+
                            '                <a href="/" class="btn btn-outline-primary "><spring:message code="allTour.label"/></a>\n');
                        $('#user').append('  <div class="position-for-langBtn"><button id="locales" class="btn btn-outline-primary dropdown-toggle position-lang" type="button"\\n\' +\n' +
                            '       data-toggle="dropdown"><spring:message code="lang.label"/><span class="caret"></span></button>\n' +
                            '       <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">\n' +
                            '                 <li role="presentation"><a role="menuitem" tabindex="-1"\n' +
                            '                                               onclick="languageSelect(\'en\');"><spring:message code="lang.eng"/></a></li>\n' +
                            '              <li role="presentation"><a role="menuitem" tabindex="-1"\n' +
                            '                             onclick="languageSelect(\'ru\');"><spring:message code="lang.ru"/></a></li>\n' +
                            '                   </ul>' +
                            '</div><button class="btn btn-outline-primary dropdown-toggle position-button" type="button" data-toggle="dropdown">${pageContext.request.userPrincipal.name}<span class="glyphicon glyphicon-user"/>\n' +
                            '                        <span class="caret"></span></button>\n' +
                            '                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">\n' +
                            '                        <li role="presentation"><a role="menuitem" tabindex="-1" href="/orders"><spring:message code="myOrder"/></a></li>\n' +
                            '                        <li role="presentation"><a role="menuitem" tabindex="-1" onclick="updateAccountSettings()"><spring:message code="settings.label"/></a></li>\n' +
                            '                    </ul>\n' +
                            '<a href="<c:url value="/logout" />" class="btn btn-outline-primary position-button"><spring:message code="logout"/></a>');
                    </script>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_COMPANY')">
                    <script>
                        $('#buttons').append('    <a href="/showFoods/cruise"  class="btn btn-outline-primary"><spring:message code="seeFood.label"/></a>\n' +
                            '                <a href="/showFoods/bus" class="btn btn-outline-primary "><spring:message code="busTour.label"/></a>\n' +
                            '                <a href="/showFoods/air" class="btn btn-outline-primary "><spring:message code="airTour.label"/></a>\n'+
                            '                <a href="/" class="btn btn-outline-primary "><spring:message code="allTour.label"/></a>\n');
                        $('#user').append('<div class="position-for-langBtn"><button id="locales" class="btn btn-outline-primary dropdown-toggle position-lang" type="button"\\n\' +\n' +
                            '       data-toggle="dropdown"><spring:message code="lang.label"/><span class="caret"></span></button>\n' +
                            '       <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">\n' +
                            '                 <li role="presentation"><a role="menuitem" tabindex="-1"\n' +
                            '                                               onclick="languageSelect(\'en\');"><spring:message code="lang.eng"/></a></li>\n' +
                            '              <li role="presentation"><a role="menuitem" tabindex="-1"\n' +
                            '                             onclick="languageSelect(\'ru\');"><spring:message code="lang.ru"/></a></li>\n' +
                            '                   </ul>' +
                            '</div><button class="btn btn-outline-primary dropdown-toggle position-button" type="button" data-toggle="dropdown">${pageContext.request.userPrincipal.name}<span class="glyphicon glyphicon-user"/>\n' +
                            '                        <span class="caret"></span></button>\n' +
                            '                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">\n' +
                            '                        <li role="presentation"><a role="menuitem" tabindex="-1" href="/showTours"><spring:message code="tours"/></a></li>\n' +
                            '                <li role="presentation"><a role="menuitem" tabindex="-1" onclick="updateAccountSettings()"><spring:message code="settings.label"/></a></li>\n' +
                            '</ul>\n' +
                            '        <a href="<c:url value="/logout" />" class="btn btn-outline-primary position-button"><spring:message code="logout"/></a>');
                    </script>
                </sec:authorize>

            </div>
        </div>
    </nav>

    <script>
    </script>


</header>
</html>
