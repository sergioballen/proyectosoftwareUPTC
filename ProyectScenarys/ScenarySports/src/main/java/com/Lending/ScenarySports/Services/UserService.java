package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Iterable<User> findAll(); //devuelve una coleccion en forma de iterable

    public Page<User> findAll(Pageable pageable); //implementa la paginación, devueleve un objeto

    public Optional<User> findByCode(int userCode);  //devuelve usuarios  por id


}
