package com.bortnichenko.serviceShortLinks.service;

import com.bortnichenko.serviceShortLinks.entity.Link;
import com.bortnichenko.serviceShortLinks.repository.LinkRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ShellComponent
public class LinkService {

    @Autowired
    LinkRepos linkRepos;

    @ShellMethod(key = "get_all", value = "Get All Links")
    public List<Link> getAllLinks() {
        List<Link> links = new ArrayList<>();
        linkRepos.findAll().forEach(links::add);
        return links;
    }

    @ShellMethod(key = "save", value = "Save link")
    public void saveLink(@ShellOption String realLink) {
        Link link = new Link();
        link.setRealLink(realLink);
        link.setShortLink("here mast be short link");
        linkRepos.save(link);
    }

}
