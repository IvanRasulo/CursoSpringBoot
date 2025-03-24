package com.microcompany.productsservice.resource;

import com.microcompany.productsservice.contraints.SerialNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(name = "Producto", description = "El producto")
public class ProductResource extends RepresentationModel<ProductResource> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Id", description = "El identificador del producto", example = "345", format = "int32")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String nombre;

    @NotBlank
    @SerialNumber(message = "{serial.format}")
    private String numserie;

}