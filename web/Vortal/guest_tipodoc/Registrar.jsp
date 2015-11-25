<%-- 
    Document   : Modificar
    Created on : 10/09/2015, 10:26:24 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar tipo de documentos</title>

        <script src="../js/validaciones.js"></script>    

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
                                <h4 class="mb"><i class="fa fa-angle-right"></i> Registrar Tipo de Documento </h4>

                                <form role="form" method="post" id="registroTipoDocumento" enctype="application/x-www-form-urlencoded" name="registroTipoDocumento">
                                    <input type="hidden" id="opcion" name="opcion" value="1"/>
                                    <div class="form-group">
                                        <label>Id Del tipo de documento</label>
                                        <input type="text" class="form-control" id="idtipodoc" name="idtipodoc" placeholder="Digita el id del Tipo de Documento" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Digita el Nombre Tipo de Documento" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Desripción</label>
                                        <textarea class="form-control" rows="4" id="descripcion" name="descripcion" placeholder="Digita la descripcion del Tipo de Documento" required></textarea>
                                    </div>
                                    <button type="button" class="btn btn-success" onclick='validarRegistroTido(document.getElementById("idtipodoc").value, document.getElementById("nombre").value, document.getElementById("descripcion").value);'>Registrar</button>
                                </form>
                                <%if (String.valueOf(request.getParameter("confir")).equalsIgnoreCase("registrado")) {%>
                                <script>
                                    alert("Registro del Tipo de Documento realizado correctamente.", function () {
                                        location.href = "principal.jsp";
                                    });
                                </script>
                                <%
                                } else if (String.valueOf(request.getParameter("confir")).equalsIgnoreCase("error")) {
                                %>
                                <script>
                                    .alert("A ocurrido un error, comprueba los datos.", function () {
                                        location.href = "registrar.jsp";
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
