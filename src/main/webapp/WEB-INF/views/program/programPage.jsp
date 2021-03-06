<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Chilling.by</title>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/forPositionPage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/reserveModal.css" type="text/css">
    <script src="/resources/jquery/starRating.js"></script>
    <script src="/resources/jquery/loadComments.js"></script>

    <link rel="stylesheet" href="/resources/css/forPositionTablePage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/formPositionAdd-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/enter.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<jsp:include page="../blocks/navbar1.jsp"/>
<jsp:include page="../blocks/navbar.jsp"/>
<body>

<div class="modal fade" id="ReserveModal" style="position: absolute; left: 325px;top:0px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h5 class="title" id="bookingTitle" style="position: relative; left: 110px"><spring:message code="positionReservation"/></h5>
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
                    <label for="date"><spring:message code="exitDate.label"/></label>
                    <div class="form-label-group">
                        <input type="date" id="date" name="date" class="form-control" required autofocus>
                    </div>
                    <div id="mistake"></div>
                    <div id="uniquePositionFieldMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="uniquePositionNameField.error"/></p></div>
                    <div id="emptyFieldMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyField.error"/></p></div>
                    <div id="incorrectDataMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="bookingDate.error"/></p></div>
                    <div id="bookingDataMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="incorrectDate.error"/></p></div>
                    <button class="btn btn-lg btn-primary btn-block" id="reserveBtn"
                            onclick="reserveProgram(${program.id}); return false;"
                            type="submit"><spring:message code="reserve"/>
                    </button>
                </form>

            </div>
        </div>

    </div>
</div>

<div id="programs" class="werty">

    <div class="blog-post">
        <section class="container" >
            <div id="backimageLook" style="position:relative;left:-70px">
                <h1 class="blog-post-title"><p id="programName">${program.name}</p></h1>
                <c:if test="${program.rating != -1}">
                    <h2>
                        <div class="starrr disabled stars-existing" data-rating=${program.rating}></div>
                    </h2>
                </c:if>
                <c:if test="${program.rating == -1}">
                    <h3>
                        <div><spring:message code="rating.error"/></div>
                    </h3>
                </c:if>
                <div><spring:message code="duration"/><b> ${program.duration}</b></div>
<%--                <div><spring:message code="dateBegin"/><b> ${food.exitDate}</b></div>--%>
<%--                <div><spring:message code="duration"/><b>${food.numberDays}</b></div>--%>
                <div><spring:message code="cost"/><b>${program.price}$</b></div>

                <div id="bookingBtn" class="mar-top clearfix" style="position:relative; bottom:145px" ></div>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <script>
                        $('#bookingBtn').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                            '                                <button style="display:none" id="reserve" class="btn btn-sm btn-primary pull-right"  onclick="reserveForm(0)" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="reserve"/> </button>\n' +
                            '                                    </span>')
                    </script>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_COMPANY')">
                    <script>
                        $('#bookingBtn').append('<span  class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                            '                                <button style="display:none" id="reserve" class="btn btn-sm btn-primary pull-right"  onclick="reserveForm(0)" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="reserve"/> </button>\n' +
                            '                                    </span>')
                    </script>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <script>
                        $('#bookingBtn').append('   <button id="reserve"  class=\'btn btn-primary pull-right\' onclick="reserveForm(0)"><spring:message code="reserve"/></button>')
                    </script>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <script>
                        $('#bookingBtn').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                            '                                <button style="display:none" id="reserve" class="btn btn-sm btn-primary pull-right"  onclick="reserveForm(0)" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="reserve"/> </button>\n' +
                            '                                    </span>')
                    </script>
                </sec:authorize>

                <div style="font-size: 20px">${program.description}</div>
                <br>
                        <img style="position:relative; left:225px;" width="300px" height="200px" src='${program.image}' alt=''>
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
                                        '<button id="send" class="btn btn-sm btn-primary pull-right" onclick="saveCommentProgram(${program.id})" type="submit"> <i class="fa fa-pencil fa-fw"></i> <spring:message code="send"/> </button>')
                                </script>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <script>
                                    $('#sendButton').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                                        '                                <button  style="display:none" id="send" class="btn btn-sm btn-primary pull-right" onclick="saveComment(${program.id})" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="send"/> </button>\n' +
                                        '                                    </span>')
                                </script>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <script>
                                    $('#sendButton').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                                        '                                <button style="display:none" id="send" class="btn btn-sm btn-primary pull-right" onclick="saveComment(${program.id})" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="send"/> </button>\n' +
                                        '                                    </span>')
                                </script>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_COMPANY')">
                                <script>
                                    $('#sendButton').append('<span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="<spring:message code="sendCommentError"/>">\n' +
                                        '                                <button style="display:none" id="send" class="btn btn-sm btn-primary pull-right" onclick="saveComment(${program.id})" type="submit" disabled> <i class="fa fa-pencil fa-fw"></i> <spring:message code="send"/> </button>\n' +
                                        '                                    </span>')
                                </script>
                            </sec:authorize>
<%--                        </div>--%>

    <label ><spring:message code="comments.label"/></label>
                    <div id="comments" >

                                <div style="position:relative;left:300px">
                                    <p><spring:message code="noComments.error"/></p>
                                </div>
                        </div>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                            <li class="page-item">
                                <button type="submit" class="btn btn-sm btn-primary" id="previousBtn" style="position:relative;left:360px"
                                        onclick="loadPage=loadPage-1;loadCommentsProgram(${program.id})"><spring:message
                                        code="previous"/></button>
                            </li>
                            <li class="page-item">
                                <button type="submit" class="btn btn-sm btn-primary " id="nextBtn" style="position:relative;left:360px"
                                        onclick="loadPage=loadPage+1;loadCommentsProgram(${program.id});"><spring:message
                                        code="next"/></button>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </section>
    </div>

</div>
<script>
    loadCommentsProgram(${program.id});
    stars();
</script>
</body>
<jsp:include page="../blocks/footer.jsp"/>
</html>