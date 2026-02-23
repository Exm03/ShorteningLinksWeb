package com.example.webLinkShortening.repository;

import com.example.webLinkShortening.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Integer> {
    Optional<Link> findByShortLink(String shortLink);
}
