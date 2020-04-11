$(document).ready(function () {
    $(".dropdown-toggle").dropdown();
});

function loginForm() {
    $("#loginForm")[0].reset();
    $("#loginModal").modal();
}

function signUpForm() {
    $("#signUpForm")[0].reset();
    $("#signUpModal").modal();
}

function signUp() {
    var name = $('#nameSignUp').val();
    var phone = $('#phoneSignUp').val();
    var email = $('#emailSignUp').val();
    var password = $('#passwordSignUp').val();
    var accountDto = ({
        "name": name,
        "phone": phone,
        "email": email,
        "password": password
    });
    $.ajax({
        type: "Post",
        url: "/registration",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(accountDto),
        success: function (result) {
            loginForm();
            $("#signUpModal").modal("hide");
        },
        error: function (result) {
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
            $("#nameUpdateViaUser").val(data.name);
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
        "name": name,
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