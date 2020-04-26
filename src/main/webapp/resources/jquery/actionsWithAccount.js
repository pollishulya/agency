var loadPage = 0;

function deleteRecord(id) {
    $.ajax({
        type: "POST",
        url: "/account/delete/" + id,
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

function updateRecord(id) {
    var url = "/account/update/" + id;
    $("#updateForm")[0].reset();
    $("#updateAccountModal").modal();
    $.ajax({
        type: "GET",
        url: url,
        success: function (data) {
            $("#idUpdate").val(data.id);
            $("#firstnameUpdate").val(data.firstname);
            $("#lastnameUpdate").val(data.lastname);
            $("#phoneUpdate").val(data.phone);
            $("#emailUpdate").val(data.email);
            $("#roles").val(data.role);

        },
        error: function (data) {
            $("#errorForm")[0].reset();
            $("#errorModal").modal();
        }
    })
}

function deleteFormHide() {
    $("#deleteModal").modal("hide");
}

function update() {
    $('#uniqueFieldMistake').hide();
    $('#emptyFieldMistake').hide();
    var firstname = $('#firstnameUpdate').val();
    var lastname = $('#lastnameUpdate').val();
    var phone = $('#phoneUpdate').val();
    var email = $('#emailUpdate').val();
    var id = $('#idUpdate').val();
    var role = $('#roles').val();
    var accountDto = ({
        "id": id,
        "firstname": firstname,
        "lastname": lastname,
        "phone": phone,
        "email": email,
        "role": role
    });
    alert(JSON.stringify(accountDto));
    $.ajax({
        type: "Post",
        contentType: "application/json;charset=utf-8",
        url: "/account/saveAfterUpdate",
        data: JSON.stringify(accountDto),
        success: function (result) {
            window.location.replace("http://localhost:8080/account/showAccounts");
        },
        error: function (result) {
            if (result.status === 500) {
                $('#uniqueFieldMistake').show();
            } else if (result.status === 400) {
                $('#emptyFieldMistake').show();
            }

        }
    });
}


function loadAccounts(param, numberRows) {

    $.ajax({
        type: "GET",
        url:"/account/findAccounts?param=" + param + "&pageSize=" + numberRows + "&pageNumber=" + loadPage,
        success: function (data) {
            $("#nextBtn").attr("disabled", false);
            if (loadPage == 0) {
                $("#previousBtn").attr("disabled", true);
            } else {
                $("#previousBtn").attr("disabled", false);
            }
            if (data.length < numberRows) {
                $("#nextBtn").attr("disabled", true);
            } else {
                $("#nextBtn").attr("disabled", false);
            }
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].firstname + "</td><td>" + data[i].lastname + "</td><td>" + data[i].phone + "</td><td>" +
                    data[i].email + "</td><td>" + data[i].role + "</td>";
                html += "<td>" + "<button class='btn btn-danger' onclick='deleteForm(" + data[i].id +
                    ")'><span class='glyphicon glyphicon-trash'></span></button>" + "</td>";
                html += "<td>" + "<button class='btn btn-primary' onclick='updateRecord(" + data[i].id + ")'>Update</button>" + "</td></tr>";

            }
            $('#tableBody').append(html);

        },
        error: function (data) {
            $("#errorForm")[0].reset();
            $("#errorModal").modal();
        }
    })
}

function loadRoles() {
    $.ajax({
        type: "GET",
        url: "/account/roles",
        success: function (data) {
            var html = '<select id="roles" class="mdb-select md-form ">';
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].role + "'>" + data[i].role + "</option>";
            }
            html += '</select>';
            $('#role').append(html);

        },
        error: function (data) {
            $("#errorForm")[0].reset();
            $("#errorModal").modal();
        }
    })
}

$(document).ready(function () {
    $("#numberRows").change(function () {
        var param = $('#search').val();
        var selectedOption = $('#numberRows').val();
        if (selectedOption != '') {
            $('#tableBody').empty();
            loadAccounts(param, selectedOption);
        }
    });
});

$(document).ready(function () {
    $(".dropdown-toggle").dropdown();
});