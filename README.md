# Exámen - Billing App

## Contexto

Hemos sido contratados por una empresa que se dedica a comercializar productos para 
el hogar. La empresa cuenta actualmente con una aplicación donde administran los productos 
que estan disponibles para la venta, los clientes y las ordenes de venta; 
**pero no cuenta con una aplicación que permita facturar**.

Despues de un proceso de licitación nuestra empresa ha sido seleccionada para desarrollar
una aplicación que permita facturar los productos que se venden en la empresa. Como parte
de los requerimientos no funcionales de la empresa, debemos conectarnos a la aplicación
existente para obtener los datos de los productos, ordenes y clientes.

## Requerimientos de alto nivel

1. La aplicación recibirá una notificación cuando se cree una nueva orden de venta.
2. A partir de la notificación, la aplicación debe obtener los datos de la orden de venta
   y generar una factura.
3. La factura debe contener los siguientes datos:
   - Número de factura
   - Código de verificación de AFIP (CAE o CAI)
   - Letra de la factura (A o B)
   - Fecha de la factura
   - Fecha de vencimiento
   - Datos del cliente
     - Nombre y apellido
     - Tipo y numero de documento
     - Dirección del cliente
   - Datos de la empresa
   - Lista de productos
     - Nombre del producto
     - Cantidad
     - Precio unitario
     - Precio total
   - Subtotal de los productos
   - Impuestos
   - Total de la factura
4. La aplicación debe almacenar, **de manera normalizada** los datos de la factura generada.
5. La aplicación debe proveer un servicio REST que permita obtener los datos de la factura
   a partir del número de factura (Por ejemplo: 00001-00000001).
6. Se debe entregar la aplicación con su Dockerfile y su compose para poder ejecutarla
integrada con la Base de Datos MySql.
7. La aplicación debe tener todos los Tests Unitarios necesarios para validar su funcionamiento.

## Requerimientos detallados

### Número de factura

La empresa posee dos puntos de venta para generar numeros de facturas, uno para facturas
A y otro para facturas B. El punto de venta para facturas A es el 00001 y el punto de venta
para facturas B es el 00002.
Los números de facturas deben ser correlativos, es decir, si la ultima factura generada
para el punto de venta 00001 es la 00000001, la siguiente factura debe ser la 00000002.

### Código de verificación de AFIP (CAE o CAI)

La empresa ya esta integrada a los organismos de control para obtener el CAE (Código de Autorización Electrónico),
para esto ya se cuenta con un servicio de terceros que proveera el numero CAE en caso
de poder conectarse al organismo de control. Si por algún motivo no se puede obtener, 
el sistemna debe utilizar el CAI (Código de Autorización de Impresión). 
El número CAI provisto por la empresa es el 12345678901234567890.

### Letra de la factura

La letra de la factura debe ser A o B, dependiendo de la condición impositiva del cliente.
Si se trata de un cliente responsable inscripto, la factura debe ser A, en caso contrario
debe ser B.

### Fecha de la factura

La fecha de la factura debe ser la fecha en la que se genera la factura, no la fecha de la orden de venta.

### Fecha de vencimiento

En caso de tratarse de una factura A, la fecha de vencimiento debe ser de 10 días corridos
a partir de la fecha de la factura. En caso de tratarse de una factura B, la fecha de vencimiento
debe ser igual a la fecha de facturación.

### Datos del cliente

Los datos del cliente deben obtenerse a través de un servicio provisto por la aplicación existente.

### Datos de la empresa

Los datos de la empresa son los siguientes:
 - Nombre: Mi empresa S.A.
 - Domicilio: Av. Siempre Viva 1234
 - Localidad: CBA
 - Tipo y numero de documento: CUIT 30-12345678-9

### Lista de productos

Los nombres y precios unitarios de los productos deben obtenerse a través de un servicio 
provisto por la aplicación existente. La cantidad de productos a facturar vendrá en la orden de venta.
El precio total de cada producto debe calcularse como el precio unitario por la cantidad.

### Subtotal de los productos

El subtotal de los productos es la suma de los precios totales de cada producto.

### Impuestos

El impuesto a aplicar es del 27% sobre el subtotal de los productos en caso de tratarse de una factura A.
En caso de tratarse de una factura B, debe aplicarse el 21% sobre el subtotal de los productos.

### Total de la factura

El total de la factura es la suma del subtotal de los productos más los impuestos.

### Almacenamiento de los datos

Los datos deben ser almacenados en una base de datos relacional MySQL. 
La base de datos debe estar normalizada y contar con un modelo cabecera/detalle.


## Requerimientos técnicos

El mensaje de la orden de venta tendrá el siguiente formato. Debe crearse una api REST
que reciba el POST del mensaje y genere la factura.
    
```json
{
    "message_id": 173547194,
    "datetime": "2020-01-01 10:00:00",
    "payload": {
      "order_id": 123456789
    }
}
```

El servicio REST para consultar el detalle de la factura debe tener el siguiente formato:

```
GET /invoices/{invoice_number}
```

El body de la factura debe tener el siguiente formato:
    
```json
    {
        "invoice_number": "00001-00000001",
        "invoice_control": {
          "control_type": "CAE",
          "control_number": "12345678901234567890"
        },
        "letter": "B",
        "date": "2020-01-01",
        "due_date": "2020-01-15",
        "customer": {
            "name": "Juan Perez",
            "document_type": "DNI",
            "document_number": "12345678",
            "address": "Av. Siempre Viva 1234"
        },
        "company": {
            "name": "Mi empresa S.A.",
            "address": "Av. Siempre Viva 1234",
            "document_type": "CUIT",
            "document_number": "30-12345678-9"
        },
        "items": [
            {
                "name": "Producto 1",
                "quantity": 1,
                "unit_price": 100,
                "total_price": 100
            },
            {
                "name": "Producto 2",
                "quantity": 2,
                "unit_price": 200,
                "total_price": 400
            }
        ],
        "subtotal": 500,
        "tax": {
            "name": "IVA",
            "percentage": 21,
            "total": 105
        },
        "total": 605
    }
```

## APIs Server

Para consumir las APIs de la aplicación existente, se descargar la imagen Docker
"[tupfrcutn/erp-server:1.0.0](https://hub.docker.com/r/tupfrcutn/erp-server/tags)"
