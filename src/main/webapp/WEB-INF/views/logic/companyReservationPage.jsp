<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>

    <meta charset="utf-8">
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="#"/>
    <base href="/">
    <link rel="stylesheet" href="/resources/css/forOrdersPage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/forPositionTablePage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/table-reservation-style.css" type="text/css">
    <script src="/resources/jquery/forOrdersPage.js"></script>

    <title>My orders</title>
</head>
<br>
<jsp:include page="../blocks/navbar1.jsp"/>
<jsp:include page="../blocks/navbar.jsp"/>
<body>
<div class="modal fade" id="deleteModal" style="position: absolute; left: 400px;top:-25px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="deleteForm">
                    <input type="hidden" id="idDeletePosition">
                    <label class="questionForm" ><spring:message code="confirmDelete.label"/></label>
                    <div id="deletePositionMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="deletePosition.error"/></p></div>
                    <hr>
                    <button class="btn  btn-primary" id="deleteBtn"
                            onclick="deleteRecord($('#idDeletePosition').val());return false;"
                            type="submit"><spring:message code="delete.button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteModalLocation" style="position: absolute; left: 400px;top:-25px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="deleteFormlLocation">
                    <input type="hidden" id="idDeletelLocation">
                    <label class="questionForm" ><spring:message code="confirmDelete.label"/></label>
                    <div id="deletePositionMistakelLocation" hidden><p class='alert alert-danger' role='alert'><spring:message code="deletePosition.error"/></p></div>
                    <hr>
                    <button class="btn  btn-primary" id="deleteBtnlLocation"
                            onclick="deleteRecord($('#idDeletelLocation').val());return false;"
                            type="submit"><spring:message code="delete.button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteModalProgram" style="position: absolute; left: 400px;top:-25px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="deleteFormProgram">
                    <input type="hidden" id="idDeleteProgram">
                    <label class="questionForm" ><spring:message code="confirmDelete.label"/></label>
                    <div id="deletePositionMistakeProgram" hidden><p class='alert alert-danger' role='alert'><spring:message code="deletePosition.error"/></p></div>
                    <hr>
                    <button class="btn  btn-primary" id="deleteBtnProgram"
                            onclick="deleteProgram($('#idDeleteProgram').val());return false;"
                            type="submit"><spring:message code="delete.button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="backimage" class="position-table">
    <div id="table">
        <table class='table'>
            <thead class="thead-light">
            <tr>
                <th scope="col"><spring:message code="username.label"/></th>
                <th scope="col"><spring:message code="phone.label"/></th>
                <th scope="col"><spring:message code="numberPerson.label"/></th>
                <th scope="col"><spring:message code="status.label"/></th>
                <th scope="col"><spring:message code="date.label"/></th>
                <th scope="col"><spring:message code="position.label"/></th>
                <th scope="col"><spring:message code="delete.label"/></th>
            </tr>
            </thead>
            <tbody id='tableBody'>
            </tbody>
        </table>


    <div id="noReservationMessage">
        <div class='panel panel-warning'>
            <div class='panel-heading'></div>
            <div class='panel-body'><spring:message code='noReservation.error'/></div>
        </div>
    </div>
    </div>
</div>

<script type="text/javascript">
    loadCompanyOrdersFood();
    loadCompanyOrdersLocation();
    loadCompanyOrdersProgram();
</script>
</body>
<jsp:include page="../blocks/footer.jsp"/>
</html>

