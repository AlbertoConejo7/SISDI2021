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
            for (var i = 0; i < res.length; i++) {
                verifiedDate(res[i]);
            }
        },
        error: function (err) {
            console.error(err);
        }
    });
}
function verifiedDate(obj){
    var day= new Date(obj.Deadline+"T00:00");
    var today=new Date();
    today.setHours(0,0,0,0);
    if(day<today){
         createAlert(obj, "danger");
    }
    else{
         createAlert(obj, "info");
    }
    
    
}
function createAlert(obj, type) {

    var alertClasses = ["alert", "animated", "flipInX"];
    alertClasses.push("alert-"+type);
    alertClasses.push("alert-dismissible");
    var strong="";
    
    if(type=="danger"){
        strong="La fecha límite de respuesta ya venció, debe responderlo pronto, la fecha límite de respuesta es: " + obj.Deadline;
    }else{
        strong="La fecha límite de respuesta es: " + obj.Deadline;
    }
    
    var msg = $("<div />", {
        "class": alertClasses.join(" ")
    });

    $("<h6 />", {
        html: obj.Offnumber
    }).appendTo(msg);
    $("<strong />", {
        html: strong
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
