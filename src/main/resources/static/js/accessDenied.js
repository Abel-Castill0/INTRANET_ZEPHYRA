document.addEventListener("DOMContentLoaded", function() {
    // Redirigir al login después de 5 segundos automáticamente
    setTimeout(function() {
        window.location.href = "/login"; // Redirige al login
    }, 3000); // 3000 ms = 5 segundos
});
