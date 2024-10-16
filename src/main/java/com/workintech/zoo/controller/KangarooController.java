package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.xa.XAException;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/kangaroos")
@AllArgsConstructor
@NoArgsConstructor
public class KangarooController {

    Map<Integer , Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos=new HashMap<>();
        kangaroos.put(1 , new Kangaroo(1 , "Kang" , 25.0 , 20.1 , "e" ,true));
    }

    @GetMapping
    public ArrayList<Kangaroo> getKangaroos(){
        Set<Integer> keys = kangaroos.keySet();
        ArrayList<Kangaroo> allKangaroos = new ArrayList<>();
        for(Integer key : keys){
            allKangaroos.add(kangaroos.get(key));
        }
        return allKangaroos;
    }



    @GetMapping("/{id}")
    public ResponseEntity<Kangaroo> getKangarooById(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("Id can not less than 1" , HttpStatus.BAD_REQUEST);
        }

        if(!kangaroos.containsKey(id)){
            throw new ZooException("There is no kangaroo with this id" , HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(kangaroos.get(id));
    }


    @PostMapping
    public ResponseEntity<Kangaroo> addKangaroo(@RequestBody Kangaroo kangaroo){

        if( !(kangaroo.getIsAggressive() == true || kangaroo.getIsAggressive() == false) ){
            throw new ZooException("The agrassive data of kangaroo can not empty" , HttpStatus.BAD_REQUEST);
        }

        if(kangaroo.getName() == null){
            throw new ZooException("The name of kangaroo can not empty" , HttpStatus.BAD_REQUEST);
        }

        if(kangaroo.getId() <= 0){
            throw new ZooException("The id of kangaroo can not less than 1" , HttpStatus.BAD_REQUEST);

        }

        if(kangaroo.getGender() == null){
            throw  new ZooException("The gender of kangaroo can not empty" , HttpStatus.BAD_REQUEST);
        }

        if(kangaroo.getHeight() <= 0){
            throw new ZooException("The height of kangaroo can not less then 1" , HttpStatus.BAD_REQUEST);

        }

        if(kangaroo.getWeight() <=0){
            throw new ZooException("The weight of kangaroo can not less then 1" , HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(kangaroo);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Kangaroo> updateKangaroo(@PathVariable int id , @RequestBody Kangaroo kangaroo){
        kangaroos.put(id , kangaroo);
        return ResponseEntity.ok(kangaroo);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Kangaroo> deleteKangaroo(@PathVariable int id){
        if(id <= 0){
            throw new ZooException("Id can not less than 1" , HttpStatus.BAD_REQUEST);
        }

        if(!kangaroos.containsKey(id)){
            throw new ZooException("There is no kangaroo with this id" , HttpStatus.NOT_FOUND);
        }

        Kangaroo deletedKangaroo = kangaroos.get(id);

        return ResponseEntity.ok(deletedKangaroo);
    }










}
