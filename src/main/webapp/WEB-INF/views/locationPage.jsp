<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Only Travel</title>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

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

<div class="modal fade" id="ReserveModal" style="position: absolute; left: 325px;top:0px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h5 class="title" id="bookingTitle" style="position: relative; left: 110px;"><spring:message code="tourReservation"/></h5>
            </div>
            <div class="modal-body">
                <form id="ReserveForm">
                    <div class="form-label-group">
                        <input type="text" id="name" name="name" class="form-control" placeholder="Name" required
                               autofocus>
                        <label for="name"><spring:message code="name.label"/></label>
                    </div>
                    <div class="form-label-group">
                        <input type="text" id="phone" name="phone" class="form-control" placeholder="Phone" required
                               autofocus>
                        <label for="phone"><spring:message code="phone.label"/></label>
                    </div>
                    <label><spring:message code="numberPerson.label"/></label>
                    <div class="form-label-group">
                        <input type="number" id="numberPersons" class="form-control" placeholder="Number persons" min=1 max=6 required autofocus>
                    </div>
                    <div id="mistake"></div>
                 <script>alert(location.id)</script>
                    <button class="btn btn-lg btn-primary btn-block" id="reserveBtn"
                            onclick="reserveLocation(${location.id}); return false;"
                            type="submit"><spring:message code="reserve"/>
                    </button>
                </form>

            </div>
        </div>

    </div>
</div>

<div id="locations" class="werty">

    <div class="blog-post">
        <section class="container">
          <div id="backimageLook" style="position:relative;left:-70px">
                <h1 class="blog-post-title"><p id="locationName">${location.name}</p></h1>
                <c:if test="${location.rating != -1}">
                    <h2>
                        <div class="starrr disabled stars-existing" data-rating=${location.rating}></div>
                    </h2>
                </c:if>
                <c:if test="${location.rating == -1}">
                    <h3>
                        <div><spring:message code="rating.error"/></div>
                    </h3>
                </c:if>
                <div><spring:message code="country"/><b> ${location.address}</b></div>
<%--                <div><spring:message code="dateBegin"/><b> ${food.exitDate}</b></div>--%>
<%--                <div><spring:message code="duration"/><b>${food.numberDays}</b></div>--%>
                <div><spring:message code="cost"/><b>${location.price}$</b></div>
                <br>
                <div class='slideshow-container textWrapLeft' style="position:relative; left:225px">

                    <div class='mySlides'>
                        <div class='numbertext'>1 / 3</div>
                        <img src='${location.image}/1.jpg' alt='' style='width:100% ;position:relative; left:0px;'>
                    </div>

                    <div class='mySlides'>
                        <div class='numbertext'>2 / 3</div>
                        <img src='${location.image}/2.jpg' style='width:100% ;position:relative; left:0px;'>
                    </div>

                    <div class='mySlides'>
                        <div class='numbertext'>3 / 3</div>
                        <img src='${location.image}/3.jpg' style='width:100% ;position:relative; left:0px;'>
                    </div>

                    <a class='prev' onclick='plusSlides(-1)'>&#10094;</a>
                    <a class='next' onclick='plusSlides(1)'>&#10095;</a>

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
                <div style='text-align:center; display:none'>
                    <span class='dot' onclick='currentSlide(1)'></span>
                    <span class='dot' onclick='currentSlide(2)'></span>
                    <span class='dot' onclick='currentSlide(3)'></span>
                </div>

            <br>


                <div id="description"></div>
                <script>
                    loadDescription(${location.id});
                </script>
                <div id="bookingBtn" class="mar-top clearfix" style="position:relative; bottom:205px"></div>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <script>
                        $('#bookingBtn').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                            '                                <button style="display:none" id="reserve" class="btn btn-sm btn-primary pull-right"  onclick="reserveForm(0)" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="reserve"/> </button>\n' +
                            '                                    </span>')
                    </script>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_COMPANY')">
                    <script>
                        $('#bookingBtn').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                            '                                <button style="display:none" id="reserve" class="btn btn-sm btn-primary pull-right"  onclick="reserveForm(0)" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="reserve"/> </button>\n' +
                            '                                    </span>')
                    </script>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <script>
                        $('#bookingBtn').append('   <button id="reserve" class=\'btn btn-primary pull-right\' onclick="reserveForm(0)"><spring:message code="reserve"/></button>')
                    </script>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <script>
                        $('#bookingBtn').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                            '                                <button style="display:none" id="reserve" class="btn btn-sm btn-primary pull-right"  onclick="reserveForm(0)" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="reserve"/> </button>\n' +
                            '                                    </span>')
                    </script>
                </sec:authorize>



            <br>
            <div class="row">
                <div class="col-md-12">

<%--                        <div class="panel-body">--%>

                            <div id="saveCommentMistake" hidden><p class='alert alert-danger' role='alert'>
                                <spring:message code="saveComment.error"/></p></div>
                            <div id="sendButton" class="mar-top clearfix"></div>
                            <sec:authorize access="hasRole('ROLE_USER')">
                                <script>
                                    $('#sendButton').append(' <div class="form-group shadow-textarea comment">\n' +
                                        '                                <label for="comment"><spring:message code="comment"/></label>\n' +
                                        '                                <div class="container">\n' +
                                        '                                    <div id="rating" class="row lead">\n' +
                                        '                                        <div id="stars" class="starrr"></div>\n' +
                                        '                                    </div>\n' +
                                        '                                    <input type="hidden" id="count">\n' +
                                        '                                </div>\n' +
                                        '                                <textarea class="commentForm" id="comment" rows="4" cols="70" style="width:762px"\n' +
                                        '                                          placeholder=<spring:message code="writeComment"/>></textarea>\n' +
                                        '                            </div>' +
                                        '<button id="send" class="btn btn-sm btn-primary pull-right" onclick="saveComment(${location.id})" type="submit"> <i class="fa fa-pencil fa-fw"></i> <spring:message code="send"/> </button>')
                                </script>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <script>
                                    $('#sendButton').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                                        '                                <button style="display:none" id="send"  class="btn btn-sm btn-primary pull-right" onclick="saveComment(${location.id})" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="send"/> </button>\n' +
                                        '                                    </span>')
                                </script>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <script>
                                    $('#sendButton').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                                        '                                <button style="display:none" id="send"  class="btn btn-sm btn-primary pull-right" onclick="saveComment(${location.id})" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="send"/> </button>\n' +
                                        '                                    </span>')
                                </script>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_COMPANY')">
                                <script>
                                    $('#sendButton').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                                        '                                <button style="display:none" id="send"  class="btn btn-sm btn-primary pull-right" onclick="saveComment(${location.id})" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="send"/> </button>\n' +
                                        '                                    </span>')
                                </script>
                            </sec:authorize>
<%--                        </div>--%>

                    <div id="comments">

                                <div class='media-block' style="position:relative;left:300px">
                                    <p><spring:message code="noComments.error"/></p>
                                </div>

                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                            <li class="page-item">
                                <button type="submit" class="btn btn-sm btn-primary" id="previousBtn" style="position:relative;left:360px"
                                        onclick="loadPage=loadPage-1;loadComments(${location.id})"><spring:message
                                        code="previous"/></button>
                            </li>
                            <li class="page-item">
                                <button type="submit" class="btn btn-sm btn-primary " id="nextBtn" style="position:relative;left:360px"
                                        onclick="loadPage=loadPage+1;loadComments(${location.id});"><spring:message
                                        code="next"/></button>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
          </div>
        </section>
    </div>

</div>
<script>
    loadComments(${location.id});
    stars();
</script>
</body>
<jsp:include page="blocks/footer.jsp"/>
</html>