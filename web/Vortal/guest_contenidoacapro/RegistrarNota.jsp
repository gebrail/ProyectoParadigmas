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
        <title>Registro de Notas</title>
        <%@include file="../jsp/head.html"%>
        <script src="../js/validaciones.js"></script>   
        <script type="text/javascript" src="../ajax/consultasnormales.js"></script>
        <script type="text/javascript" src="../ajax/estudiantes.js"></script>
        <!--fin de las mks para los calendarios-->

        <!--es importante hacer el llamado al head ya que contiene la mayoria de las funciones del template
        <script>
            function cargarAjax()
            {
                //este metodo me sirve para traerme todos las ids de las personas

            }
        </script>-->

    </head>
    <body>
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
                                <h4 class="mb"><i class="fa fa-angle-right"></i>Registro de Notas</h4>

                                <form role="form" id="consultarMateria" name="consultarMateria"> <!-- Formulario de Busqueda de la persona -->

                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label" >Materias A Su Cargo</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="losdocum" id="losdocum" onchange="consultarGrupospr(document.getElementById('losdocum').value, document.getElementById('eldoctxd').value)">
                                            </select>
                                        </div>
                                    </div>



                                    <center> <button type="button" class="btn btn-primary" onclick='listado_matpr(document.getElementById("eldoctxd").value);'>Mostrar Materias</button>
                                     <button type="button" class="btn btn-primary" onclick='consultarGrupospr(document.getElementById("losdocum").value, document.getElementById("eldoctxd").value);'>Mostrar Grupos</button></center>
                                </form>

                                <div id="grupos">
                                </div>
                                <div id="losestugu">
                                </div>


                                <div class="modal fade bs-example-modal-lg" id="modificar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">Modificar Notas Al Estudiante</h4>
                                            </div>
                                            <div class="modal-body" id="modificarForm">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>

                                                <button type="button" class="btn btn-primary" onclick='confirmarModificarNotas(document.getElementById("notaa").value, document.getElementById("notab").value, document.getElementById("notac").value);'>Guardar Cambios</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal-dialog -->
                                </div>
                                <%if ((String.valueOf(request.getParameter("confir")).equalsIgnoreCase("modificado"))) {%>
                                <script>
                                    alert("Modificacion Realizada Correctamente");

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


            <script type="text/javascript">
                                        $(document).ready(function () {
                                            var unique_id = $.gritter.add({
                                                // (string | mandatory) the heading of the notification
                                                title: 'PRECIONE EL BOTON MOSTRAR!',
                                                // (string | mandatory) the text inside the notification
                                                text: 'PARA VER LAS MATERIAS QUE TIENE A SU CARGO',
                                                // (string | optional) the image to display on the left

                                                // (bool | optional) if you want it to fade out on its own or just sit there
                                                sticky: true,
                                                // (int | optional) the time you want it to be alive for before fading out
                                                time: '',
                                                // (string | optional) the class name you want to apply to that specific message
                                                class_name: 'my-sticky-class'
                                            });
                                            return false;
                                        });
            </script>

            <script type="application/javascript">
                $(document).ready(function () {
                $("#date-popover").popover({html: true, trigger: "manual"});
                $("#date-popover").hide();
                $("#date-popover").click(function (e) {
                $(this).hide();
                });

                $("#my-calendar").zabuto_calendar({
                action: function () {
                return myDateFunction(this.id, false);
                },
                action_nav: function () {
                return myNavFunction(this.id);
                },
                ajax: {
                url: "show_data.php?action=1",
                modal: true
                },
                legend: [
                {type: "text", label: "Special event", badge: "00"},
                {type: "block", label: "Regular event", }
                ]
                });
                });


                function myNavFunction(id) {
                $("#date-popover").hide();
                var nav = $("#" + id).data("navigation");
                var to = $("#" + id).data("to");
                console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
                }
            </script>
    </body>
</html>
