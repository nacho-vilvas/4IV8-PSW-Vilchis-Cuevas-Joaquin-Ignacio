function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}

function calculo(){
    var valor0 = document.formulario.num_h.value;
    var valor1 = document.formulario.num_m.value;
    var valor_a = parseInt(valor0);
    var valor_b = parseInt(valor1);
    var valor_c = valor_a + valor_b;
    var porcentaje_h = (valor_a/valor_c) * 100;
    var porcentaje_m = (valor_b/valor_c) * 100;

    document.formulario.final_h.value = porcentaje_h + " %";
    document.formulario.final_m.value = porcentaje_m + " %";
}

function borrar(){
    document.formulario.num_h.value = "";
    document.formulario.num_m.value = "";
    document.formulario.final_h.value = "";
    document.formulario.final_m.value = "";
}