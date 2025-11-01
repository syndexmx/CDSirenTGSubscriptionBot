package syndexmx.github.com.tgsiren.controllers.admincontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syndexmx.github.com.tgsiren.utils.Crayon;

import static java.lang.System.exit;

@RestController
@RequestMapping
@Slf4j
public class ShutdownController {

    @Value("${admin-token}")
    private String adminToken;

    @PostMapping(path = "/api/v0/shutdown")
    ResponseEntity<String> shutdown(@RequestParam String token) {
        if (token == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (!token.equals(adminToken)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        log.info(Crayon.yellow("Shutdown") + " " + Crayon.red("(!)") + Crayon.yellow(" initiated by REST-remote admin"));
        try {
            Thread thread = new Thread(() -> { exit(0); });
            thread.start();
        } catch (RuntimeException exception) {
            log.error("Error occured while starting Shutting down");
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}