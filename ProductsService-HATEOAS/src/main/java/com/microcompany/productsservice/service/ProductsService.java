package com.microcompany.productsservice.service;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @PersistenceContext
    EntityManager em;

    public List<Product> getProductsByText(String text) {
        List<Product> prods = productsRepository.findByNameContaining(text);
        if (prods != null && prods.size() > 0) return prods;
        else throw new ProductNotfoundException("No hay productos");
    }

    public Product duplicate(Long pid) {
        Product prod = productsRepository.findById(pid).get();
        em.detach(prod);
        prod.setId(null);
        return productsRepository.save(prod);
    }
}
