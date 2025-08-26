package pe.leboulevard.demo.presentation.auth.dto;

// Define los datos que el usuario enviará para registrarse.
public record RegisterRequest(
        String username,
        String email,
        String password,
        String nombres,
        String apellidos,
        String numeroDocumento,
        // ==== LÍNEAS AÑADIDAS ====
        Integer id_genero,
        Integer id_estado_civil
        // ==========================
) {
}