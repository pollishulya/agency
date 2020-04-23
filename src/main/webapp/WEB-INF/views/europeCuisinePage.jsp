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

    <base href="/">
    <link rel="stylesheet" href="/resources/css/forWelcomePage.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<jsp:include page="navbar.jsp"/>
<body>

<div class="container panel-body">
    <div id="foods">
        <script type="text/javascript">
            $(document).ready(function () {

                $.get("/food?cuisine=EUROPE", function (data) {

                    var rowsHtml;
                    for (var j = 0; j < data.length; j = j + 3) {
                        var date = new Date(data[j].exitDate);
                        var dd = date.getDate();
                        var mm = date.getMonth() + 1;
                        var yyyy = date.getFullYear();
                        if (dd < 10) {
                            dd = '0' + dd;
                        }
                        if (mm < 10) {
                            mm = '0' + mm;
                        }
                        var exitDate = dd + '/' + mm + '/' + yyyy;

                        rowsHtml = "<div class='row'>";
                        rowsHtml += "<div class='col-md-3 tour position-tour'>";
                        rowsHtml += "<div class='image'><img class='tour-image' src='" + data[j].image + "/1.jpg' alt='' width='258' height='160'>" +
                            "<h2><span>" + data[j].cost + " $</span></h2></div>";
                        rowsHtml += "<p><h3>" + data[j].name + "</h3></a><p>";
                        rowsHtml += "<p><small>" + data[j].country + "</small></p>";
                        rowsHtml += "<p> Дата выезда:<b>" + exitDate + "</b><p>";
                        rowsHtml += "<p><span class='glyphicon glyphicon-time' ></span><b> " + data[j].numberDays + " <spring:message code='day.label'/></b></p>";
                        rowsHtml += " <a href='/food/" + data[j].id + "'class='btn btn-primary text-uppercase position-view'>" + "<spring:message code='view.label'/>" + "</a><br/><br/>";
                        rowsHtml += "</div>";
                        if (j + 1 < data.length) {

                            var date = new Date(data[j + 1].exitDate);
                            var dd = date.getDate();
                            var mm = date.getMonth() + 1;
                            var yyyy = date.getFullYear();
                            if (dd < 10) {
                                dd = '0' + dd;
                            }
                            if (mm < 10) {
                                mm = '0' + mm;
                            }
                            var exitDate = dd + '/' + mm + '/' + yyyy;

                            rowsHtml += "<div class='col-md-3 tour position-tour'>";
                            rowsHtml += "<div class='image'><img class='tour-image' src='" + data[j + 1].image + "/1.jpg' alt='' width='258' height='160'>" +
                                "<h2><span>" + data[j + 1].cost + " $</span></h2></div>";
                            rowsHtml += "<p><h3>" + data[j + 1].name + "</h3></a><p>";
                            rowsHtml += "<p><small>" + data[j + 1].country + "</small></p>";
                            rowsHtml += "<p> Дата выезда:<b>" + exitDate + "</b><p>";
                            rowsHtml += "<p><span class='glyphicon glyphicon-time' ></span><b> " + data[j + 1].numberDays + " <spring:message code='day.label'/></b></p>";
                            rowsHtml += " <a href='/food/" + data[j + 1].id + "'class='btn btn-primary text-uppercase position-view'>" + "<spring:message code='view.label'/>" + "</a><br/><br/>";
                            rowsHtml += "</div>";
                        }


                        if (j + 2 < data.length) {
                            //     var date = new Date(data[j + 2].exitDate);
                            //    var dd = date.getDate();
                            //    var mm = date.getMonth() + 1;
                            //    var yyyy = date.getFullYear();
                            //    if (dd < 10) {
                            //        dd = '0' + dd;
                            //    }
                            //    if (mm < 10) {
                            //        mm = '0' + mm;
                            //    }
                            //    var exitDate = dd + '/' + mm + '/' + yyyy;

                            rowsHtml += "<div class='col-md-3 tour position-tour'>";
                            rowsHtml += "<div class='image'><img class='tour-image' src='" + data[j + 2].image + "/1.jpg' alt='' width='258' height='160'>" +
                                "<h2><span>" + data[j + 2].cost + " $</span></h2></div>";
                            rowsHtml += "<p><h3>" + data[j + 2].name + "</h3></a><p>";
                            rowsHtml += "<p><small>" + data[j + 2].view + "</small></p>";
                            rowsHtml += "<p> Кухня:<b>" + data[j+2].type + "</b><p>";
                            <%--rowsHtml += "<p><span class='glyphicon glyphicon-time' ></span><b> " + data[j + 2].numberDays + " <spring:message code='day.label'/></b></p>";--%>
                            rowsHtml += " <a href='/food/" + data[j + 2].id + "'class='btn btn-primary text-uppercase position-view'>" + "<spring:message code='view.label'/>" + "</a><br/><br/>";
                            rowsHtml += "</div>";
                        }

                        $('#foods').append(rowsHtml);
                    }

                });
            });

        </script>
    </div>
</div>

<jsp:include page="footer.jsp"/>

<script>window.jQuery || document.write('<script src="/webjars/jquery/3.1.1/jquery.min.js"><\/script>')</script>

</body>
</html>