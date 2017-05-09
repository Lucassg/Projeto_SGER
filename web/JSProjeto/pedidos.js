function runclick() {

    var tdcont = document.getElementById('tdcont');
    console.log(tdcont);

    if (tdcont == null) {
        alert("Adicione algum produto antes de fechar o pedido.");
        return false;
    }else{
        return true;
    }
}