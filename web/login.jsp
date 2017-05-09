<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8"> <!-- Permite acentuação no site -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="SGER - Sistema de Gerenciamento de Entregas para Restaurantes"> <!-- Descrição da página -->
        <meta name="author" content="Leonardo, Lucas Gacia, Lucas Martins"> <!-- autores -->
        <link rel="icon" href="img/motoboy.ico"> <!-- imagem que aparece no navegador-->
        <link href="CSSLogin/bootstrap.min.css" rel="stylesheet" async="true"><!-- Bootstrap core CSS -->
        <link href="CSSLogin/ie10-viewport-bug-workaround.css" rel="stylesheet" async="true"><!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->   
        <link href="CSSLogin/signin.css" rel="stylesheet" async="true"> <!-- Custom styles for this template -->
        <title>Bem vindo ao SGER</title>
        
        <c:if test="${Falha != null}">
            <script type="text/javascript"> alert("${Falha}");</script>
        </c:if>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" name="formlogin" action="Controle?classe=ControleLogicoFuncionario&acao=autenticar_usuario" method="POST">
                <h2 class="form-signin-heading">&emsp; SGER - LOGIN:</h2>
                <label for="login" class="sr-only">Login</label>
                <input type="text" class="form-control" placeholder="Login" required autofocus size="20" name="login">
                <label for="senha" class="sr-only">Senha</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Senha" required size="20" name="senha">
                <!-- <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Salvar senha
                    </label>
                </div> -->
                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            </form>
        </div> <!-- /container -->
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="JSLogin/ie10-viewport-bug-workaround.js" async="true"></script>
        <script src="JSLogin/ie-emulation-modes-warning.js" async="true"></script>
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    </body>
</html>
