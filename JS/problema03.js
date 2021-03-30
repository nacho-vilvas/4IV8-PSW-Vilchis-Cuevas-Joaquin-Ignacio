function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);


}

function descuento(){
    var valor = document.formulario.costo.value;
    var result = parseInt(valor);
    var desc = result*0.15;
    var total = result-desc;

    document.formulario.descuentoo.value = "$"+total;
}

function borrar(){
    document.formulario.costo.value = "";
    document.formulario.descuentoo.value = "";
}