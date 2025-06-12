package com.intimoda.app.services;

import com.intimoda.app.jpa.model.Reclamacion;
import com.intimoda.app.jpa.repository.ReclamacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamacionService {

    @Autowired
    private ReclamacionRepository reclamacionRepository;

    public List<Reclamacion> obtenerTodos() {
        return reclamacionRepository.findAll();
    }

    public Reclamacion obtenerPorId(Long id) {
        return reclamacionRepository.findById(id).orElse(null);
    }

    public void guardar(Reclamacion reclamacion) {
        reclamacionRepository.save(reclamacion);
    }

    public void eliminar(Long id) {
        reclamacionRepository.deleteById(id);
    }

}
