function cursoconsultado(nombre, opcion, opcon)
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function() {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("listado").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerCurso", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&nombre=" + nombre + "&opcon=" + opcon + "&opcion=" + opcion);
}


function cursoAModificar(idcurso, opcion, opmod)
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
    conexion.open("POST", "/Schooldays/controllerCurso", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&idcurso=" + idcurso + "&opmod=" + opmod + "&opcion=" + opcion);
}


function listadocursos()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function() {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("loscursos").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerCursopersona", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 7);
}




function listarcursopersonas()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function() {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("listadodecursopersonas").innerHTML = conexion.responseText;
        }
    }
    
    conexion.open("POST", "/Schooldays/controllerCursopersona", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 0);
}




function consultapersonacurso(atributo, opcion, opcon) {
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function() {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("listado").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerCursopersona", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&atributo=" + atributo + "&opcon=" + opcon + "&opcion=" + opcion);
}