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


    if (!validarTextoVacio(idper) || !validarTextoVacio(primernombree) || !validarTextoVacio(primerapellidx) || !validarTextoVacio(segundoapellidx) || !validarTextoVacio(direccionx) || !validarTextoVacio(telefonox) || !validarTextoVacio(correox)) {
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



function SeleccionCuentaModificar()
{
    var marcado = validarRadio("idcuenta");
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



function validarRegistroCuenta() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var idpersona = document.getElementById("losdoc").value;
    var tiporol = document.getElementById("losroles").value;
    if (!validarTextoVacio(username) || !validarTextoVacio(password) || !validarTextoVacio(idpersona) || !validarTextoVacio(tiporol)) {
        alert("Por favor verifique que no hallan campos vacios");
    }
    else {
        var adm = document.getElementById("registroCuenta");
        adm.action = "/Schooldays/ControllerPersonarol";
        adm.submit();
    }
}


function confirmarModificarCuenta(passwordxd)
{

    if (!validarTextoVacio(passwordxd)) {
        alert("Campos No Validos, verifique campos vacios o caracteres especiales.");
    }
    else {
        var adm = document.getElementById("cuentaModificar");
        adm.action = "/Schooldays/ControllerPersonarol";
        adm.submit();
    }
}




function confirmarEliminarCuenta() {
    var marcado = validarRadio("eliminar");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var adm = document.getElementById("eliminarCuenta");
        adm.action = "/Schooldays/ControllerPersonarol";
        adm.submit();
    }
}




function validarRegistroCurso() {
    var idcurso = document.getElementById("idcurso").value;
    var nombrecurso = document.getElementById("nombrecurso").value;
    var descripciondelcurso = document.getElementById("descripcion").value;

    if (!validarTextoVacio(idcurso) || !validarTextoVacio(nombrecurso) || !validarTextoVacio(descripciondelcurso)) {
        alert("Por favor verifique que no hallan campos vacios");
    }
    else {
        var adm = document.getElementById("registroCurso");
        adm.action = "/Schooldays/controllerCurso";
        adm.submit();
    }
}



function confirmarModificarCurso(nombrecurso, descripcioncurso) {

    if (!validarTextoVacio(nombrecurso) || !validarTextoVacio(descripcioncurso)) {
        alert("Campos No Validos, verifique campos vacios o caracteres especiales.");
    }
    else {
        var adm = document.getElementById("cursoModificar");
        adm.action = "/Schooldays/controllerCurso";
        adm.submit();

    }
}

function SeleccionCursoModificar()
{
    var marcado = validarRadio("idcurso");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var btn = document.getElementById("btnVisualizar");
        btn.setAttribute("data-toggle", "modal");
        btn.setAttribute("data-target", "#modificar");
    }
}

function confirmarEliminarCurso() {
    var marcado = validarRadio("eliminar");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var adm = document.getElementById("eliminarCurso");
        adm.action = "/Schooldays/controllerCurso";
        adm.submit();
    }
}


function validarRegistroMateria() {
    var idmateria = document.getElementById("idmateria").value;
    var nombremateria = document.getElementById("nombremateria").value;
    var descripciondelmateria = document.getElementById("descripcion").value;
    if (!validarTextoVacio(idmateria) || !validarTextoVacio(nombremateria) || !validarTextoVacio(descripciondelmateria)) {
        alert("Por favor verifique que no hallan campos vacios");
    }
    else {
        var adm = document.getElementById("registroMateria");
        adm.action = "/Schooldays/controllerMateria";
        adm.submit();
    }
}


function SeleccionMateriaModificar()
{
    var marcado = validarRadio("idmateria");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var btn = document.getElementById("btnVisualizar");
        btn.setAttribute("data-toggle", "modal");
        btn.setAttribute("data-target", "#modificar");
    }
}




function confirmarModificarMateria(nombremateria, descripcionmateria) {

    if (!validarTextoVacio(nombremateria) || !validarTextoVacio(descripcionmateria)) {
        alert("Campos No Validos, verifique campos vacios o caracteres especiales.");
    }
    else {
        var adm = document.getElementById("materiaModificar");
        adm.action = "/Schooldays/controllerMateria";
        adm.submit();

    }
}

function confirmarEliminarMateria() {
    var marcado = validarRadio("eliminar");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var adm = document.getElementById("eliminarMateria");
        adm.action = "/Schooldays/controllerMateria";
        adm.submit();
    }
}

function validarRegistroRol(nombre, descripcion) {
    var idrolxd = document.getElementById("idrolxd").value;
    if (!validarTextoVacio(idrolxd) || !validaTextoNumeros(idrolxd) || !validarTextoVacio(nombre) || !validarTextoVacio(descripcion) || !validaSoloTexto(nombre) || !validaTextoNumeros(descripcion)) {
        alert("Campos No Validos, verifique que no esten vacios, ni con caracteres especiales");
    }
    else {
        var adm = document.getElementById("registroRol");
        adm.action = "/Schooldays/controllerRol";
        adm.submit();
    }
}



function confirmarEliminarRol() {
    var marcado = validarRadio("eliminar");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {

        var adm = document.getElementById("eliminarRol");
        adm.action = "/Schooldays/controllerRol";
        adm.submit();


    }
}


function seleccionRolModificar() {
    var marcado = validarRadio("rlid");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var btn = document.getElementById("btnVisualizar");
        btn.setAttribute("data-toggle", "modal");
        btn.setAttribute("data-target", "#modificar");
    }
}

function confirmarModificarRol(nombre, descripcion) {
    if (!validarTextoVacio(nombre) || !validarTextoVacio(descripcion) || !validaSoloTexto(nombre) || !validaTextoNumeros(descripcion)) {
        alert("Modificacion No Valida, verifique campos no vacios, ni caracteres especiales.");
    }
    else {
        var adm = document.getElementById("rolModificar");
        adm.action = "/Schooldays/controllerRol";
        adm.submit();
    }
}

function validarAsignacionCurso() {
    var idpersonas = document.getElementById("losdoc").value;
    var cursos = document.getElementById("loscursos").value;
    if (!validarTextoVacio(idpersonas) || !validarTextoVacio(cursos)) {
        alert("Por favor verifique que no hallan campos vacios");
    }
    else {
        var adm = document.getElementById("registroCursopersona");
        adm.action = "/Schooldays/controllerCursopersona";
        adm.submit();
    }
}



function validarRegistroTido(idtipodoc,nombre, descripcion) {
    if (!validarTextoVacio(idtipodoc) ||!validarTextoVacio(nombre) || !validarTextoVacio(descripcion) || !validaSoloTexto(nombre) || !validaSoloTexto(descripcion) || !validaTextoNumeros(idtipodoc)) {
        alert("Campos No Validos, verifique campos vacios y caracteres especiales.");
    }
    else {
        var adm = document.getElementById("registroTipoDocumento");
        adm.action = "/Schooldays/controllerTipodocumento";
        adm.submit();
    }
}

function confirmarEliminarTido() {
    var marcado = validarRadio("eliminar");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    var adm = document.getElementById("eliminarTido");
    adm.action = "/Schooldays/controllerTipodocumento";
    adm.submit();
}




function validarRegistroAula(idtipoaula,nombre, descripcion) {
    if (!validarTextoVacio(idtipoaula) ||!validarTextoVacio(nombre) || !validarTextoVacio(descripcion) || !validaSoloTexto(nombre) || !validaSoloTexto(descripcion) || !validaTextoNumeros(idtipoaula)) {
        alert("Campos No Validos, verifique campos vacios y caracteres especiales.");
    }
    else {
        var adm = document.getElementById("registroTipoAula");
        adm.action = "/Schooldays/controllerTipoaula";
        adm.submit();
    }
}

function confirmarEliminarAula() {
    var marcado = validarRadio("eliminar");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    var adm = document.getElementById("eliminarAula");
    adm.action = "/Schooldays/controllerTipoaula";
    adm.submit();
}





function validarRegistroSalon() {
    var idaula = document.getElementById("idaula").value;
    var nombreaula = document.getElementById("nombreaula").value;
    var tipoaula = document.getElementById("tipoaula").value;
    if (!validarTextoVacio(idaula) || !validarTextoVacio(nombreaula) || !validarTextoVacio(tipoaula)) {
        alert("Por favor verifique que no hallan campos vacios");
    }
    else {
        var adm = document.getElementById("registroAula");
        adm.action = "/Schooldays/controllerAula";
        adm.submit();
    }
}


function SeleccionAulaModificar()
{
    var marcado = validarRadio("idaula");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var btn = document.getElementById("btnVisualizar");
        btn.setAttribute("data-toggle", "modal");
        btn.setAttribute("data-target", "#modificar");
    }
}




function confirmarModificarAula(nombreaula, tipoaula) {

    if (!validarTextoVacio(nombreaula) || !validarTextoVacio(tipoaula)) {
        alert("Campos No Validos, verifique campos vacios o caracteres especiales.");
    }
    else {
        var adm = document.getElementById("aulaModificar");
        adm.action = "/Schooldays/controllerAula";
        adm.submit();

    }
}

function confirmarEliminarSalon() {
    var marcado = validarRadio("eliminar");
    if (!marcado) {
        alert("Debe Seleccionar Alguno de la lista.");
    }
    else {
        var adm = document.getElementById("eliminarAula");
        adm.action = "/Schooldays/controllerAula";
        adm.submit();
    }
}