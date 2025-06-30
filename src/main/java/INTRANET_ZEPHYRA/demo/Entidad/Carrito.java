package INTRANET_ZEPHYRA.demo.Entidad;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;


public class Carrito {

    private List<DetalleVenta> productos;
    private BigDecimal total;

    public Carrito() {
        this.productos = new ArrayList<>();
        this.total = BigDecimal.ZERO;
    }

    public List<DetalleVenta> getProductos() {
        return productos;
    }

    public void agregarProducto(DetalleVenta detalle) {
        this.productos.add(detalle);
        this.total = this.total.add(detalle.getSubtotal());
    }

    public void eliminarProducto(DetalleVenta detalle) {
        this.productos.remove(detalle);
        this.total = this.total.subtract(detalle.getSubtotal());
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void vaciarCarrito() {
        this.productos.clear();
        this.total = BigDecimal.ZERO;
    }
}
