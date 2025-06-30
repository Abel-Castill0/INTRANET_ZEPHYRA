package INTRANET_ZEPHYRA.demo.Controlador;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import INTRANET_ZEPHYRA.demo.Entidad.Producto;
import INTRANET_ZEPHYRA.demo.Servicios.ProductoServicio;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;  // Corregido el nombre de la variable

    // Muestra la lista de productos
    @GetMapping("/listaproductos")
    public String listaProductos(Model model) {
        model.addAttribute("listaproductos", productoServicio.listaProductos());  // Obtiene los productos
        return "listaproductos";  // Vista que muestra todos los productos
    }

    // Muestra el formulario para crear un nuevo producto
    @GetMapping("/nuevoProducto")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());  // Crea un nuevo objeto Producto
        return "nuevoProducto";  // Vista para crear un nuevo producto
    }

    // Guarda un nuevo producto
    @PostMapping("/guardarProducto")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoServicio.guardarProducto(producto);  // Guarda el nuevo producto
        return "redirect:/listaproductos";  // Redirige a la lista de productos
    }

    // Muestra el formulario para editar un producto existente
    @GetMapping("/producto/editar/{idProducto}")
    public String actualizarProducto(@PathVariable Integer idProducto, Model model) {
        Producto producto = productoServicio.obtenerProducto(idProducto);  // Obtiene el producto por ID
        model.addAttribute("producto", producto);  // Agrega el producto a la vista
        return "editarProducto";  // Vista para editar un producto
    }

    // Actualiza un producto
    @PostMapping("/producto/actualizar")
    public String actualizarProducto(@ModelAttribute("producto") Producto producto) {
        productoServicio.guardarProducto(producto);  // Guarda el producto actualizado
        return "redirect:/listaproductos";  // Redirige a la lista de productos
    }

    // Elimina un producto
    @GetMapping("/producto/eliminar/{idProducto}")
    public String eliminarProducto(@PathVariable Integer idProducto) {
        productoServicio.eliminarProducto(idProducto);  // Elimina el producto por ID
        return "redirect:/listaproductos";  // Redirige a la lista de productos
    }
}
// Este controlador maneja las operaciones CRUD para los productos en la aplicación.
// Permite listar, crear, editar y eliminar productos a través de las vistas correspondientes.
// Utiliza el servicio ProductoServicio para interactuar con la base de datos y realizar las operaciones necesarias.
// Las vistas asociadas deben estar definidas en el directorio de plantillas de la aplicación.
// Asegúrate de que las rutas y nombres de vistas coincidan con los utilizados en tu aplicación.
// Este controlador es una parte fundamental de la gestión de productos en la aplicación INTRANET_Z                 EPHYRA.
// Asegúrate de que el servicio ProductoServicio esté correctamente implementado y configurado para
// interactuar con la base de datos y realizar las operaciones CRUD necesarias.
// Además, verifica que las entidades y repositorios estén correctamente definidos para evitar errores de ejecución.
// Este controlador es esencial para la gestión de productos en la aplicación INTRANET_ZEPHYRA.
// Permite a los usuarios crear, editar, eliminar y listar productos a través de una interfaz web amigable.
// Asegúrate de que las vistas estén correctamente configuradas y que el servicio           
