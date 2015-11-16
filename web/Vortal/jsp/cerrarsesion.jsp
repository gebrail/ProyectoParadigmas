<%-- 
    Document   : cerrarsesion
    Created on : 13/07/2015, 04:33:34 PM
    Author     : Wilson
--%>

<%
    session.invalidate(); 
    response.sendRedirect("../index.jsp");
%>
