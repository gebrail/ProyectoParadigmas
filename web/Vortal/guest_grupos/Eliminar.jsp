<%-- 
    Document   : Eliminar
    Created on : 19/09/2015, 12:55:15 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="Gebrail Hernandez">
        <meta name="keywords" content="HTML5, CSS3, JSP, JAVAEE, JAVASCRIPT">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>Eliminar Materia</title>
        <script type="text/javascript" src="../ajax/grupos.js"></script>
        <script type="text/javascript" src="../js/validaciones.js"></script>
        <%@include file="../jsp/head.html"%>
    </head>
    <body>
        <%if (session.getAttribute("valido") == null) {
                response.sendRedirect("../index.jsp?error=Unauthorized Access");
            }
        %>
        <section id ="container">

            <!--header start-->
            <%@include file="../jsp/header.html"%>


            <!--header end-->

            <%@include file="../jsp/aside.html"%>  
            <!-- **********************************************************************************************************************************************************
                  MAIN CONTENT
                 *********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <!-- BASIC FORM ELELEMNTS -->
                    <div class="row mt">
                        <div class="col-lg-12"> 
                            <div class="form-panel">
                                <h4 class="mb"><i class="fa fa-angle-right"></i> Eliminar Grupo</h4>

                                <!--aqui nombro el formulario como consultarpersona-->
                                <form role="form" id="consultarGrupo" name="consultarGrupo"> <!-- Formulario de Busqueda de la persona -->
                                    <input type="hidden" id="opcion" name="opcion" value="3"/> <!-- input ocultos ayudan al controlador -->
                                    <input type="hidden" id="opcon" name="opcon" value="1"/>
                                    <div class="form-group">
                                        <label>Nombre del grupo</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre"  placeholder="Digite el nombre a buscar" required>
                                    </div>
                                    <button type="button" class="btn btn-success" onclick='grupoconsultado(document.getElementById("nombre").value, document.getElementById("opcion").value, document.getElementById("opcon").value);'>Consultar Registro</button>
                                </form>
                                <div id="listado">
                                </div>
                                <%if ((String.valueOf(request.getParameter("confir")).equalsIgnoreCase("eliminado"))) {%>
                                <script>
                                    alert("Eliminacion Realizada Correctamente", function () {
                                        location.href = "principal.jsp";
                                    });
                                </script>
                                <%
                                } else if ((String.valueOf(request.getParameter("confir")).equalsIgnoreCase("error"))) {
                                %>
                                <script>
                                    alert("A Ocurrido un error, elemento no existente.", function () {
                                        location.href = "principal.jsp";
                                    });
                                </script>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <!-- /content-panel -->
                    <!-- /col-lg-4 -->			
                    <!-- /row -->
                    <!--main content end-->
                    <!--footer start-->
                    <footer class="site-footer">
                        <%@include file="../jsp/foonter.html" %>
                    </footer>
                    <!--footer end-->
                </section>
                <script src="../assets/js/jquery.js"></script>
                <script src="../assets/js/jquery-1.8.3.min.js"></script>
                <script src="../assets/js/bootstrap.min.js"></script>
                <!-- js placed at the end of the document so the pages load faster -->
                <script class="include" type="text/javascript" src="../assets/js/jquery.dcjqaccordion.2.7.js"></script>
                <script src="../assets/js/jquery.scrollTo.min.js"></script>
                <script src="../assets/js/jquery.nicescroll.js" type="text/javascript"></script>
                <!--common script for all pages-->
                <script src="../assets/js/common-scripts.js"></script>
                <!--script for this page-->
                <script type="text/javascript" src="../assets/js/gritter/js/jquery.gritter.js"></script>
                <script type="text/javascript" src="../assets/js/gritter-conf.js"></script>
            </section> 
    </body>
</html>
