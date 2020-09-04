package com.aleandro.algamoney.api.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
	
	private Long id;

	@NotNull
	@Size(min= 3, max = 30)
	private String nome;
}