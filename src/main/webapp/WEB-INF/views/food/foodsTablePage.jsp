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

    <script src="/resources/jquery/actionsWithFoods.js"></script>
    <link rel="stylesheet" href="/resources/css/deleteForm.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/table-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/forPositionTablePage.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/formPositionAdd-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/enter.css" type="text/css">


    <title>Foods</title>
</head>
<jsp:include page="../blocks/navbar1.jsp"/>
<jsp:include page="../blocks/navbar.jsp"/>
<body>
<div class="modal fade" id="addPositionModal" style="position: absolute; left: 300px;top:-100px">
    <div class="col">
        <div class="modal-header">
            <a href="#" class="close" data-dismiss="modal">&times;</a>
            <h5 class="title" style="position: relative; left: 110px;"><spring:message code="addPosition.label"/></h5>
        </div>
        <div class="modal-body">
            <form id="addForm">
                <div class="form-label-group">
                    <input type="text" id="name" name="name" class="form-control" placeholder="Position name" required
                           autofocus>
                    <label for="name"><spring:message code="foodName.label"/></label>
                </div>

                <div class="form-label-group" id="cuisine">
                    <select id="cuisines" class="mdb-select md-form">
                        <option value="ASIA"><spring:message code="asian.label"/></option>
                        <option value="SLAVIC"><spring:message code="slavic.label"/></option>
                        <option value="EAST"><spring:message code="east.label"/></option>
                        <option value="EUROPE"><spring:message code="europe.label"/></option>
                    </select>
                </div>
                <div class="form-label-group">
                    <input type="text" id="price" name="price" class="form-control" placeholder="Cost" required
                           autofocus>
                    <label for="price"><spring:message code="cost.label"/></label>
                </div>
                <div class="form-label-group" id="type">
                    <select id="types" class="mdb-select md-form">
                        <option value="SNACKS"><spring:message code="snacks.label"/></option>
                        <option value="FIRST"><spring:message code="first.label"/></option>
                        <option value="SECOND"><spring:message code="second.label"/></option>
                        <option value="DESSERT"><spring:message code="dessert.label"/></option>
                        <option value="DRINKS"><spring:message code="drinks.label"/></option>
                    </select>
                </div>
                <label><spring:message code="description.label"/></label>
                <div class="form-label-group" id="descriptions">
                    <textarea class="description" id="description"></textarea>
                </div>
                <div class="form-label-group pull-right">
                    <button type="button" class="btn btn-primary btn-xs" onclick="addDay();">+</button>
                    <button type="button" class="btn btn-primary btn-xs" onclick="deleteDay();">-</button>
                </div>
                <hr>
                <div id="uniquePositionFieldMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="uniquePositionNameField.error"/></p></div>
                <div id="emptyFieldMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyField.error"/></p></div>
                <div id="incorrectDataMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="bookingDate.error"/></p></div>
                <div id="bookingDataMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="incorrectDate.error"/></p></div>
                <a href="/showFoods" class="btn btn-primary btn-block" id="savePositionBtn"
                   type="button" onclick="savePosition();return false;"><spring:message code="save.button"/>
                </a>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteModal" style="position: absolute; left: 400px;top:-25px">
    <div class="col">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
            </div>
            <div class="modal-body">
                <form id="deleteForm">
                    <input type="hidden" id="idDeletePosition">
                    <label class="questionForm"><spring:message code="confirmDelete.label"/></label>
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
<div class="modal fade" id="updatePositionModal" style="position: absolute; left: 300px;top:-100px">
    <div class="col">

            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times</a>
                <h4 class="title" style="position: relative; left: 110px;">Редактирование</h4>
            </div>
            <div class="modal-body">
                <form id="updateForm">
                    <fieldset id="submitFormUpdate">

                        <input type="hidden" id="idUpdate" name="id">

                        <div class="form-label-group">
                            <input type="text" id="nameUpdate" name="nameUpdate" class="form-control"
                                   placeholder="Position name" required autofocus>
                            <label for="nameUpdate"><spring:message code="foodName.label"/></label>
                        </div>

                        <div class="form-label-group" id="cuisineUpdate">
                            <select id="cuisinesUpdate" class="mdb-select md-form">
                                <option value="ASIA"><spring:message code="asian.label"/></option>
                                <option value="SLAVIC"><spring:message code="slavic.label"/></option>
                                <option value="EAST"><spring:message code="east.label"/></option>
                                <option value="EUROPE"><spring:message code="europe.label"/></option>
                            </select>
                        </div>

                        <div class="form-label-group">
                            <input type="text" id="costUpdate" name="costUpdate" class="form-control" placeholder="Cost"
                                   required autofocus>
                            <label for="costUpdate"><spring:message code="cost.label"/></label>
                        </div>

                        <div class="form-label-group" id="typeUpdate">
                            <select id="typesUpdate" class="mdb-select md-form">
                                <option value="SNACKS"><spring:message code="snacks.label"/></option>
                                <option value="FIRST"><spring:message code="first.label"/></option>
                                <option value="SECOND"><spring:message code="second.label"/></option>
                                <option value="DESSERT"><spring:message code="dessert.label"/></option>
                                <option value="DRINKS"><spring:message code="drinks.label"/></option>
                            </select>
                        </div>
                        <label><spring:message code="description.label"/></label>
                        <div class="form-label-group description" id="descriptionsUpdate"></div>
                        <hr>
                        <div id="uniquePositionFieldUpdateMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="uniquePositionNameField.error"/></p></div>
                        <div id="emptyFieldUpdateMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyField.error"/></p></div>
                        <div id="emptyDescriptionUpdateMistake" hidden><p class='alert alert-danger' role='alert'><spring:message code="emptyDescription.error"/></p></div>
                        <div class="form-group">
                            <a href="/showFoods" class="btn btn-primary btn-block" id="updateRecord"
                               onclick="updatePosition();return false;"><spring:message code="save.button"/></a>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="backimage" class="position-table">
        <br/><br/>
    <label><spring:message code="numberRows.label"/></label>
    <select id="numberRows" class="mdb-select md-form select-option">
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="20">20</option>
    </select>
    <input type="text" class="search-input pull-right" placeholder="Search" id="search"
           onkeyup="$('#tableBody').empty();loadFood(1,this.value, $('#numberRows').val())">

    <div id="noPositionMessage">
        <div class='panel panel-warning'>
            <div class='panel-heading'></div>
            <div class='panel-body'><spring:message code='noPosition.error'/></div>
        </div>
    </div>
<div id="table">
    <table id='positionTable' class='table'>
        <thead class="thread-light">
        <tr>
            <th scope="col"><spring:message code="positionName.label"/></th>
            <th scope="col"><spring:message code="cuisine.label"/></th>
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
</div>
    <script type="text/javascript">
        loadFood(1, $('#search').val(), 5);
    </script>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item">
                <button type="submit" class="btn btn-primary" id="previousBtn"
                        onclick="loadPage=loadPage-1;loadFood(1,$('#search').val(),$('#numberRows').val());">
                    <spring:message code="previous"/></button>
            </li>
            <li class="page-item">
                <button type="submit" class="btn btn-primary" id="nextBtn"
                        onclick="loadPage=loadPage+1;loadFood(1,$('#search').val(),$('#numberRows').val());">
                    <spring:message code="next"/></button>
            </li>
        </ul>
        <button id="addmenu" class="btn btn-info" onclick="AddNew(0)"><spring:message code="addMenu.button"/></button>
    </nav>
</div>

</body>
<jsp:include page="../blocks/footer.jsp"/>
</html>

