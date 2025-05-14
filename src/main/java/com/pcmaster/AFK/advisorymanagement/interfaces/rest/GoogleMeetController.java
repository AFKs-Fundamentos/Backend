package com.pcmaster.AFK.advisorymanagement.interfaces.rest;

import com.pcmaster.AFK.advisorymanagement.google.GoogleMeetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/meets")
public class GoogleMeetController {
    private final GoogleMeetService meetService;

    public GoogleMeetController(GoogleMeetService meetService) {
        this.meetService = meetService;
    }

    /**
     * Crea un espacio de Google Meet usando las credenciales ya autorizadas.
     */
    @PostMapping
    public ResponseEntity<String> createMeet() {
        try {
            String link = meetService.createMeetSpace();
            return ResponseEntity.ok(link);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear Meet: " + e.getMessage());
        }
    }
}
