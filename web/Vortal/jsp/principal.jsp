<%-- 
    Document   : principal
    Created on : 10/09/2015, 07:18:43 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Principal</title>
        <%@include file="head.html"%>

        <link href="http://vjs.zencdn.net/4.12/video-js.css" rel="stylesheet">
        <script src="http://vjs.zencdn.net/4.12/video.js"></script>
        <!--es importante hacer el llamado al head ya que contiene la mayoria de las funciones del template-->
        <style type="text/css">
            .vjs-default-skin { color: #ffffff; }
            .vjs-default-skin .vjs-play-progress,
            .vjs-default-skin .vjs-volume-level { background-color: #de4a4a }
            .vjs-default-skin .vjs-control-bar,
            .vjs-default-skin .vjs-big-play-button { background: rgba(0,0,0,0.07) }
            .vjs-default-skin .vjs-slider { background: rgba(0,0,0,0.023333333333333334) }
        </style>
        <link href="../assets/video-js/video-quality-selector.css" rel="stylesheet" type="text/css" />
        <script src="../assets/video-js/video-quality-selector.js"  type="text/javascript" ></script>


    </head>
    <body>
        <%if (session.getAttribute("valido") == null) {
                response.sendRedirect("../Vortal/index.jsp?error=Acceso Denegado");
            }
        %>
        <section id="container">

            <%@include file="header.html"%>




            <%@include file="aside.html"%>  

            <!-- **********************************************************************************************************************************************************
                  MAIN CONTENT
                 *********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section id="main-content"> 

                <section class="wrapper">
                    <div class="row">
                        <div class="col-lg-9 main-chart">
                            <div class="page-header">
                                <h1>Bienvenido</h1>
                            </div>
                            <p class="lead">
                                La siguiente aplicación se creo con el fin de facilitar, el trabajo de los profesores a la hora de mostrar y agregar notas.
                                Se espera que la interacción con la aplicación sea de su total agrado.
                            </p>
                            <center>
                                <img src="../img/leon_up.png" >
                            </center>


                        </div>



                        <%@include file="derecha.html"%>






                    </div>


                </section>

            </section>
            <!--main content end-->
            <!--footer start-->
            <footer class="site-footer">
                <%@include file="foonter.html" %>
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
        <script src="../assets/js/jquery.sparkline.js"></script>


        <!--common script for all pages-->
        <script src="../assets/js/common-scripts.js"></script>

        <script type="text/javascript" src="../assets/js/gritter/js/jquery.gritter.js"></script>
        <script type="text/javascript" src="../assets/js/gritter-conf.js"></script>

        <!--script for this page-->
        <script src="../assets/js/sparkline-chart.js"></script>    
        <script src="../assets/js/zabuto_calendar.js"></script>	

        <script type="text/javascript">
            $(document).ready(function () {
                var unique_id = $.gritter.add({
                    // (string | mandatory) the heading of the notification
                    title: 'Bienvenido!',
                    // (string | mandatory) the text inside the notification
                    text: 'En Esta Pagina Encontraras todo lo relacionado con el colegio',
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
