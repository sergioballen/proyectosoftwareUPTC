package com.Lending.ScenarySports.Controller;



import com.Lending.ScenarySports.Entity.User;
import com.Lending.ScenarySports.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@RestController
@RequestMapping("/api/Users")
public class UserController {
    @Autowired
    private UserService userService;

    //crear nuevo escenario


    //leer un usuario
    @GetMapping("/{userCode}")  //obtiene el id
    public ResponseEntity<User> read(@PathVariable int userCode){

        //si el id no existe devuelve el codigo 404
        Optional<User> optional_User = userService.findById(userCode);
        if (optional_User.isPresent()) {
            // si el id existe lo devuelve
            return ResponseEntity.ok(optional_User.get()) ;

        }else{
            return ResponseEntity.notFound().build();
        }

    }

    //Leer todos
    @GetMapping
    public List<User> readAll(){

        List<User> users = StreamSupport.stream(userService.findAll().spliterator(),false).
                collect(Collectors.toList());
        return users;
    }
}
