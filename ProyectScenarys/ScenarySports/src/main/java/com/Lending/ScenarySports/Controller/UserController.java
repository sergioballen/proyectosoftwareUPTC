package com.Lending.ScenarySports.Controller;



import com.Lending.ScenarySports.Entity.User;
import com.Lending.ScenarySports.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/api/Users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping ("/login")
    public ResponseEntity<Object> loginUser(@RequestBody Map<String, String> credentials) {

        String userEmail = credentials.get("userEmail");
        String userPassword = credentials.get("userPassword");
        User user = null;
        Map<String, Object> response = new HashMap();



        for (int i = 0; i < userService.getUsers().size(); i++) {

            if (userEmail.equals(userService.getUsers().get(i).getEmail())) {
                user = userService.getUsers().get(i);
            }
        }

        if (user != null) {
            if (!userPassword.equals(user.getPassword())){
                response.put("route", "");
                response.put("user", null);
                response.put("error", "Contraseña incorrecta");
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

            }
        }

        if(user.getEmail() !="adminDeportes@uptc.edu.co") {
            //response.put("error", "datos correctos");

        }

        else {


            response.put("route", "");
        }
        response.put("user", user);
        response.put("OK", "Datos correctos");

        return new ResponseEntity<>(response, HttpStatus.OK);

  }

    //leer un usuario
    @GetMapping("/{userCode}")  //obtiene el id
    public ResponseEntity<User> read(@PathVariable int userCode) {

        //si el id no existe devuelve el codigo 404
        Optional<User> optional_User = userService.findByCode(userCode);
        if (optional_User.isPresent()) {
            // si el id existe lo devuelve
            return ResponseEntity.ok(optional_User.get());

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Leer todos
    @GetMapping
    public List<User> readAll() {

        List<User> users = StreamSupport.stream(userService.findAll().spliterator(), false).
                collect(Collectors.toList());
        return users;
    }
}
