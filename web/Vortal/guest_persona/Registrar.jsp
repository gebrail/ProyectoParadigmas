<%-- 
    Document   : Registrar
    Created on : 10/09/2015, 10:26:33 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Personas</title>
        <%@include file="../jsp/head.html"%>
        <script src="../js/validaciones.js"></script>   
        <script type="text/javascript" src="../ajax/personas.js"></script>


        <!--fin de las mks para los calendarios-->
        <script>
            function cargarMetodo()
            {
                tipodocumentosxd();//este metodo me sirve para traerme los tipos de documentos en la base de datos
            }
        </script>
        <!--es importante hacer el llamado al head ya que contiene la mayoria de las funciones del template-->


    </head>
    <body onload="cargarMetodo();">
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
                                <h4 class="mb"><i class="fa fa-angle-right"></i> Registar Persona</h4>

                                <form class="form-horizontal style-form" role="form" method="post" id="registroPersona" enctype="application/x-www-form-urlencoded" name="registroPersona">
                                    <input type="hidden" id="opcion" name="opcion" value="2"/>          

                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Dijite el Documento</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="documento" name="documento" >
                                        </div>
                                    </div>
                                    <div class="form-group">

                                        <label class="col-sm-2 col-sm-2 control-label" >Tipo de documento</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="tipodedocumento" id="tipodedocumento">
                                            </select>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Primer Nombre</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="primernombre" name="primernombre" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Segundo Nombre</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="segundonombre" name="segundonombre">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Primer Apellido</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="primerapellido" name="primerapellido">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Segundo Apellido</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="segundoapellido" name="segundoapellido">
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Foto</label>
                                        <div class="col-sm-10">
                                            <input type="file" accept="image/*" class="form-control" id="files" name="files">
                                            <center>   <output id="list"></output></center>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Genero</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" id="Genero" name="Genero">
                                                <option value="Masculino">Masculino</option>
                                                <option value="Femenino">Femenino</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Direccion</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="direccion" name="direccion" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Telefono</label>
                                        <div class="col-sm-10">
                                            <input type="text"  class="form-control" id="telefono" name="telefono">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Correo</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control " id="correo" name="correo" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Estado Civil</label>
                                        <div class="col-sm-10">
                                            <select class="form-control" id="estadocivil" name="estadocivil">
                                                <option value="Soltero">Soltero</option>
                                                <option value="Casado">Casado</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Fecha de Nacimiento</label>
                                        <div class="col-sm-10">
                                            <input  type="date" placeholder="desplegar el calendario"  class="form-control" id="fechanacimiento" name="fechanacimiento">
                                        </div>
                                    </div>
                                    <!-- Load jQuery and bootstrap datepicker scripts -->
                                    <script src="../calendario/js/jquery-1.9.1.min.js"></script>
                                    <script src="../calendario/js/bootstrap-datepicker.js"></script>
                                    <center> <button type="button" class="btn btn-primary btn-lg" onclick="validarRegistroPersona();" >Registrar</button></center>

                                </form>
                                <%if (String.valueOf(request.getParameter("confir")).equalsIgnoreCase("registrado")) {%>


                                <script>
                                    alert("La persona ha sido registrada correctamente");
                                </script>



                                <%
                                } else if (String.valueOf(request.getParameter("confir")).equalsIgnoreCase("error")) {
                                %>
                                <script>
                                    alert("Hubo un error pailas mire aver que hizo mal");
                                </script>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>


                    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

                    <script>

                                    // fragmento jquery para funcionamiento del script

                                    $(document).ready(function () {

                                        // Funcion para mostrar u ocultar el cuadro

                                        function showDialog(id) {

                                            // encontramos el dialogo y lo ocultamos

                                            var dialog = $('#' + id),
                                                    card = dialog.find('.dialog-card');

                                            dialog.fadeIn();

                                            // centramos en la pantalla

                                            card.css({
                                                'margin-top': -card.outerHeight() / 2
                                            });

                                        }

                                        function hideAllDialogs() {

                                            // ocultamos los cuadros visibles
                                            $('.dialog-overlay').fadeOut();

                                        }


                                        $('.dialog-confirm-button').on('click', function () {

                                            // ocultamos el cuadro cuando es confirmado
                                            hideAllDialogs();

                                        });

                                        $('.dialog-overlay').on('click', function (e) {

                                            if (e.target == this) {
                                                // ocultamos el cuadro si es clickeado directamente
                                                hideAllDialogs();
                                            }


                                        });

                                        $(document).keyup(function (e) {

                                            // ocultamos el cuadro cuando presionamos la tecla escape

                                            if (e.keyCode == 27) {
                                                hideAllDialogs();
                                            }

                                        });


                                        // verificamos que cuadro de dialogo se esta presionando,
                                        // y mstramos el cuadro de dialogo correcto

                                        $('.dialog-show-button').on('click', function () {

                                            // toma el contenido de  "data-show-dialog" attribute
                                            var toShow = $(this).data('show-dialog');

                                            showDialog(toShow);
                                        });
                                    });

                    </script>

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

                <script>
                                    function archivo(evt) {
                                        var files = evt.target.files; // FileList object
// Obtenemos la imagen del campo "file".
                                        for (var i = 0, f; f = files[i]; i++) {
//Solo admitimos imágenes.
                                            if (!f.type.match('image.*')) {
                                                continue;
                                            }
                                            var reader = new FileReader();
                                            reader.onload = (function (theFile) {
                                                return function (e) {
                                                    // Insertamos la imagen
                                                    document.getElementById("list").innerHTML = ['<img class="img-circle" width="150" src="', e.target.result, '" title="', escape(theFile.name), '"/>'].join('');
                                                };
                                            })(f);
                                            reader.readAsDataURL(f);
                                        }
                                    }
                                    document.getElementById('files').addEventListener('change', archivo, false);
                </script>
            </section> 
    </body>
</html>
