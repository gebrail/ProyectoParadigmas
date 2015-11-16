<%-- 
    Document   : Index
    Created on : 10/09/2015, 06:49:00 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de Session</title>
        <link href="assets/css/bootstrap.css" rel="stylesheet">
        <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="assets/css/style.css" rel="stylesheet">
        <link href="assets/css/style-responsive.css" rel="stylesheet">
        <script type="text/javascript" src="js/validaciones.js"></script>
        <link rel="shortcut icon" href="img/favicon.png" /> 
             
    </head>
    <body>

        <div id="login-page">
            <div class="container">

                <form class="form-login" method="post" id="iniciosesion" name="iniciosesion">
                    <input type="hidden" name="opcion" id="opcion" value="0"> 
                    <!-- El value es igual a cero por que va a la funcion de iniciar sesion que esta en el servlet de controllerpersona rol Aqui es breve tu le mandas el numero-->

                    <h2 class="form-login-heading">Login School</h2>
                    <div class="login-wrap">
                        <input type="text" class="form-control" placeholder="Usuario" id="elusuario" name="usuario"  autofocus>
                        <br>
                        <input type="password" class="form-control" placeholder="Contraseña" id="lacontra" name="password" autofocus>
                        <label class="checkbox">
                        </label>
                        <button class="btn btn-theme btn-block" type="button" onclick='eliniciosesion(document.getElementById("elusuario").value, document.getElementById("lacontra").value);' type="submit"><i class="fa fa-lock"></i> Entrar</button>
                        <%
                            if (request.getParameter("error") != null) {
                                out.println("<h4 align=\"center\" class=\"text-danger\">" + request.getParameter("error") + "</h4>");
                            }
                        %>   
                    </div>
                </form>	  	

            </div>
        </div>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

        <!--BACKSTRETCH-->
        <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->

        <!-- aqui viene la imagen de fondo haciendo un llamado a una funcion de javascript del template de boostrap :D sapbeee-->

        <script type="text/javascript" src="assets/js/jquery.backstretch.min.js"></script>
        <script>
                            $.backstretch("img/438764.png", {speed: 500});
        </script>


    </body>
</html>
