package com.example.webLinkShortening.service;

import com.example.webLinkShortening.models.Link;
import com.example.webLinkShortening.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    private final LinkRepository repository;

    public LinkService(LinkRepository repository) {
        this.repository = repository;
    }

    public List<Link> getAll() {
        return repository.findAll();
    }

    public String addLink(String original, String shortLink) {

        if (original.isBlank() || shortLink.isBlank()) {
            return "Заполните все поля";
        }

        if (repository.findByShortLink(shortLink).isPresent()) {
            return "Такая сокращенная ссылка уже существует";
        }

        repository.save(new Link(original, shortLink));
        return "Готово";
    }
}

