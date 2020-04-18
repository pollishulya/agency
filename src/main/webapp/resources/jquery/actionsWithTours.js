var loadPage = 0;
var numberDays = 0;

function deleteRecord(id) {
    $.ajax({
        type: "POST",
        url: "/food/delete/" + id,
        success: function (res) {
            $('#row' + id).remove();
            location.reload();
        },
        error: function (res) {
            $('#deleteTourMistake').show();
        }
    });
};

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

function convertDateForUpdateField(exitDate) {

    var date = new Date(exitDate);
    var day = ("0" + date.getDate()).slice(-2);
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var today = date.getFullYear() + "-" + (month) + "-" + (day);

    return today;
}

function deleteForm(id) {
    $("#deleteForm")[0].reset();
    $("#deleteModal").modal();
    $('#idDeleteTour').val(id);
}

function loadTours(id, param, numberRows) {
    $.ajax({
        type: 'GET',
        url: "/foods/show?param=" + param + "&pageNumber=" + loadPage + "&pageSize=" + numberRows,
        success: function (res) {
            $('#tableBody').empty();
            $('#noTourMessage').empty();
            $("#nextBtn").attr("disabled", false);
            if (loadPage == 0) {
                $("#previousBtn").attr("disabled", true);
            } else {
                $("#previousBtn").attr("disabled", false);
            }
            if (res.length < numberRows) {
                $("#nextBtn").attr("disabled", true);
            } else {
                $("#nextBtn").attr("disabled", false);
            }
            var html = "";
            for (var i = 0; i < res.length; i++) {
                html += "<tr id='row" + res[i].id + "'><td>" + res[i].name + "</td><td>" + res[i].country + "</td><td>" +
                    convertDate(res[i].exitDate) + "</td>" + "<td>" + res[i].numberDays + "</td>" + "<td>" + res[i].cost + "</td>" + "<td>" + setRating(res[i].rating) + "</td>" + "<td>" + res[i].type + "</td>";
                html += "<td>" + "<button class='btn btn-danger' onclick='deleteForm(" + res[i].id +
                    ")'><span class='glyphicon glyphicon-trash'></span></button>" + "</td>";
                html += "<td>" + "<button class='btn btn-primary' onclick='updateRecord(" + res[i].id + ");return false;'>Update</button>" + "</td></tr>";
            }
            $('#tableBody').append(html);
        },
        error: function (res) {
            if (loadPage == 0) {
                $('#tourTable').detach();
                $("#previousBtn").attr("disabled", true);
            }
            $("#nextBtn").attr("disabled", true);
        }

    });

}

function AddNew() {
    $("#addForm")[0].reset();
    $("#addTourModal").modal();

}

function updateRecord(id) {
    $('#descriptionsUpdate').empty();
    var url = "/food/update/" + id;
    $("#updateForm")[0].reset();
    $("#updateTourModal").modal();
    $.ajax({
        type: "GET",
        url: url,
        success: function (res) {
            $("#idUpdate").val(res.id);
            $("#nameUpdate").val(res.name);
            $("#countryUpdate").val(res.country);
            $("#exitDateUpdate").val(convertDateForUpdateField(res.exitDate));
            $("#numberDaysUpdate").val(res.numberDays);
            $("#costUpdate").val(res.cost);
            $("#typesUpdate").val(res.type);

        }
    });
    $.ajax({
        type: "GET",
        url: "/description?foodId=" + id,
        success: function (res) {
            numberDays = res.length;
            for (var i = 0; i < res.length; i++) {
                var html = "<input type='hidden' id='idDescription_" + i + "'><textarea class='description' id='descriptionUpdate_" + i + "'></textarea>";
                $('#descriptionsUpdate').append(html);
                $('#idDescription_' + i).val(res[i].id);
                $('#descriptionUpdate_' + i).val(res[i].description);
            }
        }
    });
}

function saveTour() {

    var name = $('#name').val();
    var country = $('#country').val();
    var exitDate = $('#exitDate').val();
    var numberOfDays = $('#numberDays').val();
    var cost = $('#cost').val();
    var type = $('#types').val();
    var descriptions = [];
    for (var i = 0; i <= numberDays; i++) {
        var descriptionText = $('#description_' + i).val();
        var description = {
            "dayNumber": i + 1,
            "description": descriptionText
        };
        descriptions.push(description);
    }
  //  alert(exitDate,numberOfDays,cost,type,description);
    var foodDto = ({
        "name": name,
        "view": view,
        "exitDate": exitDate,
        "numberDays": numberOfDays,
        "cost": cost,
        "type": type,
        "foodDescriptions": descriptions
    });
  //  alert(foodDto.name);
    $.ajax({
        type: "Post",
        url: "/food/save",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(foodDto),
        success: function (res) {
          //  alert(foodDto.country);
            location.reload();
            $("#addTourModal").modal("hide");
        //    alert(foodDto.exitDate);
        },
        error: function (res) {
         //   alert(foodDto.type);
            if (res.status === 500) {
                $('#uniqueTourFieldMistake').show();
            } else if (res.responseJSON === "empty field") {
                $('#emptyFieldMistake').show();
            } else if (res.responseJSON === "data error") {
                $('#incorrectDataMistake').show();
            }
        }
    })
};
/*function saveTour() {

    var name = $('#name').val();
    var country = $('#country').val();
    var exitDate = $('#exitDate').val();
    var numberOfDays = $('#numberDays').val();
    var cost = $('#cost').val();
    var type = $('#types').val();
    var descriptions = [];
    for (var i = 0; i <= numberDays; i++) {
        var descriptionText = $('#description_' + i).val();
        var description = {
            "dayNumber": i + 1,
            "description": descriptionText
        };
        descriptions.push(description);
    }
    var tourDto = ({
        "name": name,
        "country": country,
        "exitDate": exitDate,
        "numberDays": numberOfDays,
        "cost": cost,
        "type": type,
        "tourDescriptions": descriptions
    });
    $.ajax({
        type: "Post",
        url: "/tour/save",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(tourDto),
        success: function (res) {
            location.reload();
            $("#addTourModal").modal("hide");

        },
        error: function (res) {
            if (res.status === 500) {
                $('#uniqueTourFieldMistake').show();
            } else if (res.responseJSON === "empty field") {
                $('#emptyFieldMistake').show();
            } else if (res.responseJSON === "data error") {
                $('#incorrectDataMistake').show();
            }
        }
    })
};*/
function saveDescription() {

    var name = $('#name').val();
    var descriptions = [];
    for (var i = 0; i <= numberDays; i++) {
        var descriptionText = $('#description_' + i).val();
        var description = {
            "foodName": name,
            "dayNumber": i + 1,
            "description": descriptionText
        };
        descriptions.push(description);
    }
    $.ajax({
        type: "Post",
        url: "/description/save",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(descriptions),
        success: function (res) {
        },
        error: function (res) {
        }
    })
};


function save() {

    $('#uniqueTourFieldMistake').hide();
    $('#emptyFieldMistake').hide();
    $('#emptyDescriptionMistake').hide();
    var i;
    var stop = 0;
    for (i = 0; i <= numberDays; i++) {
        var descriptionText = $('#description_' + numberDays).val();
        if (descriptionText === "") {
            $('#emptyDescriptionMistake').show();
            stop = 1;
            break;
        }
    }

    if (stop === 0) {
        saveTour();
        saveDescription();
    }
}

function updateTour() {

    $('#uniqueTourFieldUpdateMistake').hide();
    $('#emptyFieldUpdateMistake').hide();

    var name = $('#nameUpdate').val();
    var country = $('#countryUpdate').val();
    var exitDate = $('#exitDateUpdate').val();
    var id = $('#idUpdate').val();
    var numberDays = $('#numberDaysUpdate').val();
    var cost = $('#costUpdate').val();
    var type = $('#typesUpdate').val();

    var tour = ({
        "id": id,
        "name": name,
        "country": country,
        "exitDate": exitDate,
        "numberDays": numberDays,
        "cost": cost,
        "type": type
    });
    $.ajax({
        type: "Post",
        url: "/food/update",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(tour),
        success: function (res) {
            updateDescription();
            $("#updateTourModal").modal("hide");
        },
        error: function (res) {
            if (res.status === 500) {
                $('#uniqueTourFieldUpdateMistake').show();
            } else if (res.status === 400) {
                $('#emptyFieldUpdateMistake').show();
            }
        }
    })

};

function updateDescription() {
    var foodId = $('#idUpdate').val();
    var descriptions = [];
    for (var i = 0; i < numberDays; i++) {
        var idDescription = $('#idDescription_' + i).val();
        var descriptionText = $('#descriptionUpdate_' + i).val();
        var description = {
            "id": idDescription,
            "dayNumber": i + 1,
            "description": descriptionText,
            "foodId": foodId
        };
        descriptions.push(description);
    }
    $.ajax({
        type: "Post",
        url: "/description/update",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(descriptions),
        success: function (res) {
            location.reload();
        },
        error: function (res) {
        }
    })
}

// function update() {
//
//     $('#uniqueTourFieldUpdateMistake').hide();
//     $('#emptyFieldUpdateMistake').hide();
//     $('#emptyDescriptionUpdateMistake').hide();
//     var i;
//     var stop = 0;
//     for (i = 0; i <= numberDays; i++) {
//         var descriptionText = $('#descriptionUpdate_' + numberDays).val();
//         if (descriptionText === "") {
//             $('#emptyDescriptionUpdateMistake').show();
//             stop = 1;
//             break;
//         }
//     }
//
//     if (stop === 0) {
//         updateTour();
//         updateDescription();
//     }
// }

$(document).ready(function () {
    $("#numberRows").change(function () {
        var param = $('#search').val();
        var selectedOption = $('#numberRows').val();
        if (selectedOption != '') {
            $('#tableBody').empty();
            loadTours(1, param, selectedOption);
        }
    });
});

function addDay() {
    numberDays = numberDays + 1;
    var html = "<textarea class='description' id='description_" + numberDays + "'></textarea>";
    $('#descriptions').append(html);
}

function deleteDay() {
    if (numberDays >= 1) {
        $('#description_' + numberDays).detach();
        numberDays = numberDays - 1;
    }
}

function addDayInUpdate() {
    var html = "<textarea class='description' id='descriptionUpdate_" + numberDays + "' ></textarea>";
    $('#descriptionsUpdate').append(html);
    numberDays = numberDays + 1;
}

function deleteDayInUpdate() {
    if (numberDays > 1) {
        numberDays = numberDays - 1;
        var idDescription = $('#idDescription_' + numberDays).val();
        $('#descriptionUpdate_' + numberDays).detach();
        $.ajax({
            type: "POST",
            url: "/description/delete/" + idDescription,
            success: function (res) {

            }
        });
    }
}

function setRating(rating) {
    if (rating === -1) {
        return "—";
    }
    return rating;
}