$(document).ready(function () {
    $(".dropdown-toggle").dropdown();
});

function loginForm() {
    $("#loginForm")[0].reset();
    $("#loginModal").modal();
}

function enterForm() {
    $("#enterForm")[0].reset();
    $("#enterModal").modal();
}



function signUp() {
    var name = $('#nameSignUp').val();
    var phone = $('#phoneSignUp').val();
    var email = $('#emailSignUp').val();
    var password = $('#passwordSignUp').val();
    var access = "user";
    var accountDto = ({
        "firstname": name,
        "phone": phone,
        "email": email,
        "password": password,
        "access": access
    });

            $.ajax({
                type:  "Post",
        url: "/registration",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(accountDto),
        success: function (result) {
            enterForm();
            $("#signUpModal").modal("hide");
        },
        error: function (result) {
            $('#uniqueFieldMistake').show();
            if (result.status === 500) {
                $('#uniqueFieldMistake').show();
            } else if (result.status === 400) {
                $('#emptyFieldMistake').show();
            }
            else { $('#emptyFieldMistake').show();}
        }
    })
};

function updateAccountSettings() {
    var url = "/account/settings";
    $("#settingsForm")[0].reset();
    $("#accountSettingsModal").modal();
    $.ajax({
        type: "GET",
        url: url,
        success: function (data) {
            $("#idUpdateViaUser").val(data.id);
            $("#nameUpdateViaUser").val(data.firstname);
            $("#phoneUpdateViaUser").val(data.phone);
            $("#emailUpdateViaUser").val(data.email);
            $("#roleUpdateViaUser").val(data.role);

        },
        error: function (data) {
            $("#accountSettingsModal").modal("hide");
            $("#errorForm")[0].reset();
            $("#errorModal").modal();
        }
    })
}

function updateAccount() {
    $('#uniqueFieldInSettingsMistake').hide();
    $('#uniqueFieldInSettingsMistake').hide();
    var name = $('#nameUpdateViaUser').val();
    var phone = $('#phoneUpdateViaUser').val();
    var email = $('#emailUpdateViaUser').val();
    var id = $('#idUpdateViaUser').val();
    var role = $('#roleUpdateViaUser').val();
    var accountDto = ({
        "id": id,
        "firstname": name,
        "phone": phone,
        "email": email,
        "role": role
    });

    $.ajax({
        type: "Post",
        contentType: "application/json;charset=utf-8",
        url: "/account/settings/save",
        data: JSON.stringify(accountDto),
        success: function (result) {
            window.location.reload();
        },
        error: function (result) {
            if (result.status === 500) {
                $('#uniqueFieldInSettingsMistake').show();
            } else if (result.status === 400) {
                $('#emptyFieldInSettingsMistake').show();
            }

        }
    });
}

function languageSelect(selectedOption) {
    window.location.replace(window.location.pathname + '?lang=' + selectedOption);
}


function timer()
{
    var tm=new Date();
    var h=tm.getHours();
    var m=tm.getMinutes();
    var s=tm.getSeconds();
    m=checkTime(m);
    s=checkTime(s);
    document.getElementById('timer').innerHTML=h+":"+m+":"+s;
    setTimeout('timer()',500);
}
function checkTime(i)
{
    if (i<10)
    {
        i="0" + i;
    }
    return i;
}