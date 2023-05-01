package com.Lending.ScenarySports.Controller;

import com.Lending.ScenarySports.Entity.Scenary;
import com.Lending.ScenarySports.Repository.ScenaryRepository;
import com.Lending.ScenarySports.Services.ScenaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Scenary")
@CrossOrigin(origins = "http://localhost:4200")
public class ScenaryController {


    @Autowired
    private ScenaryService scenaryService;

    @Autowired
    private ScenaryRepository scenaryRepository;

    //crear nuevo escenario

    @PostMapping("/Scenarys")
    //devuelve un respuesta que sera la entidad
    public ResponseEntity<?> create(@RequestBody Scenary scenary)	{
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(scenaryService.save(scenary));

        }catch (Exception e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
    //leer un Escenario
    @GetMapping("/Scenarys/{id}")  //obtiene el id
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
    @PutMapping("/Scenarys/{id}")
    public ResponseEntity<?> updateS (@PathVariable int id, @RequestBody Scenary scenary){

        Optional<Scenary> Scenary= scenaryService.findById(id);
        if (Scenary.isPresent()) {
            Scenary.get().setNameScenary(scenary.getNameScenary());
            Scenary.get().setDescription(scenary.getDescription());

            return ResponseEntity.status(HttpStatus.CREATED).body(scenaryService.save(Scenary.get()));

        }
        return ResponseEntity.notFound().build();


    }

    //borrar escenario
   /* @DeleteMapping(value = "/Scenarys/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteS (@PathVariable int id){

        if (!scenaryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        scenaryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
*/
    @DeleteMapping("/Scenarys/{id}")
    public ResponseEntity<?> deleteS(@PathVariable int id) {
        Optional<Scenary> scenary = scenaryService.findById(id);
        if (!scenary.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        try {
            scenaryService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No se puede eliminar el escenario porque está siendo referenciado por otra entidad.");
        }
        return ResponseEntity.ok().build();
    }
    //Leer todos
    @GetMapping("/Scenarys")
    public List<Scenary> readAll(){

        List<Scenary> scenarys = StreamSupport.stream(scenaryService.findAll().spliterator(),false).
                collect(Collectors.toList());
        return scenarys;
    }
}
