document.addEventListener("DOMContentLoaded", function () {
    // Manejo de los productos agregados al carrito
    const carritoProductos = document.getElementById("carritoProductos");
    const totalElemento = document.getElementById("total");

    // Añadir productos al carrito (esto puede ser modificado para obtener productos del backend)
    // Por ejemplo, al cargar el carrito desde el servidor:
    // fetch("/api/carrito")
    //     .then(response => response.json())
    //     .then(data => {
    //         actualizarCarrito(data);
    //     });

    // Función para actualizar la tabla del carrito
    function actualizarCarrito(productos) {
        carritoProductos.innerHTML = "";
        let total = 0;

        productos.forEach(producto => {
            const fila = document.createElement("tr");

            fila.innerHTML = `
                <td>${producto.nombre}</td>
                <td>S/ ${producto.precio}</td>
                <td><input type="number" value="${producto.cantidad}" min="1" max="${producto.stock}" class="form-control form-control-sm" onchange="actualizarSubtotal(this, ${producto.precio})"></td>
                <td class="subtotal">S/ ${producto.precio * producto.cantidad}</td>
                <td><button class="btn btn-danger btn-sm" onclick="eliminarProducto(this)">Eliminar</button></td>
            `;
            carritoProductos.appendChild(fila);

            total += producto.precio * producto.cantidad;
        });

        totalElemento.innerText = "S/ " + total.toFixed(2);
    }

    // Función para actualizar el subtotal de un producto
    function actualizarSubtotal(input, precio) {
        const cantidad = input.value;
        const fila = input.closest("tr");
        const subtotal = cantidad * precio;

        fila.querySelector(".subtotal").innerText = "S/ " + subtotal.toFixed(2);
        actualizarTotal();
    }

    // Función para eliminar un producto del carrito
    function eliminarProducto(btn) {
        const fila = btn.closest("tr");
        fila.remove();
        actualizarTotal();
    }

    // Función para actualizar el total del carrito
    function actualizarTotal() {
        const filas = document.querySelectorAll("#carritoProductos tr");
        let total = 0;

        filas.forEach(fila => {
            const subtotal = parseFloat(fila.querySelector(".subtotal").innerText.replace("S/", ""));
            total += subtotal;
        });

        totalElemento.innerText = "S/ " + total.toFixed(2);
    }

    // Iniciar la carga del carrito (esto sería reemplazado por una llamada a la API del backend)
    actualizarCarrito([
        { nombre: "Producto 1", precio: 30, cantidad: 2, stock: 10 },
        { nombre: "Producto 2", precio: 50, cantidad: 1, stock: 5 }
    ]);
});
