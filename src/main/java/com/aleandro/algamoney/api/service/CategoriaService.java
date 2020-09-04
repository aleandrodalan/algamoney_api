package com.aleandro.algamoney.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.algamoney.api.model.Categoria;
import com.aleandro.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> listar() {
		return repository.findAll();
	}
	
	public Categoria salvar(Categoria categoria) {
		return repository.save(categoria);
	}
	
	public Optional<Categoria> buscarPeloCodigo(Long codigo) {
		return repository.findById(codigo);
	}
}
