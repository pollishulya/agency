function loadOrders() {
    $.get("/reservation/load", function (data) {
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
    $.get("/reservation/load", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td><a href='/food/"
                    + data[i].foodId + "' class='food-href'>" + data[i].nameFood + "</a></td>";

            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}