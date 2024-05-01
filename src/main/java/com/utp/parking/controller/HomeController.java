package com.utp.parking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String home(){
        return "Bienvenido desde un endpoint privado";
    }

    /*
    @GetMapping("/seguridad")
    public String seguridad(){
        return "Bienvenido si eres de seguridad";
    }

    @GetMapping("/docente")
    public String docente(){
        return "Bienvenido docente";
    }

    @GetMapping("/alumno")
    public String alumno(){
        return "Alumno";
    }
    */
}
