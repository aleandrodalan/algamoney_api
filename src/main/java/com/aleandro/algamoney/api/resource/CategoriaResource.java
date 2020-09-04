package com.aleandro.algamoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aleandro.algamoney.api.model.Categoria;
import com.aleandro.algamoney.api.model.dto.CategoriaDTO;
import com.aleandro.algamoney.api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public List<Categoria> listar() {
		return service.listar();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody CategoriaDTO categoriaDTO, 
										   HttpServletResponse response) {
		Categoria categoriaSalva = service.salvar(convertDtoToEntity(categoriaDTO));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Categoria> categoria = service.buscarPeloCodigo(codigo); 
		
		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build(); 
	}
	
	private Categoria convertDtoToEntity(CategoriaDTO dto) {
		return Categoria
					.builder()
					.nome(dto.getNome())
					.build();
	}
	
	private CategoriaDTO convertEntityToDto(Categoria entity) {
		return CategoriaDTO
					.builder()
					.nome(entity.getNome())
					.build();
	}
}