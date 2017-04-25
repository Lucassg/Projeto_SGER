$(document).ready(function() {
    $("input[name$='chart']").click(function() {
        var select = $(this).val();

        $("div.graf").hide();
        $("#div" + select).show();
    });
});