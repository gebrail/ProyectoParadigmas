/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.materiaDAO;
import DAO.materiaestudiantesDAO;
import DAO.personasDAO;
import VO.grupo;
import VO.materiaVO;
import VO.materiaestudiantesVO;
import VO.personasVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wilson
 */
public class controllerEstudiante extends HttpServlet {

    materiaDAO lamateriaxd = new materiaDAO();
    personasDAO laperson = new personasDAO();
    materiaestudiantesDAO estudiantesDAO = new materiaestudiantesDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            int opcion = 0;
            if (request.getParameter("opcion") != null) {
                opcion = Integer.parseInt(request.getParameter("opcion"));
            }
            switch (opcion) {
                case 0:
                    out.println(listadomateria());
                    break;
                case 1:

                    Long idpersona = Long.parseLong(request.getParameter("docest"));
                    Long idgrupo = Long.parseLong(request.getParameter("grupito"));
                    response.sendRedirect("Vortal/guest_estudiantes/AsignarMaterias.jsp?confir=" + registargrupoestudiante(idgrupo, idpersona));

                    break;
                case 2://mostrar cuentas por orden acendente del username Listar
                    //out.println(listadodecuentas());
                    break;
                case 3:
                    int opcionconsulta = Integer.parseInt(request.getParameter("opcon"));
                    String materiaBuscar = (String) request.getParameter("nombre");
                    out.println(consultaMateria(materiaBuscar, opcionconsulta));

                    break;
                case 4:
                    int opcionmodificar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionmodificar == 1) {
                        long iddelgr = Long.parseLong(request.getParameter("elgrupito"));
                        long idperso = Long.parseLong(request.getParameter("eldocument"));
                        long lamat = Long.parseLong(request.getParameter("lamat"));
                        materiaestudiantesVO recVO = new materiaestudiantesVO();
                        recVO = lamateriaxd.consultaIndividualMateriaestudiantes(iddelgr, idperso);
                        session.setAttribute("matgrChange", recVO);
                        out.println(materiaAModificar(recVO, lamat));

                    }
                    if (opcionmodificar == 2) {
                        materiaestudiantesVO recVO = new materiaestudiantesVO();
                        recVO = (materiaestudiantesVO) session.getAttribute("matgrChange");
                        recVO.setId_grupo(Long.parseLong(request.getParameter("idgru")));
                        long idmatp = Long.parseLong(request.getParameter("grupito"));
                        boolean respuestaModificar = (boolean) estudiantesDAO.modificarGrupoAsignado(recVO, idmatp);
                        if (respuestaModificar) {
                            session.removeAttribute("matgrChange");
                            response.sendRedirect("Vortal/guest_estudiantes/ModificarMateriaAsignada.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_estudiantes/ModificarMateriaAsignada.jsp?confir=error");
                        }
                    }
                    break;
                case 5://Eliminar
                    long elim = Long.parseLong(request.getParameter("eliminar"));
                    boolean respuestaEliminar = eliminarMateria(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_materias/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_materias/Eliminar.jsp?confir=error");
                    }
                    break;

                case 6:

                    out.println(materias());
                    break;

                case 7:
                    long materia = Long.parseLong(request.getParameter("materiar"));
                    long idper = Long.parseLong(request.getParameter("docest"));
                    out.println(consultaGrupos(materia, idper));

                    break;
                case 8:
                    long idperson = Long.parseLong(request.getParameter("docest"));
                    out.println(materiasqueven(idperson));

                    break;

                case 9:
                    long lm = Long.parseLong(request.getParameter("mat"));
                    long iper = Long.parseLong(request.getParameter("docest"));
                    out.println(consultaGruposdelestudiante(lm, iper));

                    break;
                case 10:
                    long idpr = Long.parseLong(request.getParameter("eldoctxd"));
                    out.println(matquedictan(idpr));
                    break;

                case 11:
                    long materia2 = Long.parseLong(request.getParameter("materiar"));
                    long idper2 = Long.parseLong(request.getParameter("docest"));
                    out.println(consultaGrupos2(materia2, idper2));
                    break;
                case 12:
                    long idgrup = Long.parseLong(request.getParameter("grupito"));
                    out.println(estudiantesgruposqueven(idgrup));
                    break;
                case 13:
                    int opcioninsertar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcioninsertar == 1) {
                        long idgrp = Long.parseLong(request.getParameter("idgrxd"));
                        long idpers = Long.parseLong(request.getParameter("idpersxd"));
                        materiaestudiantesVO recVO = new materiaestudiantesVO();
                        recVO = estudiantesDAO.consultaIndividualNE(idgrp, idpers);
                        session.setAttribute("insertarChange", recVO);
                        out.println(estudianteAInsertarN(recVO));

                    }
                    if (opcioninsertar == 2) {
                        materiaestudiantesVO recVO = new materiaestudiantesVO();
                        recVO = (materiaestudiantesVO) session.getAttribute("insertarChange");
                        recVO.setNota1(Double.parseDouble(request.getParameter("notaa")));
                        recVO.setNota2(Double.parseDouble(request.getParameter("notab")));
                        recVO.setNota3(Double.parseDouble(request.getParameter("notac")));
                        boolean respuestaModificarxd = (boolean) estudiantesDAO.InsertarNotas(recVO);
                        if (respuestaModificarxd) {
                            session.removeAttribute("insertarChange");
                            response.sendRedirect("Vortal/guest_contenidoacapro/RegistrarNota.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_contenidoacapro/RegistrarNota.jsp?confir=error");
                        }
                    }
                    break;
                case 14:
                    long idpr2 = Long.parseLong(request.getParameter("eldoctxd"));
                    out.println(matquevenest(idpr2));
                    break;
                case 15:
                    long materia3 = Long.parseLong(request.getParameter("losdocum"));
                    long idper3 = Long.parseLong(request.getParameter("eldoctxd"));
                    out.println(consultaGruposdelestudiante2(materia3, idper3));

                    break;

                case 16:
                    int opcionver = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionver == 1) {
                        long idgrp = Long.parseLong(request.getParameter("idgrxd"));
                        long idpers = Long.parseLong(request.getParameter("idpersxd"));
                        materiaestudiantesVO recVO = new materiaestudiantesVO();
                        recVO = lamateriaxd.consultaIndividualMateriaestudiantes(idgrp, idpers);
                        session.setAttribute("estChange", recVO);
                        out.println(VerNotas(recVO));

                    }
                
                    break;
            }
        } finally {
            out.close();
        }
    }

    private String matquedictan(long idproo) throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = estudiantesDAO.consultarmateriaasignadaspr(idproo);
        if (!datos.isEmpty()) {
            materiaVO tidoVO = new materiaVO();
            for (Object dato : datos) {
                tidoVO = (materiaVO) dato;
                opcion += "<option value=\"" + tidoVO.getcodigo_materia() + "\">" + tidoVO.getnombre_materia() + "</option>";

            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
    }

    private String matquevenest(long idproo) throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = estudiantesDAO.consultarmateriaasignadaset(idproo);
        if (!datos.isEmpty()) {
            materiaVO tidoVO = new materiaVO();
            for (Object dato : datos) {
                tidoVO = (materiaVO) dato;
                opcion += "<option value=\"" + tidoVO.getcodigo_materia() + "\">" + tidoVO.getnombre_materia() + "</option>";

            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
    }

    private String materias() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = estudiantesDAO.listarmaterias();
        if (!datos.isEmpty()) {
            materiaVO lamateriaVO = new materiaVO();
            for (Object dato : datos) {
                lamateriaVO = (materiaVO) dato;
                opcion += "<option value=\"" + lamateriaVO.getcodigo_materia() + "\">" + lamateriaVO.getnombre_materia() + "</option>";
            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
    }

    private boolean eliminarMateria(long idmateria) {
        return lamateriaxd.eliminarMateria(idmateria);

    }

    private String registargrupoestudiante(long idgrupo, long idpersona) throws SQLException {
        boolean registro = false;
        String message = "";
        registro = (boolean) estudiantesDAO.AsignarMateriaGrupo(idgrupo, idpersona);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";

        }
        return message;
    }

    private String listadomateria() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        String x = "";
        datos = lamateriaxd.listarmaterias();
        if (!datos.isEmpty()) {
            materiaVO curVO = new materiaVO();
            for (Object dato : datos) {
                curVO = (materiaVO) dato;
                listado += "<tr>"
                        + "<td>" + curVO.getcodigo_materia() + "</td>"
                        + "<td>" + curVO.getnombre_materia() + "</td>"
                        + "<td>" + curVO.getdescripcion() + "</td>"
                        + "<form role=\"form\" method=\"post\" id=\"eliminarPersona\" name=\"eliminarPersona\" enctype=\"application/x-www-form-urlencoded\">"
                        + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"3\"/>" + "<td>" + "<button class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i></button>" + "<button class=\"btn btn-danger btn-xs\" id=\"opcion\" name=\"opcion\" value=\"3\"><i class=\"fa fa-trash-o\" onclick=\"confirmarEliminarPersona();\"></i></button>"
                        + "</td>"
                        + "</tr>";
            }
        } else {
            listado += "<tr>"
                    + "<td colspan=\"3\">" + "No se han encontrado registros." + "</td>"
                    + "</tr>";
        }

        return listado;
    }

    private String consultaMateria(String nombre, int opcionconsulta) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = estudiantesDAO.consultarMateriaPersona(nombre);
        if (opcionconsulta == 1) {
            consulta += "<form role=\"form\" method=\"post\" id=\"eliminarMateriapersona\" name=\"eliminarMateriapersona\" enctype=\"application/x-www-form-urlencoded\">"
                    + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"5\"/>";
        } else if (opcionconsulta == 2) {
            consulta += "<form role=\"form\" id=\"modificarMateriapersona\" name=\"modificarMateriapersona\">";
        }
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "codigo del Estudiante"
                + "</th>"
                + "<th>"
                + "PRIMER NOMBRE "
                + "</th>"
                + "<th>"
                + "PRIMER APELLIDO"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                personasVO rlVO = new personasVO();
                rlVO = (personasVO) datos.get(i);
                consulta += "<tr>";
                if (opcionconsulta == 1) {
                    consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + rlVO.getid_persona() + "\">" + "</td>";
                } else if (opcionconsulta == 2) {
                    consulta += "<td><input id=\"idpersona\" type=\"radio\" name=\"idpersona\" value=\"" + rlVO.getid_persona() + "\" onchange='javascript: materiapersonaAModificar(" + rlVO.getid_persona() + ",4,1);'>" + "</td>";
                }
                consulta += "<td>" + rlVO.getid_persona() + "</td>"
                        + "<td>" + rlVO.getprimernombre_persona() + "</td>"
                        + "<td>" + rlVO.getsegundoapellido_persona() + "</td>"
                        + "</tr>";
            }
        } else {
            consulta += "<tr>"
                    + "<td colspan=\"3\">" + "No se han encontrado registros." + "</td>"
                    + "</tr>";
        }
        consulta += "</tbody>"
                + "</table>"
                + "</div>"
                + "</form>";
        if (opcionconsulta == 1) {
            consulta += "<button type=\"button\" class=\"btn btn-danger\" onclick=\"confirmarEliminarMateriapersona();\">Eliminar Materia</button>";
        } else if (opcionconsulta == 2) {
            consulta += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionMateriaModificarpersona(document.getElementById(\"idpersona\").value, document.getElementById(\"opcion\").value, 8);\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private String materiaAModificar(materiaestudiantesVO rlVO, long idmat) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"materiagrModificar\" enctype=\"application/x-www-form-urlencoded\" name=\"materiagrModificar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>codigo del estudiante</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idcursoxd\" name=\"idmateriaxd\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getId_persona() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Grupo</label>"
                + " <select class=\"form-control\" name=\"idgru\" id=\"idgru\"> ";
        LinkedList xd = new LinkedList();
        xd = lamateriaxd.consultargruposdelamateria(idmat);
        if (!xd.isEmpty()) {
            grupo persVO = new grupo();
            for (Object xd1 : xd) {
                persVO = (grupo) xd1;
                if (persVO.getId_grupo() == rlVO.getId_grupo()) {
                    formulario += "<option value=\"" + persVO.getId_grupo() + "\" selected>" + persVO.getNombre_grupo() + "</option>";
                } else {
                    formulario += "<option value=\"" + persVO.getId_grupo() + "\">" + persVO.getNombre_grupo() + "</option>";
                }
            }
        }
        formulario += "</select>"
                + "</div>"
                + "</form>";
        return formulario;
    }

    private String consultaGrupos(Long materia, long person) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = lamateriaxd.consultargruposdelamateria(materia);
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "Nombre del grupo"
                + "</th>"
                + "<th>"
                + "Id del Profesor A Cargo"
                + "</th>"
                + "<th>"
                + "Descripcion del grupo "
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {

            consulta += "<center><div class=\"alert alert-info\"><b>Atencion!</b>  Seleccione El Grupo de la Materia que desea registrar al Estudiante</div></center>";

            for (Object dato : datos) {
                grupo grupoVO = new grupo();
                grupoVO = (grupo) dato;

                LinkedList xd2 = new LinkedList();
                xd2 = estudiantesDAO.consultagrupoasignado(person);
                if (!xd2.isEmpty()) {
                    grupo cursVO = new grupo();
                    for (Object xd3 : xd2) {
                        cursVO = (grupo) xd3;
                        if (cursVO.getId_grupo() == grupoVO.getId_grupo()) {
                            consulta += "<tr>";
                            consulta += "<td><input type=\"radio\" id=\"grupito\" name=\"grupito\" value=\"" + grupoVO.getId_grupo() + "\">" + "</td>";
                            consulta += "<td>" + grupoVO.getNombre_grupo() + "</td>"
                                    + "<td>" + grupoVO.getId_persona() + "</td>"
                                    + "<td>" + grupoVO.getDescripccion() + "</td>"
                                    + "</tr>";

                        }

                    }

                }

            }

        } else {
            consulta += "<tr>"
                    + "<td colspan=\"4\"> <center><div class=\"alert alert-danger\"><b>Atencion!</b>  No se han encontrado Grupos Disponibles.</div></center></td>"
                    + "</tr>"
                    + "</tbody>"
                    + "</table>"
                    + "</div>";
        }

        if (!datos.isEmpty()) {
            consulta += "</tbody>"
                    + "</table>"
                    + "</div>"
                    + "<button type=\"button\" class=\"btn btn-round btn-success\" onclick=\"confirmarAsignacionGrupo();\">Mostrar</button>";

        }

        return consulta;
    }

    private String consultaGrupos2(Long materia, long person) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = lamateriaxd.consultargruposdelamateria(materia);
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "Nombre del grupo"
                + "</th>"
                + "<th>"
                + "Id del Profesor A Cargo"
                + "</th>"
                + "<th>"
                + "Descripcion del grupo "
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {

            consulta += "<center><div class=\"alert alert-info\"><b>Atencion!</b>  Seleccione El Grupo de la Materia</div></center>";

            for (Object dato : datos) {
                grupo grupoVO = new grupo();
                grupoVO = (grupo) dato;

                LinkedList xd2 = new LinkedList();
                xd2 = estudiantesDAO.consultagrupoasignado(person);
                if (!xd2.isEmpty()) {
                    grupo cursVO = new grupo();
                    for (Object xd3 : xd2) {
                        cursVO = (grupo) xd3;
                        if (cursVO.getId_persona() == person && cursVO.getId_grupo() == grupoVO.getId_grupo() && cursVO.getCodigo_materia() == materia) {
                            consulta += "<tr>";
                            // consulta += "<td><input type=\"radio\" id=\"grupito\" name=\"grupito\" value=\"" + grupoVO.getId_grupo() + "\" onchange='javascript: matgrAModificar(" + grupoVO.getId_grupo() + "," + grupoVO.getId_persona() + "," + "document.getElementById(\"mat\").value" + ",4,1);'>" + "</td>";
                            // consulta += "<td><input type=\"radio\" id=\"grupito\" name=\"grupito\" value=\"" + grupoVO.getId_grupo() + "\" onchange='javascript: matgrAModificar(" + grupoVO.getId_grupo() + "," + grupoVO.getId_persona() + "," + "document.getElementById(\"mat\").value" + ",4,1);'>" + "</td>";           
                            consulta += "<td><input type=\"radio\" id=\"grupito\" name=\"grupito\" value=\"" + grupoVO.getId_grupo() + "\" onchange='javascript: listado_documentosEst(" + "document.getElementById(\"grupito\").value" + ");'>" + "</td>";
                            consulta += "<td>" + grupoVO.getNombre_grupo() + "</td>"
                                    + "<td>" + grupoVO.getId_persona() + "</td>"
                                    + "<td>" + grupoVO.getDescripccion() + "</td>"
                                    + "</tr>";

                        }

                    }

                }

            }

        } else {
            consulta += "<tr>"
                    + "<td colspan=\"4\"> <center><div class=\"alert alert-danger\"><b>Atencion!</b>  No se han encontrado Grupos Disponibles.</div></center></td>"
                    + "</tr>"
                    + "</tbody>"
                    + "</table>"
                    + "</div>";
        }

        if (!datos.isEmpty()) {
            consulta += "</tbody>"
                    + "</table>"
                    + "</div>";
            //      + "<button type=\"button\" class=\"btn btn-round btn-success\" onclick=\"matgrAModificar();\">Mostrar</button>";

        }

        return consulta;
    }

//    private String consultaGrupos3(Long materia, long person) throws SQLException {
//        String consulta = "<br>";
//        LinkedList datos = new LinkedList();
//        datos = lamateriaxd.consultargruposdelamateriaest(materia, person);
//        consulta += "<div class=\"table-responsive\">"
//                + "<table class=\"table\">"
//                + "<thead>"
//                + "<tr>"
//                + "<th>"
//                + "</th>"
//                + "<th>"
//                + "Nombre del grupo"
//                + "</th>"
//                + "<th>"
//                + "Id del Profesor A Cargo"
//                + "</th>"
//                + "<th>"
//                + "Descripcion del grupo "
//                + "</th>"
//                + "</tr>"
//                + "</thead>"
//                + "<tbody>";
//        if (!datos.isEmpty()) {
//            consulta += "<center><div class=\"alert alert-info\"><b>Atencion!</b>  Seleccione El Grupo de la Materia</div></center>";
//            for (Object dato : datos) {
//                grupo grupoVO = new grupo();
//                grupoVO = (grupo) dato;
//                consulta += "<td><input type=\"radio\" id=\"grupito\" name=\"grupito\" value=\"" + grupoVO.getId_grupo() + "\" onchange='javascript: listado_documentosEst(" + "document.getElementById(\"grupito\").value,document.getElementById(\"eldoctxd\").value" + ");'>" + "</td>";
//                consulta += "<td>" + grupoVO.getNombre_grupo() + "</td>"
//                        + "<td>" + grupoVO.getId_persona() + "</td>"
//                        + "<td>" + grupoVO.getDescripccion() + "</td>"
//                        + "</tr>";
//            }
//
//        } else {
//            consulta += "<tr>"
//                    + "<td colspan=\"4\"> <center><div class=\"alert alert-danger\"><b>Atencion!</b>  No se han encontrado Grupos Disponibles.</div></center></td>"
//                    + "</tr>"
//                    + "</tbody>"
//                    + "</table>"
//                    + "</div>";
//        }
//
//        if (!datos.isEmpty()) {
//            consulta += "</tbody>"
//                    + "</table>"
//                    + "</div>";
//        }
//        return consulta;
//    }
    private String materiasqueven(long idperson) throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = estudiantesDAO.listarmateriasqueven(idperson);
        if (!datos.isEmpty()) {
            materiaVO lamateriaVO = new materiaVO();
            for (Object dato : datos) {
                lamateriaVO = (materiaVO) dato;
                opcion += "<option value=\"" + lamateriaVO.getcodigo_materia() + "\">" + lamateriaVO.getnombre_materia() + "</option>";
            }
        } else {
            opcion += "<option>El estudiante no se encuentra en ningun grupo </option>";
        }
        return opcion;
    }

    private String consultaGruposdelestudiante(Long materia, long person) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = lamateriaxd.consultargruposdelamateriadelestudiante(materia, person);
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "Id del Grupo"
                + "</th>"
                + "<th>"
                + "Id del estudiante"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {

            consulta += "<center><div class=\"alert alert-info\"><b>Atencion!</b>  Seleccione El Grupo de la Materia que desea Modificar al Estudiante</div></center>";

            for (Object dato : datos) {
                materiaestudiantesVO grupoVO = new materiaestudiantesVO();
                grupoVO = (materiaestudiantesVO) dato;
                consulta += "<tr>";

                consulta += "<td><input type=\"radio\" id=\"grupito\" name=\"grupito\" value=\"" + grupoVO.getId_grupo() + "\" onchange='javascript: matgrAModificar(" + grupoVO.getId_grupo() + "," + grupoVO.getId_persona() + "," + "document.getElementById(\"mat\").value" + ",4,1);'>" + "</td>";
                consulta += "<td>" + grupoVO.getId_grupo() + "</td>"
                        + "<td>" + grupoVO.getId_persona() + "</td>"
                        + "</tr>";

            }

        } else {
            consulta += "<tr>"
                    + "<td colspan=\"4\"> <center><div class=\"alert alert-danger\"><b>Atencion!</b>  No se han encontrado Grupos al estudiante.</div></center></td>"
                    + "</tr>"
                    + "</tbody>"
                    + "</table>"
                    + "</div>";
        }

        if (!datos.isEmpty()) {
            consulta += "</tbody>"
                    + "</table>"
                    + "</div>"
                    //   + "<button type=\"button\" class=\"btn btn-round btn-success\" onclick=\"SeleccionMateriagrupoModificar();\">Asignar</button>";
                    //              + "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionMateriagrupoModificar();\"name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
                    //SeleccionMateriagrupoModificar
                    //onclick=\"SeleccionMateriaModificarpersona(document.getElementById(\"idpersona\").value, document.getElementById(\"opcion\").value, 8);
                    + "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionMateriagrupoModificar();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }

        return consulta;
    }

    private String estudiantesgruposqueven(long mate) throws SQLException {
        String consulta = "<br>";
        consulta += "<form role=\"form\" id=\"InsertarNotas\" name=\"InsertarNotas\">";
        LinkedList datos = new LinkedList();
        datos = estudiantesDAO.listarestgr(mate);
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "Id del Estudiante"
                + "</th>"
                + "<th>"
                + "Id del Grupo"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {
            consulta += "<center><div class=\"alert alert-info\"><b>Listado de Estudiantes que pertenecen Al Grupo Seleccionado!</b>  Seleccione El Estudiante que desea Insertar Nota</div></center>";

            materiaestudiantesVO lamateriaVO = new materiaestudiantesVO();
            for (Object dato : datos) {
                lamateriaVO = (materiaestudiantesVO) dato;
                //    opcion += "<option value=\"" + lamateriaVO.getId_persona() + "\">" + lamateriaVO.getId_persona() + "</option>";
                consulta += "<tr>";

                consulta += "<td><input type=\"radio\" id=\"grupito\" name=\"grupito\" value=\"" + lamateriaVO.getId_persona() + "\" onchange='javascript:InsertarNotasXD(" + lamateriaVO.getId_grupo() + "," + lamateriaVO.getId_persona() + "," + "13,1);'>" + "</td>";
                consulta += "<td>" + lamateriaVO.getId_persona() + "</td>"
                        + "<td>" + lamateriaVO.getId_grupo() + "</td>"
                        + "</tr>";

            }
        } else {
            ///voy aqui xd
            consulta += "<tr>"
                    + "<td colspan=\"4\"> <center><div class=\"alert alert-danger\"><b>Atencion!</b>  No se encontraron estudiantes en este grupo</div></center></td>"
                    + "</tr>"
                    + "</tbody>"
                    + "</table>"
                    + "</div>";
        }
        if (!datos.isEmpty()) {
            consulta += "</tbody>"
                    + "</table>"
                    + "</div>"
                    + "</form>"
                    //   + "<button type=\"button\" class=\"btn btn-round btn-success\" onclick=\"SeleccionMateriagrupoModificar();\">Asignar</button>";
                    //              + "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionMateriagrupoModificar();\"name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
                    //SeleccionMateriagrupoModificar
                    //onclick=\"SeleccionMateriaModificarpersona(document.getElementById(\"idpersona\").value, document.getElementById(\"opcion\").value, 8);
                    + "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionNotas();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private String estudianteAInsertarN(materiaestudiantesVO rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"notasInsertar\" enctype=\"application/x-www-form-urlencoded\" name=\"notasInsertar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"13\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>codigo del grupo</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idmaxd\" name=\"idmaxd\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getId_grupo() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>codigo del Estudiante</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idprrxd\" name=\"idprrxd\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getId_persona() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nota del Primer Corte</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"notaa\" name=\"notaa\" placeholder=\"Digita para modificar la nota \" value=\"" + rlVO.getNota1() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nota del Segundo Corte</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"notab\" name=\"notab\" placeholder=\"Digita para modificar la nota \" value=\"" + rlVO.getNota2() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nota del Tercer Corte</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"notac\" name=\"notac\" placeholder=\"Digita para modificar la nota \" value=\"" + rlVO.getNota3() + "\" required>"
                + "</div>"
                + "</form>";
        return formulario;
    }

    private String consultaGruposdelestudiante2(Long materia, long person) throws SQLException {
        String consulta = "<br>";
        consulta += "<form role=\"form\" id=\"VerNotas\" name=\"VerNotas\">";
        LinkedList datos = new LinkedList();
        datos = lamateriaxd.consultargruposdelamateriaest(materia, person);
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "Id del Grupo"
                + "</th>"
                + "<th>"
                + "Id del estudiante"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {

            consulta += "<center><div class=\"alert alert-info\"><b>Atencion!</b>  Seleccione El grupo de la materia que deseas mirar</div></center>";

            for (Object dato : datos) {
                materiaestudiantesVO grupoVO = new materiaestudiantesVO();
                grupoVO = (materiaestudiantesVO) dato;
                consulta += "<tr>";

                consulta += "<td><input type=\"radio\" id=\"grupito\" name=\"grupito\" value=\"" + grupoVO.getId_grupo() + "\" onchange='javascript:matgrVer(" + grupoVO.getId_grupo() + "," + grupoVO.getId_persona() + ",16,1);'>" + "</td>";
                consulta += "<td>" + grupoVO.getId_grupo() + "</td>"
                        + "<td>" + grupoVO.getId_persona() + "</td>"
                        + "</tr>";

            }

        } else {
            consulta += "<tr>"
                    + "<td colspan=\"4\"> <center><div class=\"alert alert-danger\"><b>Atencion!</b>  No se han encontrado Grupos al estudiante.</div></center></td>"
                    + "</tr>"
                    + "</tbody>"
                    + "</table>"
                    + "</div>";
        }
        if (!datos.isEmpty()) {
            consulta += "</tbody>"
                    + "</table>"
                    + "</div>"
                    + "</form>"
                    + "<button type=\"button\" class=\"btn btn-primary\" onclick=\"Selecciongrupovernota();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }

        return consulta;
    }

    private String VerNotas(materiaestudiantesVO rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"vernotas\" enctype=\"application/x-www-form-urlencoded\" name=\"VerNotas\">"
                + "<div class=\"form-group\">"
                + "<label>codigo del grupo</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idmaxd\" name=\"idmaxd\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getId_grupo() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>codigo del Estudiante</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idprrxd\" name=\"idprrxd\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getId_persona() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nota del Primer Corte</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"notaa\" name=\"notaa\" placeholder=\"Digita para modificar la nota \" value=\"" + rlVO.getNota1() + "\"  disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nota del Segundo Corte</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"notab\" name=\"notab\" placeholder=\"Digita para modificar la nota \" value=\"" + rlVO.getNota2() + "\"  disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nota del Tercer Corte</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"notac\" name=\"notac\" placeholder=\"Digita para modificar la nota \" value=\"" + rlVO.getNota3() + "\"  disabled>"
                + "</div>"
                + "</form>";
        return formulario;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(controllerEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(controllerEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
