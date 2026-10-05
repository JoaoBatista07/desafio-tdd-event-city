package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){

        List<CityDTO> result = cityService.findAll();
        return ResponseEntity.ok(result);

    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto){
        dto = cityService.insert(dto);
        URI uri = ServletUriComponentsBuilder //Pega a URL feita na requisição. Ex: http://localhost:8080/cities
                .fromCurrentRequest().path("/{id}") //Cria um novo paramêtro na URL com o id. Ex: http://localhost:8080/cities/{id}
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CityDTO> delete(@PathVariable Long id){
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
