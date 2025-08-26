package pe.leboulevard.demo.presentation.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest; // <-- NUEVO IMPORT
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder; // <-- NUEVO IMPORT
import org.springframework.web.bind.annotation.GetMapping; // <-- NUEVO IMPORT
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.leboulevard.demo.application.auth.AuthService;
import pe.leboulevard.demo.presentation.auth.dto.AuthResponse;
import pe.leboulevard.demo.presentation.auth.dto.LoginRequest;
import pe.leboulevard.demo.presentation.auth.dto.RegisterRequest;

import java.io.IOException; // <-- NUEVO IMPORT
import java.util.Map;

@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        AuthResponse authResponse = authService.login(request);

        Cookie jwtCookie = new Cookie("jwt-token", authResponse.token());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(60 * 60 * 24); // 1 día
        response.addCookie(jwtCookie);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // ==== MÉTODO AÑADIDO PARA MANEJAR LOGOUT POR GET (INACTIVIDAD) ====
    @GetMapping("/logout")
    public void getLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. Creamos una cookie con el mismo nombre para invalidar la existente
        Cookie cookie = new Cookie("jwt-token", null);
        cookie.setMaxAge(0); // Le decimos al navegador que la elimine inmediatamente
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        // 2. Limpiamos el contexto de seguridad de Spring
        SecurityContextHolder.clearContext();

        // 3. Redirigimos al login con el mensaje de inactividad
        response.sendRedirect(request.getContextPath() + "/login?reason=inactivity");
    }
}