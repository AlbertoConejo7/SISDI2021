var date = new Date();
var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
$('#datepicker1').datepicker({
    format: "dd/mm/yyyy",
    weekStart: 1,
    language: "es",
    startDate: today,
    todayHighlight: true
});
//var hour= new Time();
//var 
