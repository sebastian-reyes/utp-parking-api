package com.utp.parking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/"})
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
