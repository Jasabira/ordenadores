package com.example.ordenadores.Controllers;

import com.example.ordenadores.Entities.Laptop;
import com.example.ordenadores.Repository.LaptopRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    //CRUD
    //Lista de laptops
    @GetMapping("/")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    //Busca laptop por id
    @GetMapping("/laptops/{id}")
    public ResponseEntity<Laptop> findOne(@PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if (laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        //return laptopOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        //return laptopOpt.orElse(null);
    }

    //Crear laptop
    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if (laptop.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    //Actualizar laptop
    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    //Eliminar laptop por id
    @Hidden //Oculta elmetodo en la docAPI
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Eliminar lista de laptops
    @Hidden
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
