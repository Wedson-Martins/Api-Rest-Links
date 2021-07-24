package com.wmdm.linksforyoutube.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wmdm.linksforyoutube.models.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

}
