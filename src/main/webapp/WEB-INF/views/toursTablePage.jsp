<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <script src=" //cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">

    <script src="/resources/jquery/actionsWithTours.js"></script>
    <link rel="stylesheet" href="/resources/css/deleteForm.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/table-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/forTourTablePage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/formTourAdd-style.css" type="text/css">

    <title>Tours</title>
</head>
<jsp:include page="blocks/navbar.jsp"/>
<body>
<div class="modal fade" id="addTourModal">
    <div class="modal-dialog formContent">
        <div class="modal-header">
            <a href="#" class="close" data-dismiss="modal">&times;</a>
            <h5 id="title"><spring:message code="addTour.label"/></h5>
        </div>
        <div class="modal-body">
            <form id="addForm">
                <div class="form-label-group">
                    <input type="text" id="name" name="name" class="form-control" placeholder="Tour name" required
                           autofocus>
                    <label for="name"><spring:message code="tourName.label"/></label>
                </div>
                <div class="form-label-group">
                    <input type="text" id="country" name="country" class="form-control" placeholder="Country" required
                           autofocus>
                    <label for="country"><spring:message code="country.label"/></label>
                </div>
                <label for="exitDate"><spring:message code="exitDate.label"/></label>
                <div class="form-label-group">
                    <input type="date" id="exitDate" name="exitDate" class="form-control" required autofocus>
                </div>
                <div class="form-label-group">
                    <input type="text" id="numberDays" name="numberDays" class="form-control" placeholder="Number days"
                           required
                           autofocus>
                    <label for="numberDays"><spring:message code="numberDays.label"/></label>
                </div>
                <div class="form-label-group">
                    <input type="text" id="cost" name="cost" class="form-control" placeholder="Cost" required
                           autofocus>
                    <label for="cost"><spring:message code="cost.label"/></label>
                </div>
                <div class="form-label-group" id="type">
                    <select id="types" class="mdb-select md-form">
                        <option value="BUS_TOUR"><spring:message code="busTour.label"/></option>
                        <option value="AIR_TOUR"><spring:message code="airTour.label"/></option>
                        <option value="SEE_TOUR"><spring:message code="seeTour.label"/></option>
                    </select>
                </div>
                <label><spring:message code="description.label"/></label>
                <div class="form-label-group" id="descriptions">
                    <textarea class="description" id="description_0"></textarea>
                </div>
                <div class="form-label-group pull-right">
                    <button type="button" class="btn btn-primary btn-xs" onclick="addDay();">+</button>
                    <button type="button" class="btn btn-primary btn-xs" onclick="deleteDay();">-</button>
                </div>
                <hr>
                <div id="uniqueTourFieldMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="uniqueTourNameField.error"/></p></div>
                <div id="emptyFieldMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyField.error"/></p></div>
                <div id="emptyDescriptionMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyDescription.error"/></p></div>
                <div id="incorrectDataMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="incorrectDate.error"/></p></div>
                <a href="/showTours" class="btn btn-primary btn-block" id="saveTourBtn"
                   type="button" onclick="saveTour();return false;"><spring:message code="save.button"/>
                </a>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteModal">
    <div class="modal-dialog formContentDelete">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="deleteForm">
                    <input type="hidden" id="idDeleteTour">
                    <label class="questionForm"><spring:message code="confirmDelete.label"/></label>
                    <div id="deleteTourMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="deleteTour.error"/></p></div>
                    <hr>
                    <button class="btn  btn-primary" id="deleteBtn"
                            onclick="deleteRecord($('#idDeleteTour').val());return false;"
                            type="submit"><spring:message code="delete.button"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="updateTourModal">
    <div class="modal-dialog formContent">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times</a>
                <h4 id="ModalTitleUpdate"></h4>
            </div>
            <div class="modal-body">
                <form id="updateForm">
                    <fieldset id="submitFormUpdate">

                        <input type="hidden" id="idUpdate" name="id">

                        <div class="form-label-group">
                            <input type="text" id="nameUpdate" name="nameUpdate" class="form-control"
                                   placeholder="Tour name" required autofocus>
                            <label for="nameUpdate"><spring:message code="tourName.label"/></label>
                        </div>

                        <div class="form-label-group">
                            <input type="text" id="countryUpdate" name="countryUpdate" class="form-control"
                                   placeholder="Country" required autofocus>
                            <label for="countryUpdate"><spring:message code="country.label"/></label>
                        </div>

                        <label><spring:message code="exitDate.label"/></label>
                        <div class="form-label-group">
                            <input type="date" id="exitDateUpdate" name="exitDateUpdate" class="form-control"
                                             required autofocus>
                        </div>

                        <div class="form-label-group">
                            <input type="text" id="numberDaysUpdate" name="numberDaysUpdate" class="form-control"
                                   placeholder="Number days" required autofocus>
                            <label for="numberDaysUpdate"><spring:message code="numberDays.label"/></label>
                        </div>

                        <div class="form-label-group">
                            <input type="text" id="costUpdate" name="costUpdate" class="form-control" placeholder="Cost"
                                   required autofocus>
                            <label for="costUpdate"><spring:message code="cost.label"/></label>
                        </div>

                        <div class="form-label-group" id="typeUpdate">
                            <select id="typesUpdate" class="mdb-select md-form">
                                <option value="BUS_TOUR"><spring:message code="busTour.label"/></option>
                                <option value="AIR_TOUR"><spring:message code="airTour.label"/></option>
                                <option value="SEE_TOUR"><spring:message code="seeTour.label"/></option>
                            </select>
                        </div>
                        <label><spring:message code="description.label"/></label>
                        <div class="form-label-group description" id="descriptionsUpdate"></div>
                        <div class="form-label-group pull-right">
                            <button type="button" class="btn btn-primary btn-xs" onclick="addDayInUpdate();">+
                            </button>
                            <button type="button" class="btn btn-primary btn-xs" onclick="deleteDayInUpdate();">-
                            </button>
                        </div>
                        <hr>
                        <div id="uniqueTourFieldUpdateMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="uniqueTourNameField.error"/></p></div>
                        <div id="emptyFieldUpdateMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyField.error"/></p></div>
                        <div id="emptyDescriptionUpdateMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyDescription.error"/></p></div>
                        <div class="form-group">
                            <a href="/showTours" class="btn btn-primary btn-block" id="updateRecord"
                               onclick="updateTour();return false;"><spring:message code="save.button"/></a>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="position-table">
    <button class="btn btn-info" onclick="AddNew(0)"><spring:message code="addTour.button"/></button>
        <br/><br/>
    <label><spring:message code="numberRows.label"/></label>
    <select id="numberRows" class="mdb-select md-form select-option">
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="20">20</option>
    </select>
    <input type="text" class="search-input pull-right" placeholder="Search" id="search"
           onkeyup="$('#tableBody').empty();loadTours(1,this.value, $('#numberRows').val())">

    <div id="noTourMessage">
        <div class='panel panel-warning'>
            <div class='panel-heading'></div>
            <div class='panel-body'><spring:message code='noTour.error'/></div>
        </div>
    </div>

    <table id='tourTable' class='table table-hover table-bordered table-sm' cellspacing='0' width='100%'>
        <thead class="black white-text">
        <tr>
            <th scope="col"><spring:message code="tourName.label"/></th>
            <th scope="col"><spring:message code="country.label"/></th>
            <th scope="col"><spring:message code="exitDate.label"/></th>
            <th scope="col"><spring:message code="numberDays.label"/></th>
            <th scope="col"><spring:message code="costForTable.label"/></th>
            <th scope="col"><spring:message code="rating.label"/></th>
            <th scope="col"><spring:message code="type.label"/></th>
            <th scope="col"><spring:message code="delete.label"/></th>
            <th scope="col"><spring:message code="update.label"/></th>
        </tr>
        </thead>
        <tbody id='tableBody'>
        </tbody>
    </table>
    <script type="text/javascript">
        loadTours(1, $('#search').val(), 5);
    </script>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item">
                <button type="submit" class="btn btn-primary" id="previousBtn"
                        onclick="loadPage=loadPage-1;loadTours(1,$('#search').val(),$('#numberRows').val());">
                    <spring:message code="previous"/></button>
            </li>
            <li class="page-item">
                <button type="submit" class="btn btn-primary" id="nextBtn"
                        onclick="loadPage=loadPage+1;loadTours(1,$('#search').val(),$('#numberRows').val());">
                    <spring:message code="next"/></button>
            </li>
        </ul>
    </nav>
</div>

</body>
<jsp:include page="blocks/footer.jsp"/>
</html>

