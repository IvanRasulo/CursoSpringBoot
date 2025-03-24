package com.microcompany.productsservice.dto;

import com.microcompany.productsservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "serial", target = "numSerie")
    ProductDTO productToProductDTO(Product product);

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "numSerie", target = "serial")
    Product productDTOToProduct(ProductDTO productDTO);

    public Collection<ProductDTO> productsToProductDTOs(Collection<Product> products);

    public Collection<Product> productDTOsToProducts(Collection<ProductDTO> productDTOs);
}
