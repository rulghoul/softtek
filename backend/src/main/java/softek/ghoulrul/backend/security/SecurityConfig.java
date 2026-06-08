package softek.ghoulrul.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final KeycloakRoleConverter keycloakRoleConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilitar CSRF (estándar en APIs stateless con JWT)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Definir reglas de acceso por método HTTP
                .authorizeHttpRequests(auth -> auth
                        // Permitir OPTIONS para que el frontend pueda hacer peticiones CORS sin token
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // REGLA: Solo ADMIN puede crear (POST) o actualizar (PUT)
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")

                        // REGLA: USER y ADMIN pueden leer (GET)
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")

                        // REGLA DE SEGURIDAD: Bloquear DELETE a nivel de infraestructura (Defensa en profundidad)
                        .requestMatchers(HttpMethod.DELETE, "/api/**").denyAll()

                        // Cualquier otra ruta requiere estar autenticado
                        .anyRequest().permitAll()
                )

                // 3. Configurar el Resource Server para usar JWT y nuestro convertidor de roles
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(keycloakRoleConverter))
                )

                // 4. Sesión stateless (no guarda estado en el servidor)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}