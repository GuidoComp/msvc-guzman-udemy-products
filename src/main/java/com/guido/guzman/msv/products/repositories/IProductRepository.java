package com.guido.guzman.msv.products.repositories;

import com.guidio.guzman.libs.msv.commons.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {
}
