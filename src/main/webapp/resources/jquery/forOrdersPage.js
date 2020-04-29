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

function AddNew() {
    $("#addForm")[0].reset();
    $("#addAccountModal").modal();
}


function deleteForm(id) {
    $('#deleteCurrentUserMistake').hide();
    $("#deleteForm")[0].reset();
    $("#deleteModal").modal();
    $('#idDelete').val(id);
}

function cancelRecord(id) {
    alert("CANC"+id);
    var url = "orders/cancel/" + id;
    $.ajax({
        type: "GET",
        url: url,

        success: function (data) {
            alert("s"+data);
            $("#idUpdate").val(data.id);
            $("CANCEL").val(data.status);
        },
        error: function (data) {
            alert("er"+data);
            $("#errorForm")[0].reset();
            $("#errorModal").modal();
        }
    })
}

function deleteFormHide() {
    $("#deleteModal").modal("hide");
}













function loadOrders() {
    $.get("/reservation/load", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/food/"
                    + data[i].foodId + "' class='food-href'>" + data[i].nameFood + "</a></td>";
                html += "<td>" + "<button id ='delete' class='btn btn-primary' onclick='cancelRecord(" + data[i].id +
                    ")'>Удалить</button>" + "</td>";
            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}


function loadCompanyOrders() {
    $.get("/reservation/loadCompany", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/food/"
                    + data[i].foodId + "' class='food-href'>" + data[i].nameFood + "</a></td>";
                html += "<td>" + "<button class='btn btn-danger' onclick='deleteForm(" + data[i].id +
                    ")'><span class='glyphicon glyphicon-trash'></span></button>" + "</td>";
            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}