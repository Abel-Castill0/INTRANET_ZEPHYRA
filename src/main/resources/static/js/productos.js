document.addEventListener("DOMContentLoaded", function () {
    // Agregar funcionalidad al botón de agregar producto
    const productos = [
        { idProducto: 1, nombre: "Producto A", precio: 50, detalle: "Descripción A", stock: 10, color: "Rojo", tamaño: "M" },
        { idProducto: 2, nombre: "Producto B", precio: 80, detalle: "Descripción B", stock: 20, color: "Azul", tamaño: "L" },
        // Otros productos...
    ];

    // Función para agregar productos a la tabla
    function agregarProducto(producto) {
        const tabla = document.querySelector("tbody");
        const fila = document.createElement("tr");

        fila.innerHTML = `
            <td>${producto.idProducto}</td>
            <td>${producto.nombre}</td>
            <td>S/ ${producto.precio}</td>
            <td>${producto.detalle}</td>
            <td>${producto.stock}</td>
            <td>${producto.color}</td>
            <td>${producto.tamaño}</td>
            <td>
                <a href="/producto/editar/${producto.idProducto}" class="btn btn-warning">Editar</a>
                <a href="/producto/eliminar/${producto.idProducto}" class="btn btn-danger">Eliminar</a>
            </td>
        `;
        tabla.appendChild(fila);
    }

    // Simulando que se agregan productos a la lista
    productos.forEach(agregarProducto);
});
