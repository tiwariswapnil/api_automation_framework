package com.restful.api.controllers;

import com.restful.api.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Object> getProductId(@PathVariable("id") String id) {
        return new ResponseEntity<>(productRepo.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }


}
