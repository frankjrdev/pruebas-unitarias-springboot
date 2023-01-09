package com.api.rest.service;


import com.api.rest.model.Empleado;
import com.api.rest.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado saveEmpleado(Empleado empleado){

        Optional<Empleado> savedEmployer =  empleadoRepository.findByEmail(empleado.getEmail());


        if (savedEmployer.isPresent()){
            throw new ResourceAccessException("El correo ya fue registrado anteriormente: " + empleado.getEmail());
        }

        return empleadoRepository.save(empleado);
    }

    public List<Empleado> gettAllEmployers() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> getEmployerById(long id){
        return empleadoRepository.findById(id);
    }



    public Empleado updateEmployer(Empleado empleadoUpdated){
        return empleadoRepository.save(empleadoUpdated);
    }

    public void deleteEmployer(long id){
        empleadoRepository.deleteById(id);
    }
}
