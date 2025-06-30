package INTRANET_ZEPHYRA.demo.Servicios;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import INTRANET_ZEPHYRA.demo.Entidad.Venta;
@Service
public class PagoServicio {

    // Método para simular un pago, por ejemplo, en efectivo
    public String procesarPago(String metodoPago, BigDecimal montoRecibido, BigDecimal totalCompra) {
        if (metodoPago.equals("efectivo")) {
            if (montoRecibido.compareTo(totalCompra) < 0) {
                return "El monto recibido es insuficiente.";
            } else {
                BigDecimal vuelto = montoRecibido.subtract(totalCompra);
                return "Pago procesado correctamente. Vuelto: " + vuelto;
            }
        } else if (metodoPago.equals("tarjeta")) {
            // Aquí puedes agregar la lógica de validación de tarjeta (simulada)
            return "Pago con tarjeta procesado correctamente.";
        } else if (metodoPago.equals("yape")) {
            // Lógica para Yape, si es el caso
            return "Pago con Yape procesado correctamente.";
        } else {
            return "Método de pago no válido.";
        }
    }
}
