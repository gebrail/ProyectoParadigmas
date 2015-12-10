<%-- 
    Document   : AsignarCurso
    Created on : 16-nov-2015, 0:41:37
    Author     : wilso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignar Materias Al Estudiante</title>
        <%@include file="../jsp/head.html"%>
        <script src="../js/validaciones.js"></script>   
        <script type="text/javascript" src="../ajax/consultasnormales.js"></script>
        <!--fin de las mks para los calendarios-->

        <!--es importante hacer el llamado al head ya que contiene la mayoria de las funciones del template-->
        <script>
            function cargarAjax()
            {
                listado_documentosEstudiantes();//este metodo me sirve para traerme todos las ids de las personas
                listadomaterias();// este metodo me trae todos los roles creados en la base de datos
            }
        </script>

    </head>
    <body onload="cargarAjax();">
        <%            if (session.getAttribute("valido") == null) {
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
                                <h4 class="mb"><i class="fa fa-angle-right"></i>Asignar Grupo al Estudiante</h4>

                                <form class="form-horizontal style-form" role="form" method="post" id="registroCursopersona" enctype="application/x-www-form-urlencoded" name="registroCursopersona">
                                    <input type="hidden" id="opcion" name="opcion" value="1"/>          


                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label" >Documentos de Estudiantes</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="docest" id="docest">
                                            </select>
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Seleecione La Materia que desea Asignarle</label>

                                        <div class="col-sm-10">
                                            <select class="form-control" id="materiar" name="materiar" onchange="consultarGrupos(document.getElementById('materiar').value,document.getElementById('docest').value)">

                                            </select>
                                        </div>
                                    </div>

                                    <div id="grupos">
                                    </div>


                                    <!-- Load jQuery and bootstrap datepicker scripts -->
                                    <script src="../calendario/js/jquery-1.9.1.min.js"></script>
                                    <script src="../calendario/js/bootstrap-datepicker.js"></script>


                                </form>
                                <%if (String.valueOf(request.getParameter("confir")).equalsIgnoreCase("registrado")) {%>
                                <script>
                                    alert("La materia ha sido asignada correctamente");
                                </script>
                                <%
                                } else if (String.valueOf(request.getParameter("confir")).equalsIgnoreCase("error")) {
                                %>
                                <script>
                                    alert("Hubo un error ");
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
