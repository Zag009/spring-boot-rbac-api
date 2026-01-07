package com.rbac.api.repository;

import com.rbac.api.entity.Role;
import com.rbac.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByIsActiveTrue();

    List<User> findAllByOrderByCreatedAtDesc();

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<User> searchUsers(String search);

    long countByRole(Role role);

    long countByIsActiveTrue();
}
