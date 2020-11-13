package com.bortnichenko.serviceShortLinks.service;

import com.bortnichenko.serviceShortLinks.model.LinkModel;
import com.bortnichenko.serviceShortLinks.repos.LinkRepos;

import java.util.List;
import java.util.Random;

public class LinkServices extends LinkModel {

    private final LinkRepos linkRepos;

    public LinkServices(LinkRepos linkRepos) {
        this.linkRepos = linkRepos;
    }

    // show all
    protected Iterable<LinkModel> showAll() {
        return linkRepos.findAll();
    }

    // add
    // TODO: 12/11/2020 make a check for duplicate short links (exclude duplicates)
    protected String add(String originalLink, String description) {
        if (linkIsNotEmpty(originalLink)) {
            // TODO: 13/11/2020 Make a check for the link's originality
            linkRepos.save(new LinkModel(originalLink, description, generateShortLink(6)));
            return "link was added";
        }
        return "Error, link can't be empty";
    }

    // update
    // TODO: 12/11/2020 make a check for duplicate short links
    protected String edit(String originalLink, String newOriginalLink, String description) {
        List<LinkModel> linkModelList = linkRepos.findByOriginalLink(originalLink);
        if (linkModelList.size() != 0) {
            linkModelList.forEach(linkModel -> {
                if (linkIsNotEmpty(newOriginalLink)) {
                    linkModel.setOriginalLink(newOriginalLink);
                }
                // TODO: 12/11/2020 make a check to change the short link (it changes by default)
                linkModel.setShortLink(generateShortLink(6));
                linkModel.setDescription(description);
            });
            return "Link was updated";
        }
        return "ERROR, i can't find this link";
    }

    // delete
    protected String delete(String originalLink) {
        if (linkIsNotEmpty(originalLink)) {
            List<LinkModel> linkModels = linkRepos.findByOriginalLink(originalLink);
            if (!linkModels.isEmpty()) {
                linkRepos.deleteAll(linkModels);
                return "Link was deleted";
            }
        }
        return "ERROR i can't find thisLink";
    }

    // check original
    private boolean linkIsNotEmpty(String originalLink) {
        return !originalLink.isEmpty();
    }

    // generate short link
    private String generateShortLink(int countSymbols) {
        Random random = new Random();
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(countSymbols)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
