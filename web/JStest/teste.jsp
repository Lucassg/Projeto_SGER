<%-- 
    Document   : teste
    Created on : 25/04/2017, 00:13:31
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <br>
        <div id="divpie" class="graf" style="background: #000; float: top; width: 400px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="divcombo" class="graf" style="background: #007fff; float: top; display: none; width: 400px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="divbar" class="graf" style="background: #00dd1c; float: top; display: none; width: 400px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="divcolumn" class="graf" style="background: #761c19; float: top; display: none; width: 400px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="divgeo" class="graf" style="background: yellow; float: top; display: none; width: 400px; height: 400px; margin: 0 auto">
            <br>
        </div>
        <div id="div6" style="float: bottom; width: 400px; margin: 0 auto">
            <form action="" >
                <input id="pie" type="radio" name="chart" value="pie"> Pie
                <input id="combo" type="radio" name="chart" value="combo"> Combo
                <input id="bar" type="radio" name="chart" value="bar"> Bar
                <input id="column" type="radio" name="chart" value="column"> Column
                <input id="geo" type="radio" name="chart" value="geo"> Geo
            </form>
        </div>
    </body>
    <script type="text/javascript" src="../JSProjeto/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="teste.js"></script>
</html>
