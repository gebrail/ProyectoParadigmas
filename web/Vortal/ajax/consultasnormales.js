function listarprofesores()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("listadodeprofesoresxd").innerHTML = conexion.responseText;
        }
    }

    conexion.open("POST", "/Schooldays/ControllerPersonarol", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 6);
}


function listaradministradores()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("listadodeadminesxd").innerHTML = conexion.responseText;
        }
    }

    conexion.open("POST", "/Schooldays/ControllerPersonarol", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 7);
}



function listado_documentosEstudiantes()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("docest").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerPersona", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 8);
}



function listadomaterias()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("materiar").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 6);
}




function consultarGrupos(materiar, docest)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("grupos").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 7 + "&materiar=" + materiar + "&docest=" + docest);
}


function consultarGrupospr(materiar, docest)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("grupos").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 11 + "&materiar=" + materiar + "&docest=" + docest);
}




function estudianteconsultado(nombre, opcion, opcon)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("listado").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&nombre=" + nombre + "&opcon=" + opcon + "&opcion=" + opcion);
}



function consultarGruposdelestudiantexd(mat, docest)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("gr").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 9 + "&mat=" + mat + "&docest=" + docest);
}



function listado_matpr(eldoctxd)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("losdocum").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 10 + "&eldoctxd=" + eldoctxd);
}


function listado_documentosEst(grupito)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("losestugu").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 12 + "&grupito=" + grupito);
}


function consultarGruposdelestudiante(mat, docest)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("gr").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 9 + "&mat=" + mat + "&docest=" + docest);
}




function consultarGruposes(materiar, docest)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("grupos").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 11 + "&materiar=" + materiar + "&docest=" + docest);
}


function listado_matest(eldoctxd)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("losdocum").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 14 + "&eldoctxd=" + eldoctxd);
}


//function consultarGruposestudiantes(materiar, docest)
//{
//    var conexion;
//    if (window.XMLHttpRequest) {
//        conexion = new XMLHttpRequest();
//    } else {
//        conexion = new ActiveXObject("Microsoft.XMLHTTP");
//    }
//    conexion.onreadystatechange = function () {
//        if (conexion.readyState == 4 && conexion.status == 200) {
//            document.getElementById("grupos").innerHTML = conexion.responseText;
//        }
//    }
//    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
//    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//    conexion.send("&opcion=" + 15 + "&materiar=" + materiar + "&docest=" + docest);
//}




function listado_grEst(losdocum, eldoctxd)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("losestugu").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 15 + "&losdocum=" + losdocum + "&eldoctxd=" + eldoctxd);
}

