function materiaconsultado(nombre, opcion, opcon)
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
    conexion.open("POST", "/Schooldays/controllerMateria", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&nombre=" + nombre + "&opcon=" + opcon + "&opcion=" + opcion);
}


function materiaAModificar(idmateria, opcion, opmod)
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
    conexion.open("POST", "/Schooldays/controllerMateria", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&idmateria=" + idmateria + "&opmod=" + opmod + "&opcion=" + opcion);
}



