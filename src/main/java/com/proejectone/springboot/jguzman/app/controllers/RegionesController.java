package com.proejectone.springboot.jguzman.app.controllers;

import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.models.Region;
import com.proejectone.springboot.jguzman.app.services.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class RegionesController {

    @Autowired
    private IRegionService service;


    @GetMapping("/regiones")
    public List<Region> findAllRegion(){
        List<Region> response = service.findAll();
        return response;
    }
}
