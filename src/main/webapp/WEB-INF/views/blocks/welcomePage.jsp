

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8" %>
<%--<!doctype html>--%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Chilling.by</title>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" type="text/css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/forWelcomePage.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<jsp:include page="navbar1.jsp"/>
<jsp:include page="navbar.jsp"/>
<body>
<div id="backimage">
    <table>
        <tr>
            <td class="grow" data-tooltip="Нажмите, чтобы просмотреть локации"><a href="/pageLocation"> <img src="/resources/images/location.jpg" width="350" height="550"> </a></td>
            <td class="grow" data-tooltip="Нажмите, чтобы просмотреть меню"><a href="/pageMenu"><img src="/resources/images/menu.jpg" width="350" height="550"></a></td>
            <td class="grow" data-tooltip="Нажмите, чтобы просмотреть развлечения"><a href="/pageTamada"><img src="/resources/images/tamada.jpg" width="350" height="550"></a></td>
        </tr>
    </table>
    <div id="tooltip"></div>
</div>
<script>
    $(function(){
        $("[data-tooltip]").mousemove(function (eventObject) {
            $data_tooltip = $(this).attr("data-tooltip");
            $("#tooltip").html($data_tooltip)
                .css({
                    "top" : eventObject.pageY -200,
                    "left" : eventObject.pageX -250
                })
                .show();
        }).mouseout(function () {
            $("#tooltip").hide()
                .html("")
                .css({
                    "top" : 0,
                    "left" : 0
                });
        });
    });
</script>
<script>window.jQuery || document.write('<script src="/webjars/jquery/3.1.1/jquery.min.js"><\/script>')</script>

</body>
<jsp:include page="footer.jsp"/>
</html>
