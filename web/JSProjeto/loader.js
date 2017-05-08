
//$(window).load(function () {
//    // Animate loader off screen
//    $(".se-pre-con").fadeOut("slow");
//    ;
//});
//
//$(document).ready(function () {
//    $(".se-pre-con").fadeIn("slow");
//});

$('#botao').click(function() {
  $('#se-pre-con').toggle('slow', function() {
    // Animation complete.
  });
});


function click() {

    document.getElementById('.se-pre-con').style.display = "block";
}