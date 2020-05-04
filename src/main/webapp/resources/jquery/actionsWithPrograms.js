var loadPage = 0;
var numberDays = 0;

function deleteRecord(id) {
    $.ajax({
        type: "POST",
        url: "/program/delete/" + id,
        success: function (res) {
            $('#row' + id).remove();
            location.reload();
        },
        error: function (res) {
            $('#deletePositionMistake').show();
        }
    });
};


function deleteForm(id) {
    $("#deleteForm")[0].reset();
    $("#deleteModal").modal();
    $('#idDeleteProgram').val(id);
}

function loadPrograms(id, param, numberRows) {
    $.ajax({
        type: 'GET',
        url: "/programs/show?param=" + param + "&pageNumber=" + loadPage + "&pageSize=" + numberRows,
        success: function (res) {
            $('#tableBody').empty();
            $('#noPositionMessage').empty();
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
                html += "<tr id='row" + res[i].id + "'><td>" + res[i].name + "</td><td>" + res[i].duration + "</td><td>" +
                    res[i].price + "</td>" + "<td>" + setRating(res[i].rating) + "</td>" + "<td>" + res[i].type + "</td>";
                    html += "<td>" + "<button id='delete' class='btn btn-primary' onclick='deleteForm(" + res[i].id +
                    ")'>Удалить</button>" + "</td>";
                html += "<td>" + "<button id='update' class='btn btn-primary' onclick='updateRecord(" + res[i].id + ");return false;'>Редактировать</button>" + "</td></tr>";
            }
            $('#tableBody').append(html);
        },
        error: function (res) {
            if (loadPage == 0) {
                $('#positionTable').detach();
                $("#previousBtn").attr("disabled", true);
            }
            $("#nextBtn").attr("disabled", true);
        }

    });

}

function AddNew() {
    $("#addForm")[0].reset();
    $("#addPositionModal").modal();

}

function updateRecord(id) {
    $('#descriptionsUpdate').empty();
    var url = "/program/update/" + id;
    $("#updateForm")[0].reset();
    $("#updatePositionModal").modal();
    $.ajax({
        type: "GET",
        url: url,
        success: function (res) {
            $("#idUpdate").val(res.id);
            $("#nameUpdate").val(res.name);
            $("#durationUpdate").val(res.duration);
            $("#priceUpdate").val(res.price);
            $("#typesUpdate").val(res.type);

        }
    });
    $.ajax({
        type: "GET",
        url: "/description?programId=" + id,
        success: function (res) {
            numberDays = res.length;
            for (var i = 0; i < res.length; i++) {
                var html = "<input type='hidden' id='idDescription_" + i + "'><textarea class='description' id='descriptionUpdate_" + i + "'></textarea>";
                $('#descriptionsUpdate').append(html);
                $('#idDescription_' ).val(res[i].id);
                $('#descriptionUpdate_').val(res[i].description);
            }
        }
    });
}

//
function saveProgram() {

    var name = $('#name').val();
    var duration = $('#duration').val();
   // var exitDate = $('#exitDate').val();
    //var numberOfDays = $('#numberDays').val();
    var price = $('#price').val();
    var type = $('#types').val();
    var description= $('#description').val();
   // var descriptions = [];
   // for (var i = 0; i <= numberDays; i++) {
      /*  var descriptionText = $('#description_').val();
        var description = {
          //  "duration": i + 1,
            "description": descriptionText
        };
        descriptions.push(description);
   // }*/
    var programDto = ({
        "name": name,
        "duration": duration,
        "price": price,
        "type": type,
        "description": description
    });
    $.ajax({
        type: "Post",
        url: "/program/save",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(programDto),
        success: function (res) {
            program.reload();
            $("#addPositionModal").modal("hide");

        },
        error: function (res) {
            if (res.status === 500) {
                $('#uniquePositionFieldMistake').show();
            } else if (res.responseJSON === "empty field") {
                $('#emptyFieldMistake').show();
            } else if (res.responseJSON === "data error") {
                $('#incorrectDataMistake').show();
            }
        }
    })
};

function saveDescription() {

    var name = $('#name').val();
    var descriptions = [];
   // for (var i = 0; i <= numberDays; i++) {
        var descriptionText = $('#description_' ).val();
        var description = {
            "programName": name,
          //  "dayNumber": i + 1,
            "description": descriptionText
        };
        descriptions.push(description);
   // }
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

    $('#uniquePositionFieldMistake').hide();
    $('#emptyFieldMistake').hide();
    $('#emptyDescriptionMistake').hide();
    var i;
    var stop = 0;
 //   for (i = 0; i <= numberDays; i++) {
        var descriptionText = $('#description_' /*+ numberDays*/).val();
        if (descriptionText === "") {
            $('#emptyDescriptionMistake').show();
          //  stop = 1;
           // break;
        }
   // }

    if (stop === 0) {
        saveProgram();
        saveDescription();
    }
}

function updateProgram () {

    $('#uniquePositionFieldUpdateMistake').hide();
    $('#emptyFieldUpdateMistake').hide();

    var name = $('#nameUpdate').val();
    var duration = $('#durationUpdate').val();
    var id = $('#idUpdate').val();
    var price = $('#costUpdate').val();
    var type = $('#typesUpdate').val();

    var tour = ({
        "id": id,
        "name": name,
        "duration": duration,
        "price": price,
        "type": type
    });
    $.ajax({
        type: "Post",
        url: "/program/update",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(tour),
        success: function (res) {
            updateDescription();
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

};

function updateDescription() {
    var locationId = $('#idUpdate').val();
    var descriptions = [];
    for (var i = 0; i < numberDays; i++) {
        var idDescription = $('#idDescription_' + i).val();
        var descriptionText = $('#descriptionUpdate_' + i).val();
        var description = {
            "id": idDescription,
            "dayNumber": i + 1,
            "description": descriptionText,
            "programId": locationId
        };
        descriptions.push(description);
    }
    $.ajax({
        type: "Post",
        url: "/description/update",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(descriptions),
        success: function (res) {
            program.reload();
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
            loadFood(1, param, selectedOption);
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