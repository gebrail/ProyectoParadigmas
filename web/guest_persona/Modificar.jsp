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
        <title>Modificar</title>
        <script type="text/javascript" src="../ajax/personas.js"></script>
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
            <header class="header black-bg">
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
                </div>
                <!--logo start-->
                <a href="../jsp/principal.jsp" class="logo"><b>Gestion de Colegio</b></a>
                <!--logo end-->
                <div class="top-menu">
                    <ul class="nav pull-right top-menu">
                        <li><a class="logout" href="../jsp/cerrarsesion.jsp">logout</a></li>
                    </ul>
                </div>
            </header>

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
                                <h4 class="mb"><i class="fa fa-angle-right"></i> Modificar Persona</h4>

                                <form role="form" id="consultarPersona" name="consultarPersona"> <!-- Formulario de Busqueda de la persona -->
                                    <input type="hidden" id="opcion" name="opcion" value="3"/> <!-- input ocultos ayudan al controlador -->
                                    <input type="hidden" id="opcon" name="opcon" value="2"/>
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre"  placeholder="Digite el apellido a buscar" required>
                                    </div>
                                    <button type="button" class="btn btn-success" onclick='personaconsultada(document.getElementById("nombre").value, document.getElementById("opcion").value, document.getElementById("opcon").value);'>Consultar Registro</button>
                                </form>
                                <div id="listado">
                                </div>
                                <div class="modal fade bs-example-modal-lg" id="modificar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">Modificar Persona</h4>
                                            </div>
                                            <div class="modal-body" id="modificarForm">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                                
                                                <button type="button" class="btn btn-primary" onclick='confirmarModificarPersona();'>Guardar Cambios</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal-dialog -->
                                </div><!-- /.modal -->
                                <%if ((String.valueOf(request.getParameter("confir")).equalsIgnoreCase("modificado"))) {%>
                                <script>
                                    alert("Modificacion Realizada Correctamente", function () {
                                        location.href = "principal.jsp";
                                    });
                                </script>
                                <%
                                } else if ((String.valueOf(request.getParameter("confir")).equalsIgnoreCase("error"))) {
                                %>
                                <script>
                                    alert("A Ocurrido un error, compruebe los datos.", function () {
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
