<%-- 
    Document   : Listar
    Created on : 10/09/2015, 10:26:53 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Listado De Profesores</title>
        <script src="../ajax/consultasnormales.js"></script>
        <%@include file="../jsp/head.html"%>


    </head>





    <body onload="listarprofesores();">
        
        
        <%if (session.getAttribute("valido") == null) {
                response.sendRedirect("../index.jsp?error=Acceso Denegado");
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

                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="content-panel">
                                <center>                                <h4><i class="fa fa-angle-right"></i> Listado De Profesores</h4
                                    <div class="showback">


                                </center>
                                <section id="unseen">
                                    <table class="table table-bordered table-striped table-condensed">
                                        <thead>
                                            <tr>
                                                <th>
                                                    ID DE LA PERSONA
                                                </th>
                                                <th>
                                                    NOMBRE
                                                </th>

                                                <th>
                                                    APELLIDO
                                                </th>

                                                <th>
                                                    ROL 
                                                </th>

                                                <th>
                                                    NOMBRE DEL GRUPO A CARGO
                                                </th>
                                                <th>
                                                    ID DEL GRUPO A CARGO
                                                </th>


                                            </tr>
                                        </thead>
                                        <tbody id="listadodeprofesoresxd">
                                        </tbody>
                                    </table

                            </div>

                            </section>
                        </div><!-- /content-panel -->
                    </div><!-- /col-lg-4 -->			
                    </div><!-- /row -->
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
