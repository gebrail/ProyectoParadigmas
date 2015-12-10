function listado_materias()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("lasmaterias").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerGrupo", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 7);
}


function listado_profesores()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("losprofesores").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerGrupo", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 6);
}


function listargrupos()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("listadogrupos").innerHTML = conexion.responseText;
        }
    }

    conexion.open("POST", "/Schooldays/controllerGrupo", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 0);
}



function grupoconsultado(nombre, opcion, opcon)
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
    conexion.open("POST", "/Schooldays/controllerGrupo", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&nombre=" + nombre + "&opcon=" + opcon + "&opcion=" + opcion);
}




function grupoAModificar(idgrupo, opcion, opmod)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("modificarForm").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerGrupo", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&idgrupo=" + idgrupo + "&opmod=" + opmod + "&opcion=" + opcion);
}


