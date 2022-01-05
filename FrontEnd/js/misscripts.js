(function recursivo() {
    $('#txtId').val(uuid.v4());
    setTimeout(function() {
        recursivo();
    }, 4000)
}());

function ejemploGetLista() {
    fetch('http://localhost:8080/clifrec')
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            console.log('data = ', data);
        })
        .catch(function(err) {
            console.error(err);
        });
}

function ejemploGetUsuario(id) {
    fetch(`http://localhost:8080/clifrec/${id}`)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            console.log('data = ', data);
        })
        .catch(function(err) {
            console.error(err);
        });
}

function ejemploPostObject(objEnviar) {

    fetch('http://localhost:8080/clifrec', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin':'*'
        },
        body: JSON.stringify(objEnviar)
        })
        .then(function(response) {
            if(response.status === 201) {
                alert('se agrego correctamente')
            }            
        })
        .then(function(data) {
            console.log('data = ', data);
        })
        .catch(function(err) {
            console.error(err);
        });
}

function ejemploGetRemito(id) {
    fetch('http://localhost:8090/ventas/remito/'+id)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            console.log('data = ', data);
        })
        .catch(function(err) {
            console.error(err);
        });
}


{
    $('#btnAgregar').click(() => {

        let objAdd = {
            idCliente: $('#txtId').val(),
            nombre: $('#txtNombre').val(),
            apellido: $('#txtApellido').val(),
            tipoDescuento: $('#selTipoDescuento').val()
        }
        ejemploPostObject(objAdd);
    });
}

{
    $('#btnLista').click(() => {       
        ejemploGetLista();
    });
}

{
    $('#btnUsuario').click(() => {       
        ejemploGetUsuario($('#idUsuario').val());
    });
}
 
{
    $('#btnVentas').click(() => {       
        ejemploGetRemito($('#idUsuario').val());
    });
}
{
    $('#btnHache').click(() => {       
        console.log('hace algo este boton');
    });
}