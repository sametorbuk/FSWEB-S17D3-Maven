package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/koalas")
@AllArgsConstructor
@NoArgsConstructor
public class KoalaController {
      Map<Integer , Koala> koalas;


      @PostConstruct
      public void init(){
          koalas=new HashMap<>();
      }

      @GetMapping
    public ResponseEntity<ArrayList<Koala>> getKoalas(){
          ArrayList<Koala> allKoalas = new ArrayList<>();
          Set<Integer> keys = koalas.keySet();

          for(Integer key : keys){
              allKoalas.add(koalas.get(key));
          }

          return ResponseEntity.ok(allKoalas);
      }


      @GetMapping("/{id}")
     public ResponseEntity<Koala> getKoala(@PathVariable int id){
          if(id <= 0){
              throw new ZooException("Id can not less than 1" , HttpStatus.BAD_REQUEST);
          }

          if(id > koalas.size()){
              throw new ZooException("There is no data with this id" , HttpStatus.NOT_FOUND);
          }

          return ResponseEntity.ok(koalas.get(id));
      }


      @PostMapping
     public ResponseEntity<Koala> addKoala(@RequestBody Koala koala){
          koalas.put(koala.getId() , koala);
          return ResponseEntity.ok(koala);
      }




      @PutMapping("/{id}")
    public ResponseEntity<Koala> updateKoala(@PathVariable int id , @RequestBody Koala koala){
          koalas.put(id , koala);

          return ResponseEntity.ok(koala);
      }



      @DeleteMapping("/{id}")
    public ResponseEntity<Koala> deleteKoala(@PathVariable int id){
          if(id <= 0){
              throw new ZooException("Id can not less than 1" , HttpStatus.BAD_REQUEST);

          }

          if(!koalas.containsKey(id)){
              throw new ZooException("There is no koala with this id" , HttpStatus.NOT_FOUND);

          }

          Koala deletedKoala = koalas.get(id);
          koalas.remove(id);
          return ResponseEntity.ok(deletedKoala);
      }





}
