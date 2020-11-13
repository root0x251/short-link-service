package com.bortnichenko.serviceShortLinks.controller;

import com.bortnichenko.serviceShortLinks.model.LinkModel;
import com.bortnichenko.serviceShortLinks.repos.LinkRepos;
import com.bortnichenko.serviceShortLinks.service.LinkServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController extends LinkServices {

    private String shortLink;
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController(LinkRepos linkRepos) {
        super(linkRepos);
    }

    @GetMapping()
    public Iterable<LinkModel> showAllLink() {
        return showAll();
    }

    @PostMapping("/addLink")
    public String addLink(@RequestParam(name = "originalLink") String originalLink,
                          @RequestParam(name = "description", required = false) String description) {
        return add(originalLink, description);
    }

    @PostMapping("/edit")
    public String editLink(@RequestParam(name = "originalLink") String originalLink,
                           @RequestParam(name = "newOriginalLink", required = false) String newOriginalLink,
                           @RequestParam(name = "description", required = false) String description) {

        return edit(originalLink, newOriginalLink, description);
    }

    @PostMapping("/delete")
    public String deleteLink(@RequestParam(name = "originalLink") String originalLink) {
        return delete(originalLink);
    }


}
