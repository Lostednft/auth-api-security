package com.example.auth_api_security.repositories;

import com.example.auth_api_security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<User, String> {

    public UserDetails findByLogin(String login);
}
