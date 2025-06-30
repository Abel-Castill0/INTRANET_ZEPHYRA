package INTRANET_ZEPHYRA.demo.Servicios;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import INTRANET_ZEPHYRA.demo.DAO.ProductoDAO;
import INTRANET_ZEPHYRA.demo.DAO.VentaDAO;
import INTRANET_ZEPHYRA.demo.Entidad.DetalleVenta;
import INTRANET_ZEPHYRA.demo.Entidad.Producto;
import INTRANET_ZEPHYRA.demo.Entidad.Venta;

@Service
public class VentaServicio {

    @Autowired 
    private VentaDAO ventaDAO;

    @Autowired
    private ProductoDAO productoDAO;

    // Registrar venta con los detalles de la compra
    public Venta registrarVenta(List<DetalleVenta> detalles) {
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());  // Establece la fecha de la venta

        BigDecimal total = BigDecimal.ZERO;  // Total de la venta

        // Itera sobre cada detalle de la venta
        for (DetalleVenta d : detalles) {
            Producto producto = productoDAO.findById(d.getProducto().getIdProducto()).orElseThrow();
            d.setPrecioUnitario(producto.getPrecio());
            d.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(d.getCantidad())));
            d.setVenta(venta);

            // Actualiza el stock del producto
            producto.setStock(producto.getStock() - d.getCantidad());
            productoDAO.save(producto);  // Guarda el producto actualizado

            total = total.add(d.getSubtotal());  // Calcula el total
        }

        // Asocia el total de la venta y los detalles
        venta.setTotal(total);
        venta.setDetalles(detalles);

        return ventaDAO.save(venta);  // Guarda la venta en la base de datos
    }
}
// Este servicio maneja la lógica de negocio relacionada con las ventas, incluyendo el registro de ventas y la actualización del stock de productos.
// Utiliza los repositorios VentaDAO y ProductoDAO para interactuar con la base de datos.
// El método registrarVenta recibe una lista de detalles de venta, calcula el total y actualiza el          stock de los productos vendidos.
// Asegura que cada producto vendido tenga su stock actualizado y calcula el subtotal de cada detalle de venta.
// Finalmente, guarda la venta completa en la base de datos y devuelve el objeto Venta resultante.
// Este enfoque asegura que todas las operaciones relacionadas con una venta se manejen de manera transaccional, garantizando la integridad de los datos en la base de datos.       