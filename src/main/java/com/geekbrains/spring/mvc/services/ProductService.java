package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.models.Product;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public Long save(Product product) {
        return repository.save(product);
    }

    public Product find(Long id) {
        return repository.find(id);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
