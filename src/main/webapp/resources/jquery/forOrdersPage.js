function loadOrders() {
    $.get("/reservation/load", function (data) {
        if (data.length > 0) {
            $('#noReservationMessage').empty();
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr id='row_" + data[i].id + "'><td>" + data[i].username + "</td><td>" + data[i].phone + "</td><td>"
                    + data[i].numberPerson + "</td><td><a href='/tour/"
                    + data[i].tourId + "' class='tour-href'>" + data[i].nameTour + "</a></td>";

            }
            $('#tableBody').append(html);
        } else {
            $('#table').detach();


        }
    });
}