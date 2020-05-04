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
    <link rel="stylesheet" href="/resources/css/forPositionTablePage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/forOrdersPage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/table-reservation-style.css" type="text/css">
    <script src="/resources/jquery/forOrdersPage.js"></script>

    <title>My orders</title>
</head>
<br>
<jsp:include page="../blocks/navbar1.jsp"/>
<jsp:include page="../blocks/navbar.jsp"/>
<body>
<div class="modal fade" id="cancelFoodModal" style="position: absolute; left: 400px;top:-25px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="cancelFoodForm">
                    <input type="hidden" id="idCancelFood">
                    <label class="questionForm" style="color:orange;"><spring:message code="confirmCancel.label"/></label>
                    <div id="deleteFoodMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="cancelPosition.error"/></p></div>
                    <hr>
                    <button class="btn  btn-primary" id="cancelFoodBtn" style="position: relative; left:50px"
                            onclick="cancelFood($('#idCancelFood').val(), $('#foodIdCancelPosition').val(),$('#companyIdCancelPosition').val(),$('#accountIdCancelPosition').val());return false;"
                            type="submit"><spring:message code="cancel.button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="cancelProgramModal" style="position: absolute; left: 400px;top:-25px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="deleteProgramForm">
                    <input type="hidden" id="idCancel">
                    <label class="questionForm" style="color:orange;"><spring:message code="confirmCancel.label"/></label>
                    <div id="deleteProgramMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="cancelPosition.error"/></p></div>
                    <hr>
                    <button class="btn  btn-primary" id="cancelProgramBtn" style="position: relative; left:50px"
                            onclick="cancelProgram($('#idCancelProgram').val()/*, $('#locationIdCancelPosition').val(),$('#companyIdCancelPosition').val()*/);return false;"
                            type="submit"><spring:message code="cancel.button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="cancelLocationModal" style="position: absolute; left: 400px;top:-25px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="cancelLocationForm">
                    <input type="hidden" id="idCancelLocation">
                    <label class="questionForm" style="color:orange;"><spring:message code="confirmCancel.label"/></label>
                    <div id="cancelLocationMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="cancelPosition.error"/></p></div>
                    <hr>
                    <button class="btn  btn-primary" id="cancelLocationBtn" style="position: relative; left:50px"
                            onclick="cancelLocation($('#idCancelLocation').val()/*, $('#locationIdCancelPosition').val(),$('#companyIdCancelPosition').val()*/);return false;"
                            type="submit"><spring:message code="cancel.button"/>
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
                <th scope="col"><spring:message code="cancel.label"/></th>

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
    loadOrdersFood();
    loadOrdersLocation();
    loadOrdersProgram();
</script>
</body>
<jsp:include page="../blocks/footer.jsp"/>
</html>

