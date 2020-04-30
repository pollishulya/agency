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


    <link rel="stylesheet" href="/resources/css/forTourPage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/reserveModal.css" type="text/css">
    <script src="/resources/jquery/starRating.js"></script>
    <script src="/resources/jquery/loadComments.js"></script>

    <link rel="stylesheet" href="/resources/css/forTourTablePage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/formTourAdd-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/enter.css" type="text/css">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<jsp:include page="blocks/navbar1.jsp"/>
<jsp:include page="blocks/navbar.jsp"/>
<body>
<br><br>
<div class='slideshow-container textWrapLeft'>
<div id="backimage" style="position:relative;left:240px; top:75px">
    <div class='mySlides' >
        <div class='numbertext'>1 / 3</div>
        <table id="foods">


            <script type="text/javascript">

                $(document).ready(function () {

                    $.get("/locations", function (data) {

                        var rowsHtml;
                        for (var j = 0; j<2; j = j + 3) {
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
                            rowsHtml += "<a href='/location/" + data[j].id + "'><div class='image'  style='position:relative;left:60px'><img class='grow' src='" + data[j].image + "/1.jpg' alt='' width='308' height='210'>" +
                                "</div></a>";
                            rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position:relative;left:140px'>" + data[j].name + "</h3><p>";
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
                                rowsHtml += "<a href='/location/" + data[j+1].id + "'><div style='position:relative;left:55px' class='image'><img class='grow' src='" + data[j+1].image + "/1.jpg' alt='' width='308' height='210'>" +
                                    "</div></a>";
                                rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position: relative;left:140px'>" + data[j+1].name + "</h3><p>";
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
                                rowsHtml += "<a href='/location/" + data[j+2].id + "'><div style='position:relative;left:55px' class='image'><img class='grow' src='" + data[j+2].image + "/1.jpg' alt='' width='308' height='210'>" +
                                    "</div></a>";
                                rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position:relative;left:140px'>" + data[j+2].name + "</h3><p>";
                                rowsHtml += "</td>";
                            }

                            $('#foods').append(rowsHtml);
                        }

                    });
                });
            </script>
        </table>
    </div>

    <div class='mySlides' >
        <div class='numbertext'>2 / 3</div>
        <table id="foods1">
            <script type="text/javascript">

                $(document).ready(function () {

                    $.get("/locations", function (data) {

                        var rowsHtml;
                        for (var j = 3; j<5; j = j + 3) {
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
                            rowsHtml += "<a href='/location/" + data[j].id + "'><div class='image'  style='position:relative;left:60px'><img class='grow' src='" + data[j].image + "/1.jpg' alt='' width='308' height='210'>" +
                                "</div></a>";
                            rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position:relative;left:140px'>" + data[j].name + "</h3><p>";
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
                                rowsHtml += "<a href='/location/" + data[j+1].id + "'><div style='position:relative;left:55px' class='image'><img class='grow' src='" + data[j+1].image + "/1.jpg' alt='' width='308' height='210'>" +
                                    "</div></a>";
                                rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position: relative;left:140px'>" + data[j+1].name + "</h3><p>";
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
                                rowsHtml += "<a href='/location/" + data[j+2].id + "'><div style='position:relative;left:55px' class='image'><img class='grow' src='" + data[j+2].image + "/1.jpg' alt='' width='308' height='210'>" +
                                    "</div></a>";
                                rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position:relative;left:140px'>" + data[j+2].name + "</h3><p>";
                                rowsHtml += "</td>";

                            }

                            $('#foods1').append(rowsHtml);
                        }

                    });
                });
            </script>
        </table>
    </div>

    <div class='mySlides'>
        <div class='numbertext'>3 / 3</div>
        <table id="foods2">
            <script type="text/javascript">

                $(document).ready(function () {

                    $.get("/locations", function (data) {

                        var rowsHtml;
                        for (var j = 6; j<8; j = j + 3) {
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
                            rowsHtml += "<a href='/location/" + data[j].id + "'><div class='image'  style='position:relative;left:60px'><img class='grow' src='" + data[j].image + "/1.jpg' alt='' width='308' height='210'>" +
                                "</div></a>";
                            rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position:relative;left:140px'>" + data[j].name + "</h3><p>";
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
                                rowsHtml += "<a href='/location/" + data[j+1].id + "'><div style='position:relative;left:55px' class='image'><img class='grow' src='" + data[j+1].image + "/1.jpg' alt='' width='308' height='210'>" +
                                    "</div></a>";
                                rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position:relative;left:140px'>" + data[j+1].name + "</h3><p>";
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
                                rowsHtml += "<a href='/location/" + data[j+2].id + "'><div style='position:relative;left:55px' class='image'><img class='grow' src='" + data[j+2].image + "/1.jpg' alt='' width='308' height='210'>" +
                                    "</div></a>";
                                rowsHtml += "<p><h3 class='nameTable' style='font-size:30px; position:relative;left:140px'>" + data[j+2].name + "</h3><p>";
                                rowsHtml += "</td>";
                            }

                            $('#foods2').append(rowsHtml);
                        }

                    });
                });
            </script>
        </table>
    </div>

    <a class='prev' style="position:absolute; left:0px;" onclick='plusSlides(-1)'>&#10094;</a>
    <a class='next' style="position:absolute; right:0px;" onclick='plusSlides(1)'>&#10095;</a>
</div>
<script>
    var slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("dot");
        if (n > slides.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = slides.length
        }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " active";
    }
</script>
<br>

</div>


<script>window.jQuery || document.write('<script src="/webjars/jquery/3.1.1/jquery.min.js"><\/script>')</script>

</body>

</html>
