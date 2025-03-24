package com.myshoppingcart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @RequestMapping("")
    public String responde() {
        return "Usuarios";
    }

}
