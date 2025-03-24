package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.assembler.ProductModelAssembler;
import com.microcompany.productsservice.exception.NewProductException;
import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.model.StatusMessage;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.resource.ProductResource;
import com.microcompany.productsservice.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductServiceController implements IProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    private ProductsService servicioProds;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductModelAssembler productModelAssembler;

    @Override
    public ResponseEntity<Collection<ProductResource>> getAllProducts(String filtro) {
//        Collection<ProductResource> products = productModelAssembler.toCollectionModel(servicioProds.getProductsByText(filtro)).getContent();
        Collection<ProductResource> products = productModelAssembler.toModel(servicioProds.getProductsByText(filtro));
        return ResponseEntity.status(HttpStatus.OK.value()).body(products);
    }

    @Override
    public ResponseEntity<ProductResource> getAProduct(Long pid) {
        ProductResource prod = productsRepository.findById(pid)
                .map(productModelAssembler::toModel)
                .orElseThrow(() -> new ProductNotfoundException("Producto no existe:" + pid));

        return ResponseEntity.status(HttpStatus.OK.value()).body(prod);
    }

    @Override
    public ResponseEntity createProduct(Product aProd) {
//        if (aProd.getName() != null) {
            productsRepository.save(aProd);
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(aProd);
        /*else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "No se ha podido crear el producto. Revisa la petición."), HttpStatus.BAD_REQUEST);*/
//        } else throw new NewProductException("No se ha podido crear el producto. Revisa la petición.");
    }

    @Override
    public ResponseEntity updateProduct(Long pid, Product aProd) {
        aProd.setId(pid);
        productsRepository.save(aProd);
        if (aProd != null && aProd.getId() > 0) return ResponseEntity.status(HttpStatus.ACCEPTED.value()).body(aProd);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_MODIFIED.value(), "No se ha podido crear el producto. Revisa la petición."), HttpStatus.NOT_MODIFIED);
    }

    @Override
    public ResponseEntity deleteProduct(Long pid) {
        Product prod = productsRepository.findById(pid).orElse(null);
        if (prod != null) {
            productsRepository.deleteById(pid);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new StatusMessage(HttpStatus.NOT_FOUND.value(), "No se han encontrado producto con id:" + pid));
    }

    @Override
    public ResponseEntity cloneProduct(Long pid) {
        Product prod = servicioProds.duplicate(pid);
        if (prod != null) {
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(prod);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new StatusMessage(HttpStatus.NOT_FOUND.value(), "No se han encontrado producto con id:" + pid));
    }


}