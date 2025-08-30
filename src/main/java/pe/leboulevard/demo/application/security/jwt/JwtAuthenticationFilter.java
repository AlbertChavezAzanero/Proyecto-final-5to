package pe.leboulevard.demo.application.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String jwt = extractJwtFromCookie(request);

        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String username = jwtProvider.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtProvider.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.info(">>> Usuario '{}' autenticado exitosamente. <<<", username);
                }
            }
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            log.warn("El token JWT ha expirado: {}", e.getMessage());
            handleInvalidToken(response, request, "session_expired");
        } catch (JwtException e) {
            log.warn("Token JWT inválido: {}", e.getMessage());
            handleInvalidToken(response, request, "invalid_token");
        }
    }

    private String extractJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> "jwt-token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    // --- MÉTODO NUEVO PARA CENTRALIZAR EL MANEJO DE ERRORES ---
    private void handleInvalidToken(HttpServletResponse response, HttpServletRequest request, String errorType) throws IOException {
        // 1. Limpiamos el contexto de seguridad
        SecurityContextHolder.clearContext();

        // 2. Creamos una cookie para invalidar la existente en el navegador
        Cookie cookie = new Cookie("jwt-token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // Le dice al navegador que la borre
        response.addCookie(cookie);

        // 3. Redirigimos al login con un mensaje claro
        response.sendRedirect(request.getContextPath() + "/login?error=" + errorType);
    }
}