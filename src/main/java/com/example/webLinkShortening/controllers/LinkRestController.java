package com.example.webLinkShortening.controllers;

import com.example.webLinkShortening.models.Link;
import com.example.webLinkShortening.service.LinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LinkRestController {

    private final LinkService service;

    public LinkRestController(LinkService service) {
        this.service = service;
    }

    @GetMapping("/links")
    public List<Link> getLinks() {
        return service.getAll();
    }

    @PostMapping("/links")
    public Map<String, String> addLink(@RequestBody Map<String, String> body) {

        String result = service.addLink(
                body.get("original"),
                body.get("shortLink")
        );

        return Map.of("message", result);
    }
}
