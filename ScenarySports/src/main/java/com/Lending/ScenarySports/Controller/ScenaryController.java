package com.Lending.ScenarySports.Controller;

import com.Lending.ScenarySports.Entity.Scenary;
import com.Lending.ScenarySports.Services.ScenaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Scenary")
public class ScenaryController {


    @Autowired
    private ScenaryService scenaryService;

    //crear nuevo escenario

    @PostMapping
    //devuelve un respuesta que sera la entidad
    public ResponseEntity<?> create(@RequestBody Scenary scenary)	{

        return ResponseEntity.status(HttpStatus.CREATED).body(scenaryService.save(scenary));

    }
    //leer un usuario
    @GetMapping("/{id}")  //obtiene el id
    public ResponseEntity<Scenary> read(@PathVariable int id){

        //si el id no existe devuelve el codigo 404
        Optional<Scenary> optional_Scenary = scenaryService.findById(id);
        if (optional_Scenary.isPresent()) {
            // si el id existe lo devuelve
            return ResponseEntity.ok(optional_Scenary.get()) ;

        }else{
            return ResponseEntity.notFound().build();
        }

    }

    //modificar  escenario
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Scenary scenary, @PathVariable int id){

        Optional<Scenary> Scenary= scenaryService.findById(id);
        if (Scenary.isPresent()) {
            Scenary.get().setNameScenary(scenary.getNameScenary());
            Scenary.get().setDescription(scenary.getDescription());

            return ResponseEntity.status(HttpStatus.CREATED).body(scenaryService.save(Scenary.get()));

        }
        return ResponseEntity.notFound().build();


    }

    //borrar escenario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){

        if (!scenaryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        scenaryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    //Leer todos
    @GetMapping
    public List<Scenary> readAll(){

        List<Scenary> scenarys = StreamSupport.stream(scenaryService.findAll().spliterator(),false).
                collect(Collectors.toList());
        return scenarys;
    }
}
