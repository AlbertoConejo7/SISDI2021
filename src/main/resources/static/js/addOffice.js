function saveResponseTime(){
    var limite=document.getElementById("responseTimeValue");
    var alert=document.getElementById("alert-save");
    var error=document.getElementById("errorResponseTime");
    
    
    if(limite.value==""){
        error.innerText="Campo requerido";
        error.classList.add("show-message");
        error.classList.remove("hide-message");
        limite.classList.add("error-input");
    }
    else{
        
        error.innerText="Error";
        $("#responseTime .close").click();
        error.classList.remove("show-message");
        error.classList.add("hide-message");
        limite.classList.remove("error-input");
        alert.classList.add("show");
        alert.classList.remove("fade");
        console.log(limite.value);
    }
}
