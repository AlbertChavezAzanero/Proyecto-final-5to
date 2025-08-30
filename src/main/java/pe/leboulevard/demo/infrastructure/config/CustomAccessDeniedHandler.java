package pe.leboulevard.demo.infrastructure.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Usamos RedirectAttributes para pasar mensajes que sobreviven a una redirección.
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // Preparamos los mensajes que quieres mostrar
        String accessDeniedMessage = "Estamos preparando tu espacio de trabajo. Un administrador está asignando tus permisos.";
        String welcomeMessage = "¡Te damos la bienvenida a EventSys! Nos alegra tenerte aquí. Muy pronto tendrás acceso completo.";

        redirectAttributes.addFlashAttribute("accessDeniedMessage", accessDeniedMessage);
        redirectAttributes.addFlashAttribute("welcomeMessage", welcomeMessage);

        // Almacenamos los atributos flash para que estén disponibles después de la redirección
        request.getSession().setAttribute("flash", redirectAttributes.getFlashAttributes());

        // Redirigimos al usuario a una nueva URL amigable
        response.sendRedirect(request.getContextPath() + "/acceso-denegado");
    }
}