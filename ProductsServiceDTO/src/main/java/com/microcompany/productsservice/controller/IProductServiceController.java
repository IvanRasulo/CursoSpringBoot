package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.dto.ProductDTO;
import com.microcompany.productsservice.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collection;

@RestController
@RequestMapping("/default")
@Validated
//@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
@Tag(name = "Endpoints Productos", description = "Endpoints para la gestión de productos")
public interface IProductServiceController {

    @Operation(summary = "Lista de productos", description = "Método para solicitar la lista de productos, con capacidad de filtrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Si existen productos"),
            @ApiResponse(responseCode = "404", description = "Si no existen productos, incluso para el filtrado"),
            @ApiResponse(responseCode = "412", description = "Si el filtro no cumple con los constraints")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Collection<ProductDTO>> getAllProducts(
            @Parameter(name = "filtro", description = "texto de filtrado 3-20 chars", example = "Hola")
            /*@Size(min = 3, max = 10)*/ @RequestParam(value = "nombrewith", defaultValue = "") String filtro
    );

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ProductDTO> getAProduct(@Min(1) @PathVariable("id") Long pid);

    //    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ProductDTO> createProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Producto a crear son id", required = true)
            @Valid @RequestBody ProductDTO aProd
    );

    @PutMapping(value = "/{pid}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable Long pid, @RequestBody ProductDTO aProd);

    @DeleteMapping(value = "/{pid}")
    ResponseEntity deleteProduct(@PathVariable Long pid);

    @PostMapping(value = "/{pid}/clone")
    ResponseEntity<ProductDTO> cloneProduct(@PathVariable Long pid);
}
