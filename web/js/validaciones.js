function validarTextoVacio(cadena) {
    if (cadena == null || cadena.length == 0 || /^\s+$/.test(cadena))
        return false;
    else
        return true;
}

function validaSoloTexto(cadena) {
    var patron = /^([a-zA-Z]|\s*)*$/;
    if (!cadena.search(patron))
        return true;
    else
        return false;
}

function validaTextoNumeros(cadena) {
    var patron = /^([0-9A-Za-z]|\s*)*$/;
    if (!cadena.search(patron))
        return true;
    else
        return false;
}


function eliniciosesion(usuario, password) {
    if (!validarTextoVacio(usuario) || !validarTextoVacio(password)) {

        alert("Por favor verifique el usuario o la contraseña");
    }
    else {
        var adm = document.getElementById("iniciosesion");
        adm.action = "/Schooldays/ControllerPersonarol";
        adm.submit();
    }

}

function validarRegistroPersona() {

    var idper = document.getElementById("documento").value;
    var primernombree = document.getElementById("primernombre").value;
    var segundonombr = document.getElementById("segundonombre").value;
    var primerapellidx = document.getElementById("primerapellido").value;
    var segundoapellidx = document.getElementById("segundoapellido").value;
    var direccionx = document.getElementById("direccion").value;
    var telefonox = document.getElementById("telefono").value;
    var correox = document.getElementById("correo").value;


    if (!validarTextoVacio(idper) || !validarTextoVacio(primernombree) || !validarTextoVacio(segundonombr) || !validarTextoVacio(primerapellidx) || !validarTextoVacio(segundoapellidx) || !validarTextoVacio(direccionx) || !validarTextoVacio(telefonox) || !validarTextoVacio(correox)) {
        alert("Por favor verifique que hallan campos vacios");
    } else
    if (!validaSoloTexto(primernombree) || !validaSoloTexto(primerapellidx) || !validaSoloTexto(segundonombr) || !validaSoloTexto(segundoapellidx)) {
        alert("Solo se admite texto");
    } else if (!validaTextoNumeros(idper) || !validaTextoNumeros(telefonox)) {

        alert("Solo se admiten numeros por favor vuelva a mirar");
    }

    else {
        var adm = document.getElementById("registroPersona");
        adm.action = "/Schooldays/controllerPersona";
        adm.submit();

    }
}

function confirmarEliminarpersona() {
    var marcado = validarRadio("eliminar");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var adm = document.getElementById("eliminarPersona");
        adm.action = "/Schooldays/controllerPersona";
        adm.submit();
    }
}


function seleccionPersonaModificar() {
    var marcado = validarRadio("perlid");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var btn = document.getElementById("btnVisualizar");
        btn.setAttribute("data-toggle", "modal");
        btn.setAttribute("data-target", "#modificar");
    }
}
function validarRadio(elemento) {
    var marcado = false;
    var elementos = document.getElementsByName(elemento);
    for (var i = 0; i < elementos.length; i++) {
        if (elementos[i].checked)
            marcado = true;
    }
    return marcado;
}


function confirmarModificarPersona() {

        var adm = document.getElementById("personaModificar");
        adm.action = "/Schooldays/controllerPersona";
        adm.submit();
    
}
