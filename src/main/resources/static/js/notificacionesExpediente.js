$(document).ready(function () {
    doAjaxExp();
});
function doAjaxExp() {
    var data = "index";
    $.ajax({
        url: "/notificationExpediente",
        type: "POST",
        data: data,
        dataType: "json",
        success: function (res) {
            for (var i = 0; i < res.length; i++) {
                createAlertExp(res[i]);
            }
        },
        error: function (err) {
            console.error(err);
        }
    });
}

function createAlertExp(obj) {

    var alertClasses = ["alert", "animated", "flipInX"];
    alertClasses.push("alert-danger");
    alertClasses.push("alert-dismissible");
    
    
    var msg = $("<div />", {
        "class": alertClasses.join(" ")
    });

    $("<h6 />", {
        html: obj.Filename
    }).appendTo(msg);
    $("<strong />", {
        html: "La fecha de almacenaje para el expediente "+obj.Filename+ " se venció, el expediente fue el crado el día "+ obj.Create +", por favor realizar el traslado."
    }).appendTo(msg);

    $("<p />", {
        html: "El expediente se encuantra compartido con el usuario " + obj.Receptor
    }).appendTo(msg);

    $("<a />", {
        name: "link",
        href: "/offices/pendingExpediente",
        text: "Expedientes"
    }).appendTo(msg);

    $("<span />", {
        "class": "close",
        "data-dismiss": "alert",
        html: "X"
    }).appendTo(msg);

    $('#pageMessages').prepend(msg);
}
