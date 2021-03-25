function validar(formulario){

    if(formulario.nombre.value.length < 5){
        alert("Por favor escribe mas de 5 caracteres en el campo nombre");
        formulario.nombre.focus();
    return false;
    }

    var checkOk = "QWERTYUIOPASDFGHJKLÑZXCVBNM" 
    + "qwertyuioasdfghjklzxcvbnm";

    var checkStr = formulario.nombre.value;

    var allValid = true;

    for(var i = 0; i < checkStr.length; i++){
        var ch = checkStr.charAt(i);
        for( var j = 0; j < checkOk.length; j++)
        if(ch == checkOk.charAt(j))
            break;
        
        if(j == checkOk.length){
            allValid = false;
            break;
        }
    }

    if(!allValid){
        alert("Escribe solo letras en el campo Nombre");
        formulario.nombre.focus();
        return false;
    }

    var checkOk = "1234567890";

    var checkStr = formulario.edad.value;

    var allValid = true;

    for(var i = 0; i < checkStr.length; i++){
        var ch = checkStr.charAt(i);
        for( var j = 0; j < checkOk.length; j++)
        if(ch == checkOk.charAt(j))
            break;
        
        if(j == checkOk.length){
            allValid = false;
            break;
        }
    }

    if(!allValid){
        alert("Escribe solo numeros en el campo Edad");
        formulario.edad.focus();
        return false;
    }

    var txt = formulario.email.value;

    var b = /^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/;

    alert("Email" + (b.test(txt)?"":" no ") + " valido");
    return b.test(txt);


}