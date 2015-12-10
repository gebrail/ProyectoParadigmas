function consultarGruposdelestudiante(docest)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function() {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("mat").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 8 + "&docest=" + docest );
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




function matgrAModificar(elgrupito,eldocument,lamat, opcion, opmod)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }

    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("losidsxd").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&elgrupito=" + elgrupito + "&eldocument=" + eldocument + "&lamat=" + lamat+ "&opmod=" + opmod + "&opcion=" + opcion);
}





function InsertarNotasXD(idgrxd,idpersxd, opcion, opmod)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function() {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("modificarForm").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerEstudiante", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&idgrxd=" + idgrxd + "&idpersxd=" + idpersxd + "&opmod=" + opmod + "&opcion=" + opcion);
}