package INTRANET_ZEPHYRA.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import INTRANET_ZEPHYRA.demo.DAO.ProductoDAO;
import INTRANET_ZEPHYRA.demo.Entidad.DetalleVentaWrapper;
import INTRANET_ZEPHYRA.demo.Servicios.VentaServicio;

@Controller
public class VentaControlador {

    @Autowired
    private ProductoDAO productoDAO;

    @Autowired
    private VentaServicio ventaServicio;

    // Muestra la lista de productos y un formulario vacío para los detalles de la venta
    @GetMapping("/Ventas")
    public String nuevaVenta(Model model) {
        model.addAttribute("productos", productoDAO.findAll());  // Lista los productos disponibles
        model.addAttribute("detalles", new DetalleVentaWrapper());  // Crea un nuevo objeto para los detalles de la venta
        return "Ventas";  // Vista de ventas
    }

    // Registrar una nueva venta cuando el formulario es enviado
    @PostMapping("/registrar")
    public String registrarVenta(@ModelAttribute("detalles") DetalleVentaWrapper detalles) {
        // Verifica que los detalles de la venta no estén vacíos
        if (detalles != null && detalles.getDetalles() != null && !detalles.getDetalles().isEmpty()) {
            ventaServicio.registrarVenta(detalles.getDetalles());  // Llama al servicio para registrar la venta
        }
        return "redirect:/Ventas";  // Redirige a la vista de ventas
    }
}
