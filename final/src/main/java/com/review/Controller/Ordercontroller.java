package com.review.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.review.Model.Ordermodel;
import com.review.Repo.Orderrepo;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class Ordercontroller {

    @Autowired
    private Orderrepo dishRepository;

    @GetMapping
    public List<Ordermodel> getAllDishes() {
        return dishRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordermodel> getDishById(@PathVariable Long id) {
        return dishRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ordermodel createDish(@RequestBody Ordermodel dish) {
        return dishRepository.save(dish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ordermodel> updateDish(@PathVariable Long id, @RequestBody Ordermodel updatedDish) {
        return dishRepository.findById(id).map(dish -> {
            dish.setFoodName(updatedDish.getFoodName());
            dish.setType(updatedDish.getType());
            dish.setPrice(updatedDish.getPrice());
            dishRepository.save(dish);
            return ResponseEntity.ok(dish);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
