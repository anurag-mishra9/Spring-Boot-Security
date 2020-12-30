package com.securityjdbc;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securityjdbc.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByName (String userName);
}
