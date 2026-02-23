package com.example.webLinkShortening.models;

import jakarta.persistence.*;

@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String original;

    @Column(name = "short_link", unique = true)
    private String shortLink;

    public Link() {}

    public Link(String original, String shortLink) {
        this.original = original;
        this.shortLink = shortLink;
    }

    public Integer getId() { return id; }
    public String getOriginal() { return original; }
    public String getShortLink() { return shortLink; }
}