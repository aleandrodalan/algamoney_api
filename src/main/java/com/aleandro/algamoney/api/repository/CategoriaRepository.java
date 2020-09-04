package com.aleandro.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleandro.algamoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}