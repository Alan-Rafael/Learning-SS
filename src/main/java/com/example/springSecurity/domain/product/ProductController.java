package com.example.springSecurity.domain.product;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private  final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody ProductDto productDto){
        Product product = new Product(
                null,
                productDto.name()
        );
        this.productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> list = this.productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
