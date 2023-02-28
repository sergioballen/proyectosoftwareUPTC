package com.DeportesUptc.Escenarios_Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DeportesUptc.Escenarios_Entity.Scenary_Sports;
import com.DeportesUptc.Escenarios_Service.Scenary_Services;


@RestController
@RequestMapping("/api/scenarys")
public class Scenary_Controller {

			@Autowired  
		private Scenary_Services ScenaryServices;
		
		//crear nuevo escenario
		
		@PostMapping
		//devuelve un respuesta que sera la entidad
		public ResponseEntity<?> create(@RequestBody Scenary_Sports Scenary_Sports)	{
			
			return ResponseEntity.status(HttpStatus.CREATED).body(ScenaryServices.save(Scenary_Sports));
					 
		}
		//leer un usuario
		@GetMapping("/[id]")  //obtiene el id
		public ResponseEntity<?> read(@PathVariable Long id){
			
			//si el id no existe devuelve el codigo 404
			Optional<Scenary_Sports> optional_Scenary= ScenaryServices.findbyId(id);
			if (optional_Scenary.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			// si el id existe lo devuelve
			return ResponseEntity.ok(optional_Scenary) ;
			
		}
		
		//modificar  escenario
		@PutMapping("/{id}")
		public ResponseEntity<?> update (@RequestBody Scenary_Sports Scenary_Sports, @PathVariable Long id){
			
			Optional<Scenary_Sports> Scenary= ScenaryServices.findbyId(id);
			if (Scenary.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			 Scenary.get().setName_scenarySport(Scenary_Sports.getName_scenarySport());
			 Scenary.get().setDescription(Scenary_Sports.getDescription());
			 
			 return ResponseEntity.status(HttpStatus.CREATED).body(ScenaryServices.save(Scenary.get()));
			
		}

		//borrar escenario
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete (@PathVariable Long id){
			
			if (!ScenaryServices.findbyId(id).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			ScenaryServices.deleteById(id);
			return ResponseEntity.ok().build();
		}
}
