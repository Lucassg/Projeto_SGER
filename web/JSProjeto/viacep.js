(function () {

    $('input[name="cep"]').blur(function(e) {
        var cep = $('input[name="cep"]').val() || ''

        if (!cep) {
            return
        }
        $.ajax({
            type: "GET",
            url: "validacaoServlet",
            data: "CEP=" + cep,
            success: function (result) {
                if (result == true) {
                    var url = 'http://viacep.com.br/ws/' + cep + '/json'
                    $.getJSON(url, function (data) {
                        if ("error" in data) {
                            return
                        }

                        $('input[name="rua"]').val(data.logradouro)
                        $('input[name="bairro"]').val(data.bairro)
                        $('input[name="cidade"]').val(data.localidade)
                        $('input[name="uf"]').val(data.uf)
                    })
                } else {
                    alert('Por gentileza informe ao Cliente, que endere\u00e7o est\u00e1 fora da area de entrega')
                    $('.inputcep').val('');
                }
            }
        });
    })
})()