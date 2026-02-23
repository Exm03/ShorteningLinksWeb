package com.example.webLinkShortening.dto;

import jakarta.validation.constraints.NotBlank;

public class LinkRequest {

    @NotBlank(message = "Original link is required")
    private String original;

    @NotBlank(message = "Short link is required")
    private String shortLink;

    public String getOriginal() { return original; }
    public String getShortLink() { return shortLink; }
}
