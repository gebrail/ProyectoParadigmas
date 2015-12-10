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
        <script type="text/javascript" src="../ajax/estudiantes.js"></script>
        <!--fin de las mks para los calendarios-->

        <!--es importante hacer el llamado al head ya que contiene la mayoria de las funciones del template-->
        <script>
            function cargarAjax()
            {
                listado_documentosEstudiantes();//este metodo me sirve para traerme todos las ids de las personas

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
                                <h4 class="mb"><i class="fa fa-angle-right"></i>Ver Materias A Estudiantes</h4>

                                <form class="form-horizontal style-form" role="form" method="post" id="registroCursopersona" enctype="application/x-www-form-urlencoded" name="registroCursopersona">
                                    <input type="hidden" id="opcion" name="opcion" value="1"/>          


                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label" >Documentos de Estudiantes</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="docest" id="docest" onchange="consultarGruposdelestudiante(document.getElementById('docest').value)">
                                            </select>
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Seleecione La Materia que desea Modificarle</label>

                                        <div class="col-sm-10">
                                            <select class="form-control" id="mat" name="mat" onchange="consultarGruposdelestudiantexd(document.getElementById('mat').value, document.getElementById('docest').value)">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" onclick='consultarGruposdelestudiantexd(document.getElementById("mat").value, document.getElementById("docest").value);'>Mostrar</button>
                                    </div>


                                    <div id="gr">
                                    </div>


                                </form>



                                <div class="modal fade bs-example-modal-lg" id="modificar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">Modificar Materia asignada al estudiante</h4>
                                            </div>
                                            <div class="modal-body" id="modificarForm">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>

                                                <button type="button" class="btn btn-primary" onclick='confirmarModificarAsignacion(document.getElementById("idgru").value,document.getElementById("grupito").value);'>Guardar Cambios</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal-dialog -->
                                </div>






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
