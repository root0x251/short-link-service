package com.bortnichenko.serviceShortLinks.repos;

import com.bortnichenko.serviceShortLinks.model.LinkModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkRepos extends CrudRepository<LinkModel, Long> {

    List<LinkModel> findByOriginalLink(String originalLink);

}
