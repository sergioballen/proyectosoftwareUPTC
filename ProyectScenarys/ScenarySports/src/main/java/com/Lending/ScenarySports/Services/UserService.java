package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    public Iterable<User> findAll(); //devuelve una coleccion en forma de iterable

    public Page<User> findAll(Pageable pageable); //implementa la paginación, devueleve un objeto

    public Optional<User> findById(int userCode);  //devuelve usuarios  por id

}
