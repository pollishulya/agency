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
<jsp:include page="../blocks/navbar1.jsp"/>
<jsp:include page="../blocks/navbar.jsp"/>
<body>
<div id="backimage" style="position:relative;left:240px;">
    <table id="leading" data-tooltip='Нажмите, чтобы узнать подробнее'>
        <script type="text/javascript">

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
            $(document).ready(function () {

                $.get("/program?type=LEADING", function (data) {

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
                        rowsHtml = "<tr class='row' >";
                        rowsHtml += "<td>";
                        rowsHtml += "<a href='/program/" + data[j].id + "'><div class='image'  style='position:relative;left:60px'><img class='grow' src='" + data[j].image + "/1.jpg' alt='' width='308' height='210'>" +
                            "</div></a>";
                        rowsHtml += "<p><h3 class='nameTable' style='font-size:26px;position:relative;left:80px''>" + data[j].name + "</h3><p>";
                        rowsHtml += "</td>";
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

                            rowsHtml += "<td>";
                            rowsHtml += "<a href='/program/" + data[j + 1].id + "'><div style='position:relative;left:55px' class='image'><img class='grow' src='" + data[j + 1].image + "/1.jpg' alt='' width='308' height='210'>" +
                                "</div></a>";
                            rowsHtml += "<p><h3 class='nameTable' style='font-size:26px; position: relative;left:75px'>" + data[j + 1].name + "</h3><p>";
                            rowsHtml += "</td>";
                        }


                        if (j + 2 < data.length) {
                            var date = new Date(data[j + 2].exitDate);
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

                            rowsHtml += "<td>";
                            rowsHtml += "<a href='/program/" + data[j+2].id + "'><div style='position:relative;left:55px' class='image'><img class='grow' src='" + data[j+2].image + "/1.jpg' alt='' width='308' height='210'>" +
                                "</div></a>";
                            rowsHtml += "<p><h3 class='nameTable' style='font-size:26px; position:relative;left:75px'>" + data[j+2].name + "</h3><p>";
                            rowsHtml += "</td>";
                        }

                        $('#leading').append(rowsHtml);
                    }

                });
            });

        </script>
    </table>
    <div id="tooltip"></div>
</div>

<jsp:include page="../blocks/footer.jsp"/>

<script>window.jQuery || document.write('<script src="/webjars/jquery/3.1.1/jquery.min.js"><\/script>')</script>

</body>
</html>
