package com.bortnichenko.serviceShortLinks.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Links")
public class LinkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "original_link")
    private String originalLink;
    private String description;
    @Column(name = "short_link")
    private String shortLink;

    public LinkModel() {
    }

    public LinkModel(String originalLink, String description) {
        this.originalLink = originalLink;
        this.description = description;
    }

    public LinkModel(String originalLink, String description, String shortLink) {
        this.originalLink = originalLink;
        this.description = description;
        this.shortLink = shortLink;
    }

    public String getDescription() {
        return description != null ? description : " - ";
    }

}
