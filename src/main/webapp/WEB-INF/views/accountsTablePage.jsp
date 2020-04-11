<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" type="text/css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <script src="/resources/jquery/actionsWithAccount.js"></script>
    <link rel="stylesheet" href="/resources/css/deleteForm.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/forAccountTablePage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/table-style.css" type="text/css">

    <title>Accounts</title>
</head>
<jsp:include page="blocks/navbar.jsp"/>
<body>

<div class="modal" id="deleteModal">
    <div class="modal-dialog formContentDelete">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="deleteForm">
                    <input type="hidden" id="idDelete">
                    <label class="questionForm"><spring:message code="confirmDelete.label"/></label>
                    <div id="deleteCurrentUserMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="deleteCurrentUser.error"/></p></div>
                    <hr>
                    <button class="btn  btn-primary" id="deleteBtn"
                            onclick="deleteRecord($('#idDelete').val());return false;"
                            type="submit"><spring:message code="delete.button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="updateAccountModal">
    <div class="modal-dialog formContent">
        <div class="modal-header">
            <a href="#" class="close" data-dismiss="modal">&times;</a>
            <h5><spring:message code="update.label"/></h5>
        </div>
        <div class="modal-body">
            <form id="updateForm">
                <input type="hidden" id="idUpdate" name="id">
                <div class="form-label-group">
                    <input type="text" id="nameUpdate" class="form-control" placeholder="Name" required
                           autofocus>
                    <label for="nameUpdate"><spring:message code="name"/></label>
                </div>
                <div class="form-label-group">
                    <input type="text" id="phoneUpdate" class="form-control" placeholder="Phone" required
                           autofocus>
                    <label for="phoneUpdate"><spring:message code="phone"/></label>
                </div>
                <div class="form-label-group">
                    <input type="text" id="emailUpdate" class="form-control"
                           placeholder="Email" required autofocus>
                    <label for="emailUpdate"><spring:message code="email.label"/></label>
                </div>
                <div class="form-label-group" id="role"></div>
                <div id="uniqueFieldMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="uniqueField.error"/></p></div>
                <div id="emptyFieldMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyField.error"/></p></div>
                <a href="/account/showAccounts" class="btn btn-primary btn-block" id="updateRecord"
                   onclick="update();return false;"><spring:message code="save.button"/></a>
            </form>
        </div>
    </div>
</div>

<div class="position-table">
    <br/><br/>
    <label><spring:message code="numberRows.label"/></label>
    <select id="numberRows" class="mdb-select md-form select-option">
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="20">20</option>
    </select>

    <input type="text" class="search-input pull-right" placeholder="Search" id="search"
           onkeyup="$('#tableBody').empty();loadAccounts(this.value, $('#numberRows').val())">
    <table id='table' class='table table-hover table-bordered table-sm' cellspacing='0' width='100%'>
        <thead class="black white-text table-info">
        <tr>
            <th scope="col"><spring:message code="username.label"/></th>
            <th scope="col"><spring:message code="phone.label"/></th>
            <th scope="col"><spring:message code="email.label"/></th>
            <th scope="col"><spring:message code="role.label"/></th>
            <th scope="col"><spring:message code="delete.label"/></th>
            <th scope="col"><spring:message code="update.label"/></th>
        </tr>
        </thead>
        <tbody id='tableBody'>
        </tbody>
        <script type="text/javascript">

            loadAccounts($('#search').val(), 5);
            loadRoles();

        </script>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item">
                <button type="submit" class="btn btn-primary" id="previousBtn"
                        onclick="$('#tableBody').empty();loadPage=loadPage-1;loadAccounts($('#search').val(),$('#numberRows').val());">
                    <spring:message code="previous"/></button>
            </li>
            <li class="page-item">
                <button type="submit" class="btn btn-primary" id="nextBtn"
                        onclick="$('#tableBody').empty();loadPage=loadPage+1;loadAccounts($('#search').val(),$('#numberRows').val());">
                    <spring:message code="next"/></button>
            </li>
        </ul>
    </nav>
</div>

<script>window.jQuery || document.write('<script src="/webjars/jquery/3.1.1/jquery.min.js"><\/script>')</script>
</body>
<jsp:include page="blocks/footer.jsp"/>
</html>

