/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function aulaConsultado(nombre, opcion)
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
    conexion.open("POST", "/Schooldays/controllerTipoaula", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&nombre=" + nombre + "&opcion=" + opcion);
}

function listadoAula()
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
    conexion.open("POST", "/Schooldays/controllerTipoaula", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 0);
}



function lostiposdeaulaxd()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function () {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("tipoaula").innerHTML = conexion.responseText;
        }
    }
    conexion.open("POST", "/Schooldays/controllerAula", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 7);
}


function listadodeAula()
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
    conexion.open("POST", "/Schooldays/controllerAula", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 0);
}



function aulaxdconsultado(nombre, opcion, opcon)
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
    conexion.open("POST", "/Schooldays/controllerAula", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&nombre=" + nombre + "&opcon=" + opcon + "&opcion=" + opcion);
}




function aulaAModificar(idaula, opcion, opmod)
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
    conexion.open("POST", "/Schooldays/controllerAula", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&idaula=" + idaula + "&opmod=" + opmod + "&opcion=" + opcion);
}




