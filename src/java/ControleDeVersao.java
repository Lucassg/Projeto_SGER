/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas
 */
public class ControleDeVersao {
 /*
    Para fins de controle de versão e anotações relevantes para projeto
   
    Dia 06/03/2017
    
    - Autor: Lucas Garcia 
    
    INICIO LUCAS GARCIA
    
    - Movi os arquivos das pastas CSSMapa e JSMapa para 
    CSSProjeto e JSProjeto, respectivamente. Alterei as referências na parte web 
    e tudo parece estar funcionando como esperado. Também removi algumas linhas de
    código desnecessárias ou que estavam comentadas na parte web.
    
    - Outra coisa que acertei foi mover a classe RotaTeste para o modelo, alterando
    o nome para RotaTemp e fazendo as devidas referências no controle.
    
    - Tentei utilizar um caminho relativo para a criação do arquivo JSON com os 
    endereços para os múltiplos pontos, mas por algum motivo, quando a requisição
    parte da view do projeto (JSP), a pasta do tomcat é reconhecida como a pasta
    raíz do projeto. Fiz um teste com a classe Main utilizando o caminho relativo
    './web/JSON/nome_do_arquivo.json' e funcionou corretamente, o que me leva a 
    reforçar a hipótese que requisições vindas a partir do JSP, passando pela servlet
    e no mapeamento do servidor causam esse problema. Esse é um ponto que devemos
    levar ao Thiago. Não consegui achar nada que possa resolver o nome problema até 
    o momento.
    
    Para testar essa hipótese, basta dar acesso de gravação para o seu usuário a
    pasta to tomcat com o mesmo e o netbeans fechados. use apenas './nome_do_arquivo.json'
    para criar o arquivo no ControleLogicoRota e procure pelo arquivo JSON na pasta
    bin dentro da pasta to tomcat.
    
    - O problema com a codificação em UFT-8 no json ainda continua. Tentei utilizar 
    alguns exemplos para converter os endereços vindos do banco, mas não funcionaram.
    O arquivo json continua tendo problemas com acentuação. Outro ponto para
    levarmos ao Thiago.
    
    - Precisamos começar a trabalhar na parte da formação das rotas. Seria bom 
    conversarmos sobre a maneira que cada um imagina como o algoritmo deve funcionar.
    
    
    FIM LUCAS GARCIA
    
    Fim Dia 06/03/2017
    
    ----------------------------------------------------------
    
    Inicio Dia 10/03/2017
    
    Autor: Lucas Garcia
    
    INICIO LUCAS GARCIA
    
    - Usei o código feito pelo Léo para criar e popular a matriz e fiz algumas alterações 
    para atender ao nosso propósito. Pelo que testei tudo esta funcionando como o esperado,
    porém, ainda não adicionei o endereço do estabelecimento no algoritmo. Isso precisa 
    ser feito.
    
    - A respeito do DistanceMatrix: Inicialmente o algoritmo consultava no maps cada par
    de endereços, gerando em um cenário de 15 pedidos, por exemplos, 105 requisições ao maps, 
    sem contar o endereço do estabelecimento. Dessa maneira a resposta do algortimo até a 
    matriz ser montada totabilizava em média 25 segundos de execução.
    
    - Durante a criação e testes do algoritmo eu acabei execendo a cota diária da chave
    do DistanceMatrix. Acabei não contabilizando a quantidade de requisições que fiz
    durante essa fase, mas deve ter sido por volta de 20~30 execuções de código. Se 
    o limite de pedidos ao maps é de 2500, meus testes realmente execederam a cota.
    
    - Para tentar reduzir o tempo de execução do algortimo eu tentei uma abordagem
    diferente na utilização do DistanceMatrix. Como havia dito, o código gerava
    uma requisição ao maps para cada par de endereços, totabilizando 105 requisições
    para cada 15 pedidos. 
    
    Segundo o Google Developers, o DistanceMatrix aceita até 25 endereços de origem
    e de destino como entrada. Se passarmos 5 endereços, por exemplo, tanto para a
    origem quanto para o destino, teremos um total de 25 resultados (nº de origens * nº de destinos). 
    O maps fará a combinação de cada origem com todos os destinos fornecidos.
    
    O que estou fazendo para tentar melhorar o tempo de execução é passar os endereços
    em lotes. Se temos 5 pedidos para entrega, então temos 10 possíveis pares de 
    endereços a serem gerados. A requisição ao maps ficaria da seguinte maneira:
    
    Req. 1 - origem endereço 1, destinos endereços 2, 3, 4 e 5;
    Req. 2 - origem endereço 2, destinos endereços 3, 4 e 5;
    Req. 3 - origem endereço 3, destinos endereços 4 e 5;
    Req. 4 - origem endereço 4, destino endereço 5;
    
    Dessa maneira diminuímos as 10 requisições do algortimo antigo para apenas 4
    do novo algoritmo, também diminuindo o tempo de execução em 68% de acordo com
    o que eu calculei.
    
    Nessa mesma linha de raciocínio eu pensei em mandar todos os endereços de uma
    única vez. Ou seja, se tivermos 5 pedidos enviariamos todos os 5 endereços
    tanto na origem quanto no destino e tratariamos o resultado no algoritmo para
    extrairmos apenas o que nos interessa. No entanto, recebi o seguinte erro ao 
    enviar 15 endereços:
   
    distancematrix OverQueryLimitException: You have exceeded your rate-limit for this API.
   
    Já verifiquei e a cota diária de requisições não foi execedida. Pode ser que 
    a quantidade de endereços foi exedida ou até mesmo a quantidade de caractéres
    de entrada ou de saída foram exedidos. Preciso verificar isso com calma.
    
    Também preciso verificar se essas otimizações trazem melhoras somente no tempo 
    de execuções ou na economia de requisições ao maps também.
    
    FIM LUCAS GARCIA
    
    Fim Dia 10/03/2016
 */   
}
