package com.wmdm.linksforyoutube.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wmdm.linksforyoutube.models.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

}
