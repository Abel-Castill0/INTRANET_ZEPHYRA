document.addEventListener("DOMContentLoaded", function () {
    const errorMessage = document.getElementById('error-message');

    // Si el error de login está presente, mostrar el mensaje
    if (errorMessage) {
        setTimeout(function () {
            errorMessage.classList.add('alert-show');
        }, 500);
    }

    // Se podría agregar validaciones extra aquí, por ejemplo, para verificar si el campo de usuario o contraseña está vacío antes de enviar el formulario
    const loginForm = document.getElementById('loginForm');
    loginForm.addEventListener('submit', function (event) {
        const username = document.querySelector('input[name="username"]').value;
        const password = document.querySelector('input[name="password"]').value;
        
        if (!username || !password) {
            alert('Por favor, completa todos los campos.');
            event.preventDefault();  // Evita el envío del formulario si hay campos vacíos
        }
    });
});
// Este script maneja la lógica del formulario de login, mostrando mensajes de error y validando campos
// Se puede extender para incluir más funcionalidades como validación de formato de email, etc.             