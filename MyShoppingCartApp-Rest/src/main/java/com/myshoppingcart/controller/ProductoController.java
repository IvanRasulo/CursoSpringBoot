package com.myshoppingcart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @RequestMapping("")
    public String responde() {
        return "Productos";
    }

}
