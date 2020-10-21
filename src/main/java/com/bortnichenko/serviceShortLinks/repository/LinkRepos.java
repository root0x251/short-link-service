package com.bortnichenko.serviceShortLinks.repository;

import com.bortnichenko.serviceShortLinks.entity.Link;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepos extends CrudRepository<Link, Long> {
}
