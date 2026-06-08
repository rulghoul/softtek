package softek.ghoulrul.backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class InventarioException extends RuntimeException {

    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public InventarioException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
