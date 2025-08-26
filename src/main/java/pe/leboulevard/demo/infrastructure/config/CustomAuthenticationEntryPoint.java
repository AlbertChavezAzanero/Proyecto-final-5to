package pe.leboulevard.demo.infrastructure.config; // Asegúrate de que tu paquete sea el correcto

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException { // <-- ¡AQUÍ ESTÁ EL CAMBIO!

        // Cada vez que ocurra un error de autenticación (ej: token JWT expirado o inválido),
        // redirigimos al usuario a la página de login con el parámetro 'reason=inactivity'.
        response.sendRedirect("/login?reason=inactivity");
    }
}