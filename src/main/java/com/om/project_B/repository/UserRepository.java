package com.om.project_B.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.om.project_B.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Standard CRUD operations are automatically provided
}
