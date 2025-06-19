package com.teste.gepjaa.users.repository;

import com.teste.gepjaa.users.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UsersRepository extends JpaRepository<Users, String> {
    UserDetails findByLogin(String username);
}
