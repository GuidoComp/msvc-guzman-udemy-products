package com.guido.guzman.msv.products.repositories;

import com.guido.guzman.msv.products.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {
}
