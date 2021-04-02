$(document).ready(function () {
    doAjaxExp("expedientesTras");
});
function doAjaxExp(dir) {
    var data = "index";
    $.ajax({
        url: "/notificationExpediente",
        type: "POST",
        data: data,
        dataType: "json",
        success: function (res) {
            if(res.length==0 && dir!="pageMessages"){
                $('#expedientesTras').prepend($("<span />", {
                    html: "No hay Expedientes para trasladar"
                }));
            }
            for (var i = 0; i < res.length; i++) {
                createAlertExp(res[i], dir);
            }
        },
        error: function (err) {
            console.error(err);
        }
    });
}

function createAlertExp(obj, dir) {

    var alertClasses = "alert alert-info";
    
    var msg = $("<div/>", {
        "class": alertClasses
    });

    $("<h6 />", {
        html: obj.Filename
    }).appendTo(msg);
    $("<strong />", {
        html: "La fecha de almacenaje para el expediente se venció, por favor realizar el traslado. <br/>"
    }).appendTo(msg);

    $("<a />", {
        name: "link",
        href: "/offices/pendingExpediente",
        text: "Expedientes"
    }).appendTo(msg);


    $('#'+dir).prepend(msg);
}
