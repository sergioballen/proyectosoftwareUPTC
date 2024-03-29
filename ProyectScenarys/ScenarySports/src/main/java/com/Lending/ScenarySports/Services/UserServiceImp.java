package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.User;

import com.Lending.ScenarySports.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService{

    @Autowired   // inyecta el userRepository
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findByCode(int userCode) {
        return userRepository.findById(userCode);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


}
