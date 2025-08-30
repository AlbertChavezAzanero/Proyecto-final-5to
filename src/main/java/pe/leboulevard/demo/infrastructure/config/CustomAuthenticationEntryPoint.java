package pe.leboulevard.demo.infrastructure.config;

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
                         AuthenticationException authException) throws IOException {

        // CAMBIO: Ahora el mensaje de error es más preciso.
        // O podrías redirigir a una página de error personalizada, ej: "/acceso-denegado"
        response.sendRedirect(request.getContextPath() + "/login?error=unauthorized");
    }
}