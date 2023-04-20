package com.Lending.ScenarySports.Repository;

import com.Lending.ScenarySports.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
