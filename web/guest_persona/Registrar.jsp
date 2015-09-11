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
        <!--es importante hacer el llamado al head ya que contiene la mayoria de las funciones del template-->
    </head>
    <body>
        <section id ="container">

            <!--header start-->
            <header class="header black-bg">
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
                </div>
                <!--logo start-->
                <a href="../jsp/Principal.jsp" class="logo"><b>Gestion de Colegio</b></a>
                <!--logo end-->
                <div class="top-menu">
                    <ul class="nav pull-right top-menu">
                        <li><a class="logout" href="../jsp/cerrarsesion.jsp">Cerrar Sesion</a></li>
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
                    <h3><i class="fa fa-angle-right"></i> Registo de Persona</h3>

                    <!-- BASIC FORM ELELEMNTS -->
                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h4 class="mb"><i class="fa fa-angle-right"></i> Registar Persona</h4>
                                <form class="form-horizontal style-form" role="form" method="post" enctype="application/x-www-form-urlencoded" name="registroPersona">
                                    <input type="hidden" id="opcion" name="opcion" value="3"/>             
                                    <center>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Dijite el Documento</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Tipo de Documento</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Primer Nombre</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Segundo Nombre</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Primer Apellido</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Segundo Apellido</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Genero</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Direccion</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Telefono</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Correo</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Estado Civil</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Fecha de Nacimiento</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div></center>
                                    <center> <button type="button" class="btn btn-primary btn-lg">Registrar</button></center>
                                </form>
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
