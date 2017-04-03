function centralizarJanela(url, title, w, h) {
    var esquerda = (screen.width / 2) - (w / 2);
    var topo = (screen.height / 2) - (h / 2);
    return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + topo + ', left=' + esquerda);
}


