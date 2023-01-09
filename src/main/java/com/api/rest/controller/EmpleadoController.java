package com.api.rest.controller;

import com.api.rest.model.Empleado;
import com.api.rest.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;


    @ResponseStatus(HttpStatus.CREATED)
    public Empleado saveEmployer(@RequestBody Empleado employer){
        return  empleadoService.saveEmpleado(employer);
    }

    @GetMapping
    public List<Empleado> listedEmplyers(){
        return  empleadoService.gettAllEmployers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmployerById(@PathVariable("id") Long empleadoId ){
        return empleadoService.getEmployerById(empleadoId)
                                .map(ResponseEntity::ok)
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmployer(@PathVariable("id") Long empleadoId, @RequestBody Empleado employer){
        return empleadoService.getEmployerById(empleadoId)
                .map(findEmployer -> {
                       findEmployer.setNombre(employer.getNombre());
                       findEmployer.setApellido(employer.getApellido());
                       findEmployer.setEmail(employer.getEmail());

                       Empleado updatedEmployer = empleadoService.updateEmployer(findEmployer);

                       return new ResponseEntity(updatedEmployer, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("id")
    public ResponseEntity<String> deleteEmployer (@PathVariable("id") long employerId){
        empleadoService.deleteEmployer(employerId);
        return new ResponseEntity<String>("Empleado eliminado exitosamente", HttpStatus.OK);
    }


}
