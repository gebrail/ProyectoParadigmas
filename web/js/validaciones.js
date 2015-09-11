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
         adm.action = "/Shooldays/ControllerPersonarol";
        adm.submit();
    }

}

