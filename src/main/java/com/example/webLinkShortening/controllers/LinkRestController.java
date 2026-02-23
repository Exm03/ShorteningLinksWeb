package com.example.webLinkShortening.controllers;

import com.example.webLinkShortening.dto.LinkRequest;
import com.example.webLinkShortening.models.Link;
import com.example.webLinkShortening.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/links")
public class LinkRestController {

    private final LinkService service;

    public LinkRestController(LinkService service) {
        this.service = service;
    }

    @GetMapping
    public List<Link> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody LinkRequest request) {

        service.addLink(request.getOriginal(), request.getShortLink());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable Integer id) {
        try {
            service.deleteLink(id);
            return ResponseEntity.ok(Map.of("message", "Link deleted"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
