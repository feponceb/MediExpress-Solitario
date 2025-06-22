package com.mediexpress.gestionprods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionprods.model.categoria;
import com.mediexpress.gestionprods.repository.categoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class categoriaService {

    @Autowired
    private categoriaRepository CategoriaRepository;

    // Listar todas las categorías
    public List<categoria> getCategorias() {
        return CategoriaRepository.findAll();
    }

    // Obtener una categoría por ID
    public categoria getCategoria(Long id) {
        return CategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    // Crear nueva categoría
    public categoria saveCategoria(categoria c) {
        return CategoriaRepository.save(c);
    }

    // Eliminar categoría
    public void deleteCategoria(Long id) {
        CategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        CategoriaRepository.deleteById(id);
    }

    // Actualizar categoría
    public categoria updateCategoria(Long id, categoria nueva) {
        categoria existente = CategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existente.setNombreCat(nueva.getNombreCat());

        return CategoriaRepository.save(existente);
    }

}
