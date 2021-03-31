$(document).ready(function () {
    doAjax();
});
function doAjax() {
    var data = "index";
    $.ajax({
        url: "/notificationResponse",
        type: "POST",
        data: data,
        dataType: "json",
        success: function (res) {
            console.log(res);
            for (var i = 0; i < res.length; i++) {
                createAlert(res[i]);
            }
        },
        error: function (err) {
            console.error(err);
        }
    });
}
//'Opps!', 'Something went wrong', 'Here is a bunch of text about some stuff that happened.', 'danger', true, 'pageMessages'
//title, summary, details, severity, dismissible, appendToId
function createAlert(obj) {

    var alertClasses = ["alert", "animated", "flipInX"];
    alertClasses.push("alert-info");
    alertClasses.push("alert-dismissible");

    var msg = $("<div />", {
        "class": alertClasses.join(" ")
    });

    $("<h6 />", {
        html: obj.Offnumber
    }).appendTo(msg);
    $("<strong />", {
        html: "La Fecha limite de Respuesta es: " + obj.Deadline
    }).appendTo(msg);

    $("<p />", {
        html: "El oficio fue emitido por " + obj.Emisor
    }).appendTo(msg);

    $("<a />", {
        name: "link",
        href: "/offices/pendingOffice",
        text: "Oficios Recibidos"
    }).appendTo(msg);

    $("<span />", {
        "class": "close",
        "data-dismiss": "alert",
        html: "X"
    }).appendTo(msg);

    $('#pageMessages').prepend(msg);
}
