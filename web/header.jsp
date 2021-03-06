<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> <!-- Permite acentuação no site -->
        <meta name="description" content="SGER - Sistema de Gerenciamento de Entregas para Restaurantes"> <!-- Descrição da página -->
        <meta name="author" content="Leonardo, Lucas Gacia, Lucas Martins"> <!-- autores -->
        <link href="CSSProjeto/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap core CSS -->
        <link href="CSSProjeto/StyleSger.css" rel="stylesheet"><!-- Tirado o async="true" por estar carregando depois do HTML --> 
        <link href="CSSProjeto/ie10-viewport-bug-workaround.css" rel="stylesheet"> <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="CSSProjeto/dashboard.css" rel="stylesheet"> <!-- Custom styles for this template -->
        <link rel="icon" href="img/motoboy.ico"> <!-- imagem que aparece no navegador-->
        <title>SGER - Sistema de Gerenciamento de Entregas para Restaurantes</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="Controle?classe=ControleLogicoRedirecionamento&page=index">SGER</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="Controle?classe=ControleLogicoFuncionario&acao=sair">Sair</a></li>
                    </ul>
                </div>
            </div>
        </nav>