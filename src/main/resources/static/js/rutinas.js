/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

//Creamos una nueva paquete sobre static, llamado static.jc, dentro de el le damos, new,other,other, javascrip file


/* La siguiente función se utiliza para visualizar la imagen seleccionada en la
 * página html donde se desea "cargar" utilizando un llamado "ajax"*/
//Visualizar la imagen html seleccionada en la pagina html donde se va a cargar
function readURL(input) {//le pasamos input por parametro
    if (input.files && input.files[0]) {//En los archivos de nuestro input haya archivos y que haya al menos uno en la posicion 0
        var reader = new FileReader();//Crea un reader
        reader.onload = function (e) {
            $('#blah')//JQUERY
                    .attr('src', e.target.result)
                    .height(200);//Altura maxima de 200
        };
        reader.readAsDataURL(input.files[0]);
    }
}

/* La siguiente función se utiliza para activar la cantidad de elementos seleccionados
 * En el carrito de compras utilizando un llamado "ajax" */
function addCard(formulario) {
    var valor = formulario.elements[0].value;
    var url = '/carrito/agregar';
    url = url + '/' + valor;
    $("#resultsBlock").load(url);
}
