package com.guido.guzman.msv.products.services;


import com.guidio.guzman.libs.msv.commons.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
