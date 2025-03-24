package com.microcompany.productsservice.dto;

import com.microcompany.productsservice.contraints.SerialNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "Producto", description = "El producto")
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Id", description = "El identificador del producto", example = "345", format = "int32")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String nombre;

    @NotBlank
//    @Pattern(regexp = "[1-9]{3}-[1-9]{3}-[1-9]{4}")
    @SerialNumber(message = "{serial.format}")
    private String numSerie;

}