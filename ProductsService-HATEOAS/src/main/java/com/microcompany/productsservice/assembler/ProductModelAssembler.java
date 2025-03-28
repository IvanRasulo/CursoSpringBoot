package com.microcompany.productsservice.assembler;

import com.microcompany.productsservice.controller.ProductServiceController;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.resource.ProductResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductResource> {
    public ProductModelAssembler() {
        super(ProductServiceController.class, ProductResource.class);
    }

    @Override
    public ProductResource toModel(Product entity) {
        ProductResource productModel = instantiateModel(entity);

        productModel = productModel.builder().
                id(entity.getId()).
                nombre(entity.getName()).
                numserie(entity.getSerial()).
                build();

        productModel.add(linkTo(
                methodOn(ProductServiceController.class).getAProduct(entity.getId())
        ).withSelfRel());

        productModel.add(linkTo(
                methodOn(ProductServiceController.class).getAllProducts("")
        ).withRel("productlist"));

        return productModel;
    }


    public Collection<ProductResource> toModel(Collection<Product> entities) {
        if (entities.isEmpty()) return Collections.emptyList();

        return entities.stream().
                map(aProd -> ProductResource.builder().
                        id(aProd.getId()).
                        nombre(aProd.getName()).
                        build().
                        add(linkTo(
                                methodOn(ProductServiceController.class).getAProduct(aProd.getId())
                        ).withSelfRel())
                ).collect(Collectors.toList());
    }

    @Override
    public CollectionModel<ProductResource> toCollectionModel(Iterable<? extends Product> entities) {
        CollectionModel<ProductResource> productModels = super.toCollectionModel(entities);

        productModels.add(linkTo(
                methodOn(ProductServiceController.class).getAllProducts("")
        ).withSelfRel());

        return productModels;
    }

}
