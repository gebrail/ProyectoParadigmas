function listarmaterias()
{
    var conexion;
    if (window.XMLHttpRequest) {
        conexion = new XMLHttpRequest();
    } else {
        conexion = new ActiveXObject("Microsoft.XMLHTTP");
    }
    conexion.onreadystatechange = function() {
        if (conexion.readyState == 4 && conexion.status == 200) {
            document.getElementById("listadodematerias").innerHTML = conexion.responseText;
        }
    }
    
    conexion.open("POST", "/Schooldays/controllerMateria", true);
    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    conexion.send("&opcion=" + 0);
}