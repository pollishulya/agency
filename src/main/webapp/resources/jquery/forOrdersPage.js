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


function loadOrders() {
    $.get("/reservation/loadCompany", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td>" + data[i].status + "</td><td>" + convertDate(data[i].date) + "</td><td><a href='/food/"
                    + data[i].foodId + "' class='food-href'>" + data[i].nameFood + "</a></td>";

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

            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}