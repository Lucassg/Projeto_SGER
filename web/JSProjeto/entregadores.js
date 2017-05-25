
function runclick() {

    var tdcont = document.getElementById('contador').value;

    for (var i = 1; i <= tdcont; i++) {
        var rota = "rota" + i;
        var select1 = document.getElementById(rota).value;
        
        if(select1 == null){
            alert("Atribua um entregador a cada uma das rotas geradas antes de continuar.");
            return false;
        }
    }

//    var rota = "rota" + i;
//    var select1 = document.getElementById(rota).value;
//    console.log("contador: " + tdcont);
//    console.log("select1: " + select1);
}