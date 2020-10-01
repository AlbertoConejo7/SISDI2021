function saveResponseTime() {
    var limite = document.getElementById("responseTimeValue");
    var error = document.getElementById("errorResponseTime");

    if (limite.value == "") {
        error.innerText = "Campo requerido";
        error.classList.add("show-message");
        error.classList.remove("hide-message");
        limite.classList.add("error-input");
    } else {
        error.innerText = "Error";
        $("#responseTime .close").click();
        error.classList.remove("show-message");
        error.classList.add("hide-message");
        limite.classList.remove("error-input");
        showAlert();
    }
}
function mostrarResultados(datos) {
      console.log(datos);
}
function showAlert() {
    $("#alert-save").addClass("show");
    $("#alert-save").removeClass("fade");
    window.setTimeout(function () {
        $("#alert-save").alert('close');
    }, 3000);
};

function getJSON2(url, data, callback) {
     fetch(url, {
        method: 'POST',
        body: data
    }).then((result) => {
        return result.json();
    }).then(callback);
}