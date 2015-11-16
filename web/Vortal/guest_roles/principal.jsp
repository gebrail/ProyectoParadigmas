<%-- 
    Document   : principal
    Created on : 10/09/2015, 10:28:15 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Principal De Roles</title>
        <%@include file="../jsp/head.html"%>
    </head>
    <body>
        <%if (session.getAttribute("valido") == null) {
                response.sendRedirect("../index.jsp?error=Acceso Denegado");
            }
        %>
        <section id="container">

            <!--header start-->
            <%@include file="../jsp/header.html"%>


            <!--header end-->

            <%@include file="../jsp/aside.html"%>  
            <!-- **********************************************************************************************************************************************************
                  MAIN CONTENT
                 *********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section id="main-content"> 

                <section class="wrapper site-min-height">
                    <h3><i class="fa fa-angle-right"></i> Gestion De Roles</h3>
                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="row">
                                <!-- TWITTER PANEL -->
                                <div class="col-lg-4 col-md-4 col-sm-4 mb">
                                    <!-- WHITE PANEL - TOP USER -->
                                    <div class="white-panel pn">
                                        <div class="white-header">
                                            <h5>Registrar Rol</h5>
                                        </div>
                                        <p><img src="../assets/img/crear.png" class="img-circle" width="100"></p>
                                        <div class="row">
                                            <a href="Registrar.jsp"    <button type="button" class="btn btn-theme">Regitrar Rol</button> </a>



                                        </div>
                                    </div>
                                </div><!-- /col-md-4 -->

                                <div class="col-lg-4 col-md-4 col-sm-4 mb">
                                    <!-- WHITE PANEL - TOP USER -->
                                    <div class="white-panel pn">
                                        <div class="white-header">
                                            <h5>Eliminar Rol</h5>
                                        </div>
                                        <p><img src="../assets/img/borrar.png" class="img-circle" width="100"></p>
                                        <div class="row">
                                            <a href="Eliminar.jsp"    <button type="button" class="btn btn-danger">Eliminar Roles</button> </a>

                                        </div>
                                    </div>
                                </div><!-- /col-md-4 -->

                                <div class="col-lg-4 col-md-4 col-sm-4 mb">
                                    <!-- WHITE PANEL - TOP USER -->
                                    <div class="white-panel pn">
                                        <div class="white-header">
                                            <h5>Modificar Rol</h5>
                                        </div>
                                        <p><img src="../assets/img/modificar.png" class="img-circle" width="100"></p>
                                        <div class="row">
                                            <a href="Modificar.jsp"    <button type="button" class="btn btn-theme02">Modificar Roles</button> </a>


                                        </div>
                                    </div>
                                </div><!-- /col-md-4 -->
                            </div>
                            <div class="row">

                           
                                    <div class="col-lg-4 col-md-4 col-sm-4 mb">
                                        <!-- WHITE PANEL - TOP USER -->
                                        <div class="white-panel pn">
                                                
                                            <div class="white-header">
                                                <h5>Listar Todos Los Roles</h5>
                                            </div>
                                            <center>
                                                <p><img src="../assets/img/report-256.png" class="img-circle" width="100"></p></center>
                                            <div class="row">
                                                <a href="Listar.jsp"    <button type="button" class="btn btn-info">Listar Roles</button> </a>


                                            </div>

                                         
                                        </div>
                                    </div>


                                    <div class="col-lg-4 col-md-4 col-sm-4 mb">
                                        <!-- WHITE PANEL - TOP USER -->
                                        <div class="white-panel pn">
                                                
                                            <div class="white-header">
                                                <h5>Asignacion de Funcionalidades</h5>
                                            </div>
                                            <center>
                                                <p><img src="../assets/img/Admin_Roles-128.png" class="img-circle" width="100"></p></center>
                                            <div class="row">
                                                <a href="Asignarfuncionalidad.jsp"    <button type="button" class="btn btn-theme03">Asignar Funcionalidad Roles</button> </a>


                                            </div>

                                         
                                        </div>
                                    </div>

                            </div>

                        </div>
                    </div>

                </section>

            </section>
            <!--main content end-->
            <!--footer start-->
            <footer class="site-footer">
                <%@include file="../jsp/foonter.html" %>
            </footer>
            <!--footer end-->
        </section>

        <!-- js placed at the end of the document so the pages load faster -->
        <script src="../assets/js/jquery.js"></script>
        <script src="../assets/js/jquery-1.8.3.min.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="../assets/js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="../assets/js/jquery.scrollTo.min.js"></script>
        <script src="../assets/js/jquery.nicescroll.js" type="text/javascript"></script>


        <!--common script for all pages-->
        <script src="../assets/js/common-scripts.js"></script>

        <!--script for this page-->
        <script type="text/javascript" src="../assets/js/gritter/js/jquery.gritter.js"></script>
        <script type="text/javascript" src="../assets/js/gritter-conf.js"></script>





    </body>
</html>
