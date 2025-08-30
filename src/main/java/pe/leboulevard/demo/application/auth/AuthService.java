package pe.leboulevard.demo.application.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.leboulevard.demo.application.security.jwt.JwtProvider;
import pe.leboulevard.demo.domain.usuarios.model.UsuariosModel;
import pe.leboulevard.demo.domain.usuarios.repository.UsuariosRepository;
import pe.leboulevard.demo.infrastructure.empleados.entity.EmpleadosEntity;
import pe.leboulevard.demo.infrastructure.empleados.jpa.EmpleadosRepositoryJpa;
// IMPORTAMOS LAS ENTIDADES QUE VAMOS A USAR
import pe.leboulevard.demo.infrastructure.genero.entity.GeneroEntity;
import pe.leboulevard.demo.infrastructure.estadocivil.entity.EstadoCivilEntity;
import pe.leboulevard.demo.infrastructure.roles.entity.RolesEntity;
import pe.leboulevard.demo.infrastructure.roles.jpa.RolesRepositoryJpa;
import pe.leboulevard.demo.infrastructure.usuarios.entity.UsuariosEntity;
import pe.leboulevard.demo.infrastructure.usuarios.jpa.UsuariosRepositoryJpa;
import pe.leboulevard.demo.presentation.auth.dto.AuthResponse;
import pe.leboulevard.demo.presentation.auth.dto.LoginRequest;
import pe.leboulevard.demo.presentation.auth.dto.RegisterRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuariosRepository usuariosRepository;
    private final EmpleadosRepositoryJpa empleadosRepositoryJpa;
    private final RolesRepositoryJpa rolesRepositoryJpa;
    private final UsuariosRepositoryJpa usuariosRepositoryJpa;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UsuariosModel user = usuariosRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtProvider.generateAccessToken(user);
        return new AuthResponse(token);
    }

    @Transactional
    public Map<String, String> register(RegisterRequest request) {
        // 1. Crear la entidad del empleado
        EmpleadosEntity nuevoEmpleadoEntity = new EmpleadosEntity();
        nuevoEmpleadoEntity.setNombres(request.nombres());
        nuevoEmpleadoEntity.setApellidos(request.apellidos());
        nuevoEmpleadoEntity.setNumeroDocumento(request.numeroDocumento());
        nuevoEmpleadoEntity.setFechaIngreso(LocalDate.now());
        nuevoEmpleadoEntity.setSalario(BigDecimal.ZERO);

        GeneroEntity genero = new GeneroEntity();
        genero.setIdGenero(request.id_genero());
        nuevoEmpleadoEntity.setGenero(genero);

        EstadoCivilEntity estadoCivil = new EstadoCivilEntity();
        estadoCivil.setIdEstadoCivil(request.id_estado_civil());
        nuevoEmpleadoEntity.setEstadoCivil(estadoCivil);

        nuevoEmpleadoEntity.setUsuarioCreacion("system-register");
        nuevoEmpleadoEntity.setUsuarioActualizacion("system-register");

        EmpleadosEntity empleadoGuardado = empleadosRepositoryJpa.save(nuevoEmpleadoEntity);

        // --- INICIO DE LA CORRECCIÓN ---
        // 2. Buscar el rol por defecto "Usuario" por su nombre
        RolesEntity rolPorDefecto = rolesRepositoryJpa.findByNombre("Usuario")
                .orElseThrow(() -> new RuntimeException("El rol 'Usuario' por defecto no fue encontrado en la base de datos."));
        // --- FIN DE LA CORRECCIÓN ---

        // 3. Crear la entidad del usuario
        UsuariosEntity nuevoUsuarioEntity = new UsuariosEntity();
        nuevoUsuarioEntity.setUsername(request.username());
        nuevoUsuarioEntity.setEmail(request.email());
        nuevoUsuarioEntity.setPasswordHash(passwordEncoder.encode(request.password()));
        nuevoUsuarioEntity.setEstaActivo(true);
        nuevoUsuarioEntity.setEmpleado(empleadoGuardado);
        nuevoUsuarioEntity.setRol(rolPorDefecto); // Asignamos el rol "Usuario"
        nuevoUsuarioEntity.setUsuarioCreacion("system-register");
        nuevoUsuarioEntity.setUsuarioActualizacion("system-register");

        usuariosRepositoryJpa.save(nuevoUsuarioEntity);

        // 4. Devolver un mensaje de éxito
        Map<String, String> response = new HashMap<>();
        response.put("message", "¡Registro exitoso! Por favor, inicia sesión.");
        return response;
    }
}