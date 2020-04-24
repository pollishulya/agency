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
    <link rel="stylesheet" href="/resources/css/table-reservation-style.css" type="text/css">
    <script src="/resources/jquery/forOrdersPage.js"></script>

    <title>My orders</title>
</head>
<jsp:include page="blocks/navbar.jsp"/>
<body>
<div class="position-table">
    <div id="table">
        <table class='table table-hover table-bordered table-sm' cellspacing='0' width='100%'>
            <thead class="black white-text">
            <tr>
                <th scope="col"><spring:message code="username.label"/></th>
                <th scope="col"><spring:message code="phone.label"/></th>
                <th scope="col"><spring:message code="numberPerson.label"/></th>
                <th scope="col"><spring:message code="tour.label"/></th>
            </tr>
            </thead>
            <tbody id='tableBody'>
            </tbody>
        </table>
    </div>

    <div id="noReservationMessage">
        <div class='panel panel-warning'>
            <div class='panel-heading'></div>
            <div class='panel-body'><spring:message code='noReservation.error'/></div>
        </div>
    </div>
</div>

<script type="text/javascript">
    loadOrders();
</script>
</body>
<jsp:include page="blocks/footer.jsp"/>
</html>

