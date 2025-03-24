package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.dto.ProductDTO;
import com.microcompany.productsservice.dto.ProductMapper;
import com.microcompany.productsservice.exception.GlobalProductException;
import com.microcompany.productsservice.exception.NewProductException;
import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.model.StatusMessage;
import com.microcompany.productsservice.persistence.ProductsRepository;
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

    @Override
    public ResponseEntity<Collection<ProductDTO>> getAllProducts(String filtro) {
        Collection<ProductDTO> products = ProductMapper.INSTANCE.productsToProductDTOs(servicioProds.getProductsByText(filtro));
        return ResponseEntity.status(HttpStatus.OK.value()).body(products);
    }

    @Override
    public ResponseEntity<ProductDTO> getAProduct(Long pid) {
        ProductDTO prod = productsRepository.findById(pid)
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .orElseThrow(() -> new ProductNotfoundException("Producto no existe:" + pid));
        return ResponseEntity.status(HttpStatus.OK.value()).body(prod);
    }

    @Override
    public ResponseEntity<ProductDTO> createProduct(ProductDTO aProdDTO) {
        Product aProd = ProductMapper.INSTANCE.productDTOToProduct((aProdDTO));
        productsRepository.save(aProd);
        aProdDTO = ProductMapper.INSTANCE.productToProductDTO(aProd);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(aProdDTO);
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(Long pid, ProductDTO aProdDTO) {
        Product aProd = ProductMapper.INSTANCE.productDTOToProduct((aProdDTO));
        aProd.setId(pid);
        productsRepository.save(aProd);
        aProdDTO = ProductMapper.INSTANCE.productToProductDTO(aProd);
        if (aProdDTO != null && aProdDTO.getId() > 0)
            return ResponseEntity.status(HttpStatus.ACCEPTED.value()).body(aProdDTO);
        else throw new GlobalProductException("No se ha podido crear el producto. Revisa la petición.");
    }

    @Override
    public ResponseEntity deleteProduct(Long pid) {
        Product prod = productsRepository.findById(pid).orElse(null);
        if (prod != null) {
            productsRepository.deleteById(pid);
            return ResponseEntity.noContent().build();
        } else throw new GlobalProductException("No se ha borrado producto con id:" + pid);
    }

    @Override
    public ResponseEntity<ProductDTO> cloneProduct(Long pid) {
        Product prod = servicioProds.duplicate(pid);
        if (prod != null) {
            ProductDTO aProdDTO = ProductMapper.INSTANCE.productToProductDTO(prod);
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(aProdDTO);
        } else throw new GlobalProductException("No se ha encontrado producto con id:" + pid);
    }


}