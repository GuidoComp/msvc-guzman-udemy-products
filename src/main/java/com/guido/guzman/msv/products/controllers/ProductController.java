package com.guido.guzman.msv.products.controllers;

import com.guidio.guzman.libs.msv.commons.entities.Product;
import com.guido.guzman.msv.products.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductController {

    private final IProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestHeader(name = "message-request", required = false) String message) {
        logger.info("Calling controller method ProductController::list");
        logger.info("Message request: {}", message);
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) throws InterruptedException {
        logger.info("Calling controller method ProductController::details");
        if (id.equals(10L)) {
            throw new IllegalStateException("Product not found");
        }
        if (id.equals(7L)) {
            TimeUnit.SECONDS.sleep(3L);
        }

        return productService.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        logger.info("Calling controller method ProductController::create, creating product: {}", product);
        return ResponseEntity.status(201).body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        this.productService.deleteById(id);
        logger.info("Calling controller method ProductController::delete, deleting product: {}", product.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        logger.info("Calling controller method ProductController::update, updating product: {}", product);
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        return ResponseEntity.ok(productService.save(product));
    }
}
