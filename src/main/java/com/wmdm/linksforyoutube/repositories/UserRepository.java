package com.wmdm.linksforyoutube.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wmdm.linksforyoutube.models_for_access.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
