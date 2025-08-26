package pe.leboulevard.demo.presentation.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Error al guardar los datos. Verifique que no existan duplicados.";
        String rootCauseMessage = ex.getMostSpecificCause().getMessage().toLowerCase();

        // Personalizamos el mensaje según el campo duplicado
        if (rootCauseMessage.contains("for key 'usuarios.username'")) {
            message = "El nombre de usuario ya está en uso. Por favor, elige otro.";
        } else if (rootCauseMessage.contains("for key 'usuarios.email'")) {
            message = "El correo electrónico ya está registrado. Por favor, usa otro.";
        } else if (rootCauseMessage.contains("for key 'empleados.numero_documento'")) {
            message = "El número de documento ya está registrado.";
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);

        // Usamos el código de estado 409 Conflict, que es apropiado para duplicados
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}