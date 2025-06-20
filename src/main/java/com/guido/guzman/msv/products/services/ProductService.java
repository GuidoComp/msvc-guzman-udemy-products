package com.guido.guzman.msv.products.services;

import com.guidio.guzman.libs.msv.commons.entities.Product;
import com.guido.guzman.msv.products.repositories.IProductRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final Environment environment;

    public ProductService(IProductRepository productRepository, Environment environment) {
        this.productRepository = productRepository;
        this.environment = environment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>) productRepository.findAll())
                .stream()
                .map(p -> {
                    p.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    return p;
                }).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(p -> {
            p.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return p;
        });
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

}
