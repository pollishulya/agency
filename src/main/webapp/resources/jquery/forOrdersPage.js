function convertDate(exitDate) {
    var date = new Date(exitDate);
    var dd = date.getDate();
    var mm = date.getMonth() + 1;
    var yyyy = date.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    var result = dd + '/' + mm + '/' + yyyy;

    return result;
}


function deleteRecord(id) {
    $.ajax({
        type: "POST",
        url: "/reserve/delete/" + id,
        success: function (result) {
            deleteFormHide();
            $('#row_' + id).remove();
        },
        error: function (result) {
            $('#deleteCurrentUserMistake').show();
        }
    });
};

function deleteProgram(id) {
    $.ajax({
        type: "POST",
        url: "/bookingProgram/delete/" + id,
        success: function (result) {
            deleteFormHide();
            $('#row_' + id).remove();
        },
        error: function (result) {
            $('#deleteCurrentUserMistakeProgram').show();
        }
    });
};

function deleteProgram(id) {
    $.ajax({
        type: "POST",
        url: "/bookingLocation/delete/" + id,
        success: function (result) {
            deleteFormHide();
            $('#row_' + id).remove();
        },
        error: function (result) {
            $('#deleteCurrentUserMistakeProgram').show();
        }
    });
};

function AddNew() {
    $("#addForm")[0].reset();
    $("#addAccountModal").modal();
}


function deleteForm(id) {
    $('#deleteCurrentUserMistake').hide();
    $("#deleteForm")[0].reset();
    $("#deleteModal").modal();
    $('#idDeletePosition').val(id);

}



function deleteFormProgram(id) {
    $('#deleteCurrentUserMistakeProgram').hide();
    $("#deleteFormProgram")[0].reset();
    $("#deleteModalProgram").modal();
    $('#idDeleteProgram').val(id);
}

function deleteFormLocation(id) {
    $('#deleteCurrentUserMistakeLocation').hide();
    $("#deleteFormLocation")[0].reset();
    $("#deleteModalLocation").modal();
    $('#idDeleteLocation').val(id);
}

function cancelLocationForm(id) {
    $("#cancelLocationForm")[0].reset();
    $("#cancelLocationModal").modal();
    $('#idCancelLocation').val(id);
}

function cancelFoodForm(id, foodId, companyId, accountId) {
    $("#cancelFoodForm")[0].reset();
    $("#cancelFoodModal").modal();
    $('#idCancelFood').val(id);
    $('#foodIdCancelPosition').val(foodId);
    $('#companyIdCancelPosition').val(companyId);
    $('#accountIdCancelPosition').val(accountId);
}

function cancelProgramForm(id) {
    $("#cancelProgramForm")[0].reset();
    $("#cancelProgramModal").modal();
    $('#idCancelProgram').val(id);
}


function cancelLocation(id,locationId,companyId,accountId) {

       var location = ({
           "id": id,
           "locationId": locationId,
         //  "nameLocation":"jjj",
           "accountId": accountId,
           "companyId": companyId,
       // "status": "CANCEL"
    });
    $.ajax({
        type: "Post",
        url: "/bookingLocation/cancel/"+id ,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(location),
        success: function (res) {

          //  updateDescription();
           $("#updatePositionModal").modal("hide");
        },
        error: function (res) {
            if (res.status === 500) {
                $('#uniquePositionFieldUpdateMistake').show();
            } else if (res.status === 400) {
                $('#emptyFieldUpdateMistake').show();
            }
        }
    })

}
function cancelFood(id,foodId,companyId,accountId) {

    var food = ({
        "id": id,
        "foodId": foodId,
        //  "nameLocation":"jjj",
        "accountId": accountId,
        "companyId": companyId,
        // "status": "CANCEL"
    });
    $.ajax({
        type: "Post",
        url: "/bookingFood/cancel/"+id ,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(food),
        success: function (res) {

            //updateDescription();
          //  food.reload();
            $("#updatePositionModal").modal("hide");
        },
        error: function (res) {
            if (res.status === 500) {
                $('#uniquePositionFieldUpdateMistake').show();
            } else if (res.status === 400) {
                $('#emptyFieldUpdateMistake').show();
            }
        }
    })

}
function cancelProgram(id,programId,companyId,accountId) {

    var program= ({
        "id": id,
        "programId": programId,
        //  "nameLocation":"jjj",
        "accountId": accountId,
        "companyId": companyId,
        // "status": "CANCEL"
    });
    $.ajax({
        type: "Post",
        url: "/bookingProgram/cancel/"+id ,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(program),
        success: function (res) {

           // updateDescription();
           // program.reload();
            $("#updatePositionModal").modal("hide");
        },
        error: function (res) {
            if (res.status === 500) {
                $('#uniquePositionFieldUpdateMistake').show();
            } else if (res.status === 400) {
                $('#emptyFieldUpdateMistake').show();
            }
        }
    })

}

function deleteFormHide() {
    $("#deleteModal").modal("hide");
}









function loadOrdersFood() {
    $.get("/reservation/loadFood", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/food/"
                    + data[i].foodId + "' class='food-href'>" + data[i].nameFood + "</a></td>";
                html += "<td>" +  "<button id='delete' class='btn btn-primary'  onclick='cancelFood(" + data[i].id+","
                    +data[i].foodId+","+data[i].companyId+","+data[i].accountId+
                    ")'>Отменить</button>" + "</td>";
                html += "<td>";
            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}

function loadOrdersLocation() {
    $.get("/reservation/loadLocation", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/location/"
                    + data[i].locationId + "' class='food-href'>" + data[i].nameLocation + "</a></td>";
                html += "<td>" + "<button id ='delete' class='btn btn-primary' onclick='cancelLocation(" + data[i].id+","
                    +data[i].locationId+","+data[i].companyId+","+data[i].accountId+
                    ")'>Отменить</button>" + "</td>";
            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}

function loadOrdersProgram() {
    $.get("/reservation/loadProgram", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/program/"
                    + data[i].programId + "' class='food-href'>" + data[i].nameProgram + "</a></td>";
                html += "<td>" + "<button id='delete' class='btn btn-primary' onclick='cancelProgram(" + data[i].id+","
                    +data[i].locationId+","+data[i].companyId+","+data[i].accountId+
                    ")'>Отменить</button>" + "</td>";
            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}

function loadCompanyOrdersFood() {
    $.get("/reservation/loadCompanyFood", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/food/"
                    + data[i].foodId + "' class='food-href'>" + data[i].nameFood + "</a></td>";
                html += "<td>" + "<button id='deleteBtn' class='btn btn-primary' onclick='deleteForm(" + data[i].id +
                    ")'>Удалить</button>" + "</td>";
            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}
function loadCompanyOrdersLocation() {
    $.get("/reservation/loadCompanyLocation", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/location/"
                    + data[i].locationId + "' class='food-href'>" + data[i].nameLocation + "</a></td>";
                html += "<td>" + "<button id='deleteBtn' class='btn btn-primary' onclick='deleteFormLocation(" + data[i].id +
                    ")'>Удалить</button>" + "</td>";
            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}
function loadCompanyOrdersProgram() {
    $.get("/reservation/loadCompanyProgram", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/food/"
                    + data[i].programId + "' class='food-href'>" + data[i].nameProgram + "</a></td>";
                html += "<td>" + "<button id='deleteBtn' class='btn btn-primary' onclick='deleteFormProgram(" + data[i].id +
                    ")'>Удалить</button>" + "</td>";
            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}