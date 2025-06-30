package INTRANET_ZEPHYRA.demo.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import INTRANET_ZEPHYRA.demo.Servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configura el codificador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliza el codificador de contraseñas BCrypt
    }

    // Configura el DaoAuthenticationProvider para la autenticación personalizada
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UsuarioServicio usuarioServicio) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioServicio); // Utiliza el servicio de usuario personalizado para obtener los detalles
        authProvider.setPasswordEncoder(passwordEncoder()); // Utiliza el codificador de contraseñas BCrypt
        return authProvider;
    }

    // Configura las reglas de seguridad para las solicitudes HTTP usando Lambda DSL
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Desactiva la protección CSRF (deberías activar CSRF si es necesario)
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/login", "/css/**", "/js/**", "/img/**").permitAll()  // Permite acceso sin autenticación a ciertas rutas
                    .requestMatchers("/admin/**").hasRole("ADMIN")  // Solo los administradores pueden acceder a rutas bajo /admin/
                    .requestMatchers("/cliente/**").hasRole("CLIENTE")  // Solo los clientes pueden acceder a rutas bajo /cliente/
                    .anyRequest().authenticated() // Cualquier otra solicitud debe ser autenticada
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login").permitAll()  // Página de login accesible sin autenticación
                    .defaultSuccessUrl("/inicio", true) // Redirige al inicio después de un inicio de sesión exitoso
            )
            .logout(logout ->
                logout
                    .logoutUrl("/logout")  // URL para cerrar sesión
                    .logoutSuccessUrl("/login?logout").permitAll() // Redirige a la página de login después de cerrar sesión
            )
            .exceptionHandling(exceptionHandling ->
                exceptionHandling
                    .accessDeniedPage("/accessDenied")  // Redirige a una página si el usuario no tiene acceso
            );

        return http.build();  // Regresa la configuración de seguridad construida
    }

    // Configura el AuthenticationManager para la autenticación de usuarios
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, DaoAuthenticationProvider authenticationProvider) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.authenticationProvider(authenticationProvider); // Utiliza el proveedor de autenticación personalizado
        return auth.build(); // Construye el AuthenticationManager
    }
}
