package softek.ghoulrul.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
class ActivoTecnologicoControllerTest {

    @Test
    void crearActivo() {
    }

    @Test
    void obtenerTodos() {
    }

    @Test
    void buscarPorCategoria() {
    }

    @Test
    void actualizarActivo() {
    }
}