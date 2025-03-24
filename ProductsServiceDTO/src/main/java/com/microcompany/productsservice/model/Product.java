package com.microcompany.productsservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.microcompany.productsservice.contraints.SerialNumber;
import com.microcompany.productsservice.exception.GlobalProductException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "Producto", description = "El producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Id", description = "El identificador del producto", example = "345", format = "int32")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
//    @Pattern(regexp = "[1-9]{3}-[1-9]{3}-[1-9]{4}")
    @SerialNumber(message = "{serial.format}")
    private String serial;

}