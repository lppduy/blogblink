package com.lppduy.blogblink.repository;

import com.lppduy.blogblink.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
